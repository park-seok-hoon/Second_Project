package team5_servlet.kr.kh.team5.controller.mypage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.MemberService;
import team5_servlet.kr.kh.team5.service.MemberServiceImp;


@WebServlet("/changeMember")
public class ChangeMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImp();
    
   
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		request.getRequestDispatcher("/WEB-INF/mypage/changeMember.jsp").forward(request, response);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	MemberVO user = (MemberVO)request.getSession().getAttribute("user");
    	request.setAttribute("user",user );
		String id = user.getMb_id();
		String pw = request.getParameter("pw");
		String nickname=request.getParameter("nickname");
		String mail=request.getParameter("mail");
		String phone=request.getParameter("phone");
		String name=request.getParameter("name");
		System.out.println(user);
		
		if(pw=="") {
			pw=user.getMb_pw();
			
		}
		if(nickname=="") {
			nickname=user.getMb_nickname();
			
		}
		if(mail=="") {
			mail=user.getMb_mail();
		}
		if(phone=="") {
			phone=user.getMb_phone();
		}
		if(name=="") {
			name=user.getMb_name();
		}
		MemberVO member = new MemberVO(id,pw,nickname,mail,phone,name);
			boolean res = memberService.changeMember(member);
			if(res) {
				System.out.println("변경 성공");
				response.sendRedirect(request.getContextPath() + "/");
			}
			else {
				//실패하면 회원가입 페이지 유지
				System.out.println("변경 실패");
				doGet(request, response);
			}
		}
		


	

}
