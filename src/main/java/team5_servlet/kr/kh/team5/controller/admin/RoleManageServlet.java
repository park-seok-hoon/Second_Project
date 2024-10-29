package team5_servlet.kr.kh.team5.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.AuthorityVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.AdminService;
import team5_servlet.kr.kh.team5.service.AdminServiceImp;
import team5_servlet.kr.kh.team5.utils.Methods;

@WebServlet("/auth/manage")
public class RoleManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Methods method = new Methods();
	private AdminService adminService = new AdminServiceImp();
	private Methods mthds = new Methods();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prevName = request.getParameter("prevName");
		
		if(prevName!=null) {
			String state = request.getParameter("state");
			if(state.equals("member")) {
				request.setAttribute("member", "edit");
			}
			AuthorityVO role = adminService.selectAuthByAname(prevName);
			request.setAttribute("role", role);
			List<String> maList = adminService.selectMaByAname(prevName);
			System.out.println(maList);
			request.setAttribute("maList", maList);
		}
		
		ArrayList<MemberVO> list = adminService.getMemberList();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/authmanage.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 역할 이름 받아오기
		String name = request.getParameter("name");
		
		// jsp에서 checkbox의 value를 mb_id로 해서 전달 받기.
		// values 이용!
		String [] list = request.getParameterValues("auth-check");
		
		// getParameter를 하면 'on' 또는 null 반환
		// 'on'일 경우 '1' 반환, 아니면 '0'반환
		String notice = method.checkOn(request.getParameter("notice"));	
		String reply = method.checkOn(request.getParameter("reply"));
		String post = method.checkOn(request.getParameter("post"));
		String board = method.checkOn(request.getParameter("board"));
		String category = method.checkOn(request.getParameter("category"));
		String role = method.checkOn(request.getParameter("role"));
		
		String alist = role + category + board + post + reply + notice;
		
		if(adminService.insertRole(name, alist)) {
			if(adminService.insertMA(name, list)) {
				request.setAttribute("msg", "추가에 성공했습니다.");
				request.setAttribute("url", "auth/list");

			}else {
				request.setAttribute("msg", "MA 추가에 실패했습니다.");
				request.setAttribute("url", "auth/list");
			}
		}else {
			request.setAttribute("msg", "Auth 추가에 실패했습니다.");
			request.setAttribute("url", "auth/list");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
