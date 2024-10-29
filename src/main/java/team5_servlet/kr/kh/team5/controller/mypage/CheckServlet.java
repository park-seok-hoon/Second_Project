package team5_servlet.kr.kh.team5.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/check")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("/WEB-INF/mypage/check.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		String checkId=request.getParameter("id");
		String checkPw=request.getParameter("pw");
	
		if(user.getMb_id().equals(checkId) && user.getMb_pw().equals(checkPw)) {
			request.getRequestDispatcher("/WEB-INF/mypage/changeMember.jsp").forward(request, response);
			System.out.println("성공");
		}else {
			doGet(request, response);
			System.out.println("실패");
			
		}
	}

}
