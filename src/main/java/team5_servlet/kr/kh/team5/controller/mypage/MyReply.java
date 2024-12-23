package team5_servlet.kr.kh.team5.controller.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;
import team5_servlet.kr.kh.team5.pagination.PageMaker;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;
import team5_servlet.kr.kh.team5.service.ReplyService;
import team5_servlet.kr.kh.team5.service.ReplyServiceImp;


@WebServlet("/myReply")
public class MyReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
     private ReplyService replyService=new ReplyServiceImp();  
     private PostService postService=new PostServiceImp();
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String search = request.getParameter("search");
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		String id=user.getMb_id();
		//화면에서 보낸 page정보를 가져옴
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		}catch(Exception e) {
			e.printStackTrace();
			page = 1;
		}
		//type과 search와 page를 이용해서 Criteria 객체를 생성
		Criteria cri = new Criteria(page,2);
		
		//서비스에게 현재 페이지 정보를 주면서 현재 페이지 정보에 맞는 게시글 수를 가져오라고 시킴 : getTotalCount
		int totalCount = replyService.getTotalCount(cri,id);
		System.out.println(totalCount);
		//현재 페이지 정보, 게시글수, 한 페이지네이션에서 페이지 갯수를 이용하여 PageMaker 클래스 객체를 생성
		PageMaker pm = new PageMaker(5, cri, totalCount);
		//생성한 객체를 화면에 전달
		request.setAttribute("pm", pm);
		//서비스에게 현재 페이지 정보를 주면서 게시글 리스트를 달라고 요청
		ArrayList<ReplyVO> list = replyService.getReplyList(cri,id);
		ArrayList<PostVO> list2= postService.getPostTitle(id);
		System.out.println(list);
		System.out.println(list2);
		//화면에 게시글 리스트를 전송 : 화면에서 사용할 이름 - boardList
		request.setAttribute("replyList", list);
		request.setAttribute("postList", list2);
		request.getRequestDispatcher("/WEB-INF/mypage/myReply.jsp").forward(request, response);
	}
	



}