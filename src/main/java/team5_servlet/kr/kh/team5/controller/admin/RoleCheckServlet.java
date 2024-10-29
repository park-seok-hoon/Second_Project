package team5_servlet.kr.kh.team5.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.AuthorityVO;
import team5_servlet.kr.kh.team5.service.AdminService;
import team5_servlet.kr.kh.team5.service.AdminServiceImp;

@WebServlet("/role/check")
public class RoleCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//화면에서 보낸 댓글 내용 가져옴
		String name = request.getParameter("name");
		try {
			name = request.getParameter("name");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		AuthorityVO res = adminService.checkDupRole(name);
		
		response.getWriter().write(res!=null?"ok": "");
	}
}


