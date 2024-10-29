package team5_servlet.kr.kh.team5.controller.login ;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team5_servlet.kr.kh.team5.model.dto.LoginDTO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.MemberService;
import team5_servlet.kr.kh.team5.service.MemberServiceImp;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//아이디와 비번을 이용하여 LoginDTO 객체를 생성
		LoginDTO loginDto = new LoginDTO(id, pw);
		
		//LoginDTO 객체를 이용해서 memeberService에게 MemberVO 객체를 달라고 요청.
		MemberVO user = memberService.getMember(loginDto);
		//성공하면 세션에 회원 정보를 저장하고 메인페이지로 이동
		if(user != null) {
			request.setAttribute("msg", "로그인에 성공했습니다.");
			request.setAttribute("url", "");
			
			user.setA_list(memberService.selectAlistById(id));
			System.out.println(user.getA_list());
			
			request.getSession().setAttribute("user", user);
		}
		//실패하면 로그인 페이지로 이동
		else {
			request.setAttribute("msg", "로그인에 실패했습니다.");
			request.setAttribute("url", "login");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}


}