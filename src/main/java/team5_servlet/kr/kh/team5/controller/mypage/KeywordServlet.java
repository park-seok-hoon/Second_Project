package team5_servlet.kr.kh.team5.controller.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.KeywordVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;
import team5_servlet.kr.kh.team5.pagination.PageMaker;
import team5_servlet.kr.kh.team5.service.KeywordService;
import team5_servlet.kr.kh.team5.service.KeywordServiceImp;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;


@WebServlet("/keyword")
public class KeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService=new PostServiceImp();
    private KeywordService keywordService=new KeywordServiceImp();
    public KeywordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e) {
			page = 1;
		}
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		KeywordVO search1 = keywordService.getKey(user.getMb_id());
		if(search1==null) {
			Criteria cri = new Criteria(page, 2, "");
			//검색어, 검색타입에 맞는 전체 게시글 개수를 가져옴 
			int totalCount = postService.getTotalCountByKey(cri);
			System.out.println(totalCount);
			PageMaker pm = new PageMaker(5, cri, totalCount);
			request.setAttribute("pm", pm);
			//현재 페이지 정보에 맞는 게시글 리스트를 가져옴
			ArrayList<BoardVO> list = postService.getPostList(cri);
		}else {
			String search=search1.getK_content();
			Criteria cri = new Criteria(page, 2, search);
			//검색어, 검색타입에 맞는 전체 게시글 개수를 가져옴 
			int totalCount = postService.getTotalCountByKey(cri);
			System.out.println(totalCount);
			PageMaker pm = new PageMaker(5, cri, totalCount);
			request.setAttribute("pm", pm);
			//현재 페이지 정보에 맞는 게시글 리스트를 가져옴
			ArrayList<BoardVO> list = postService.getPostList(cri);
			System.out.println(list);
			request.setAttribute("list", list);//화면에 전송
		}
		//검색어, 검색 타입, 현재 페이지, 한 페이지 컨텐츠 개수를 이용하여 현재 페이지 정보 객체를 생성 
		
		
		request.getRequestDispatcher("/WEB-INF/mypage/keyword.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		KeywordVO search = keywordService.getKey(user.getMb_id());
		String k_content = request.getParameter("keyword");
		
		if(k_content.equals("")) {
			System.out.println("응애");
			k_content=search.getK_content();
		}
		String k_mb_id=user.getMb_id();
		KeywordVO keyword=new KeywordVO(k_content,k_mb_id);
		boolean res=keywordService.insertKey(keyword);
		if(res) {
			System.out.println("성공");
			doGet(request, response);
		}else {
			doGet(request, response);
		}
		
	}


}