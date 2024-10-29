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


@WebServlet("/post/detail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	private Methods mthds = new Methods();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num;
		
		try {
			num = Integer.parseInt(request.getParameter("num"));
		}catch (Exception e) {
			System.out.println("null num");
			num = -1;
		}
		
		// 현재 세션의 유저 정보를 가져옴
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user != null) {
			// 게시글 관리 권한 여부 확인
			boolean postAuth = mthds.checkAuth(3, user.getA_list());
			request.setAttribute("postAuth", postAuth);
			
			// 댓글 관리 권한 여부 확인
			boolean rplyAuth = mthds.checkAuth(4, user.getA_list());
			request.setAttribute("rplyAuth", rplyAuth);
			
			
		}
		
		PostVO post = postService.selectPostByPnum(num);	// num 값을 가져와서 post를 쿼리함
		
		// 조회수를 증가시킴
		postService.increaseView(post.getP_num());
		post.setP_view(post.getP_view()+1);
		
		request.setAttribute("post", post);			// 쿼리한 post의 정보를 화면에 전송함

		// 화면을 송출함.
		request.getRequestDispatcher("/WEB-INF/views/post/postdetail.jsp").forward(request, response);
	}

}