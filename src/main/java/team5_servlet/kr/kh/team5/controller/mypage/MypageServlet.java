package team5_servlet.kr.kh.team5.controller.mypage ;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.dto.LoginDTO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.MemberService;
import team5_servlet.kr.kh.team5.service.MemberServiceImp;



@WebServlet("/mypageHome")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/mypage/mypageHome.jsp").forward(request, response);
	}

	

}