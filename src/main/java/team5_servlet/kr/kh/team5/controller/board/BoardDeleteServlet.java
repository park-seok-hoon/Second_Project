package team5_servlet.kr.kh.team5.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;

@WebServlet("/board/delete")// /board/delete로 하면 연결이 안됩니다 그런데 어디서 사용하고 있는지 찾지를 못하겠습니다... -조민석
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PostService postService = new PostServiceImp();
	private BoardService boardService = new BoardServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = 0;
		try {
			num = Integer.parseInt(request.getParameter("num"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		boolean res = boardService.deleteBoard(num, user);
		//추가에 성공하면
		if(res) {
			request.setAttribute("msg", "삭제에성공했습니다.");
			request.setAttribute("url", "board/manager");
		}
		//실패하면
		else {
			request.setAttribute("msg", "삭제에실패했습니다.");
			request.setAttribute("url", "board/manager");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}


}
