package team5_servlet.kr.kh.team5.controller.post;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.CategoryVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;
import team5_servlet.kr.kh.team5.service.CategoryService;
import team5_servlet.kr.kh.team5.service.CategoryServiceImp;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;

@WebServlet("/post/insert")
public class PostInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	private CategoryService categoryService = new CategoryServiceImp();
	private BoardService boardService = new BoardServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카테고리 목록 불러오기
		ArrayList<CategoryVO> cateList = categoryService.getCategoryList();
		request.setAttribute("cateList", cateList);
		
		// 게시판 목록 불러오기
		ArrayList<BoardVO> boardList = boardService.getBoardList();
		request.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("/WEB-INF/views/post/postinsert.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 세션의 유저 정보를 가져옴
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		// 유저가 작성한 게시글 정보 가져오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bo_num = Integer.parseInt( request.getParameter("board"));
		
		// 작성하는 유저 정보 가져오기
		String writer = user.getMb_id();
		
		// 게시글 객체 생성
		PostVO post = new PostVO(title, content, bo_num, writer);
		
		// 서비스에게 게시글을 주면서 등록하라고 시킴
		if(postService.insertPost(post)) {
			request.setAttribute("msg", "게시글을 등록했습니다.");
			request.setAttribute("url", "/category");
//			System.out.println("게시글 등록 완료");
		}else {
			request.setAttribute("msg", "게시글을 등록하지 못했습니다.");
			request.setAttribute("url", "/");
	//					response.sendRedirect(request.getContextPath()+"/board/list");
	//					System.out.println("게시글 등록 실패");
		}
		
		// 메인화면으로 돌아가기(게시글 목록 조회 구현 후 수정 필요함)
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
