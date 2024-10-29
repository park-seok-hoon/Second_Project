package team5_servlet.kr.kh.team5.controller.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.MemberService;
import team5_servlet.kr.kh.team5.service.MemberServiceImp;


@WebServlet("/findid")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/findid.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phonenum = request.getParameter("phonenum");
	
		MemberVO user = new MemberVO(name,email,phonenum); 
		
		MemberVO findUserId = memberService.findId(user);
		
		if(findUserId == null)
		{
			request.setAttribute("msg", "아이디를 찾는데 실패하였습니다.");
			request.setAttribute("url", "login");
		}else{
			request.setAttribute("msg", "아이디는 "+findUserId.getMb_id());	
			request.setAttribute("url", "login");
		}
			
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
