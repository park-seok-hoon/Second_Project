package team5_servlet.kr.kh.team5.controller.category;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.service.CategoryService;
import team5_servlet.kr.kh.team5.service.CategoryServiceImp;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;

@WebServlet("/category/delete")
public class CategoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//입력받은 카테고리명을 가져옴
		int cnum=0;
		try {
			cnum = Integer.parseInt(request.getParameter("cnum"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//유저 정보를 가져옴
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		boolean res = categoryService.categoryDelete(cnum, user);
		//게시글 삭제를 위한 PostVO가져오기
		if(res) {
			request.setAttribute("url", "category/manager");
			request.setAttribute("msg", "삭제에성공했습니다.");
		}
		//실패하면
		else {
			request.setAttribute("url", "category/manager");
			request.setAttribute("msg", "삭제에실패했습니다.");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
