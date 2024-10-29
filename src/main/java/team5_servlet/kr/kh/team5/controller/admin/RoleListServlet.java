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
import team5_servlet.kr.kh.team5.utils.Methods;

@WebServlet("/auth/list")
public class RoleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();
	private Methods mthds = new Methods();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<AuthorityVO> list = adminService.getAuthList();
		int totalCount = adminService.getAuthTotal();
		
		request.setAttribute("list", list);
		request.setAttribute("count", totalCount);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/authlist.jsp").forward(request, response);
	}
	



}
