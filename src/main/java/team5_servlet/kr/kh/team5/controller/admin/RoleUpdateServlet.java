package team5_servlet.kr.kh.team5.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.service.AdminService;
import team5_servlet.kr.kh.team5.service.AdminServiceImp;
import team5_servlet.kr.kh.team5.utils.Methods;

@WebServlet("/auth/update")
public class RoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();
	private Methods method = new Methods();
	private String prevName;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 기존 역할 이름 받아오기
		prevName = request.getParameter("prevName");
		System.out.println("prevName : "+prevName);
		
		// 새 이름 받아오기
		String newName = request.getParameter("name");
		System.out.println("newName : " + newName);
		// jsp에서 checkbox의 value를 mb_id로 해서 전달 받기.
		// values 이용!
		String [] mbList = request.getParameterValues("auth-check");
		
		// getParameter를 하면 'on' 또는 null 반환
		// 'on'일 경우 '1' 반환, 아니면 '0'반환
		String notice = method.checkOn(request.getParameter("notice"));	
		String reply = method.checkOn(request.getParameter("reply"));
		String post = method.checkOn(request.getParameter("post"));
		String board = method.checkOn(request.getParameter("board"));
		String category = method.checkOn(request.getParameter("category"));
		String role = method.checkOn(request.getParameter("role"));
		
		String alist = role + category + board + post + reply + notice;
		
		// 기존 ma를 삭제함
		adminService.deleteMA(prevName);
		// role을 삭제하고 다시 insert함
		if(!adminService.deleteRole(prevName) || !adminService.insertRole(newName, alist)) {
			request.setAttribute("msg", "Role 변경에 실패했습니다.");
		}
		// ma를 insert함
		else if(mbList!=null && !adminService.insertMA(newName, mbList)) {
			request.setAttribute("msg", "MA 추가에 실패했습니다.");
		}
		else {
			request.setAttribute("msg", "역할을 변경했습니다.");
		}
		request.setAttribute("url", "auth/list");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		
		
	}

}
