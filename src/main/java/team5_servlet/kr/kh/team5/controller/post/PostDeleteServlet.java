package team5_servlet.kr.kh.team5.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;
import team5_servlet.kr.kh.team5.utils.Methods;

@WebServlet("/post/delete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num;
		
		try {
			num = Integer.parseInt(request.getParameter("num"));
		}catch (Exception e) {
			System.out.println("null num");
			num = -1;
		}
		PostVO post = postService.selectPostByPnum(num);
		
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		String writer = user.getMb_id();
		
		// 서비스에게 게시글을 주면서 등록하라고 시킴
		if(postService.deletePost(post, user)) {
			request.setAttribute("msg", "게시글을 삭제했습니다.");
		}else {
			request.setAttribute("msg", "게시글을 삭제하지 못했습니다.");
		}
		
		// 메인화면으로 돌아가기(게시글 목록 조회 구현 후 수정 필요함)
		request.setAttribute("url", "/");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
