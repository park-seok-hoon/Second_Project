package team5_servlet.kr.kh.team5.controller.signup;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.MemberService;
import team5_servlet.kr.kh.team5.service.MemberServiceImp;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면에서 보낸 데이터를 가져옴
				//form태그 안 입력 태그에 name이 id 요소의 값을 가져옴
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String nickname = request.getParameter("nickname");
				String mail = request.getParameter("mail");
				String phone = request.getParameter("phone"); 
				String name = request.getParameter("name");
				
				MemberVO member = new MemberVO(id,pw,nickname,mail,phone,name);
				
				boolean res = memberService.signup(member);
				if(res) {
					request.setAttribute("msg", "회원 가입에 성공했습니다.");
					request.setAttribute("url", "");
				}else {
					request.setAttribute("msg", "회원 가입에 실패했습니다.");
					request.setAttribute("url", "signup");
				}
				request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}