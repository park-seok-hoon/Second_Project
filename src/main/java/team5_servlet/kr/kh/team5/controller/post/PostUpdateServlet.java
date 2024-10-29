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

@WebServlet("/post/update")
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	private CategoryService categoryService = new CategoryServiceImp();
	private BoardService boardService = new BoardServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 번호 가져오기
		int num = 0;
		try {
			num = Integer.parseInt(request.getParameter("num"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		PostVO post = postService.getPostByPnum(num);
		
		
		// 현재 세션의 유저 정보를 가져옴
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		// 현재 로그인 정보와 게시글의 작성자가 다를 경우
		if(!user.getMb_id().equals(post.getP_mb_id())) {
			request.setAttribute("msg", "접근할 수 없는 기능입니다.");
			request.setAttribute("url", "/");
			System.out.println("incorrect userID");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
		
		// 카테고리 목록 불러오기
		ArrayList<CategoryVO> cateList = categoryService.getCategoryList();
		request.setAttribute("cateList", cateList);
		
		// 게시판 목록 불러오기
		ArrayList<BoardVO> boardList = boardService.getBoardList();
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/WEB-INF/views/post/postupdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 세션의 유저 정보를 가져옴
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		// 유저가 작성한 게시글 정보 가져오기
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bo_num = Integer.parseInt( request.getParameter("board"));
		int num = Integer.parseInt( request.getParameter("num"));
		
		// 작성하는 유저 정보 가져오기
		String writer = user.getMb_id();
		
		// 게시글 객체 생성
		PostVO post = new PostVO(num, title, content, bo_num, writer);
		
		// 서비스에게 게시글을 주면서 수정하라고 시킴
		if(postService.updatePost(post)) {
			request.setAttribute("msg", "게시글을 수정했습니다.");
			request.setAttribute("url", "/");
		}else {
			request.setAttribute("msg", "게시글을 수정하지 못했습니다.");
			request.setAttribute("url", "/post/update?num="+num);
		}
		
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
