package team5_servlet.kr.kh.team5.controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.AuthorityVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;
import team5_servlet.kr.kh.team5.service.AdminService;
import team5_servlet.kr.kh.team5.service.AdminServiceImp;

@WebServlet("/auth/delete")
public class RoleDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name;
		
		try {
			name = request.getParameter("name");
		}catch (Exception e) {
			System.out.println("null name");
			name = "";
		}
		
		// 역할 삭제 실행
		if(adminService.deleteRole(name)) {
			request.setAttribute("msg", "역할을 삭제했습니다.");
		}else {
			request.setAttribute("msg", "역할 삭제에 실패했습니다.");
		}
		request.setAttribute("url", "/auth/list");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
	



}
