package team5_servlet.kr.kh.team5.controller.category;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.CategoryVO;
import team5_servlet.kr.kh.team5.service.CategoryService;
import team5_servlet.kr.kh.team5.service.CategoryServiceImp;


@WebServlet("/category/insert")
public class CategoryInsertServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/category/categoryinsert.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//카테고리 리스트를 가져옴
		ArrayList<CategoryVO> list = categoryService.getCategoryList();
		request.setAttribute("list", list);
		//입력받은 카테고리명을 가져옴
		String categoryName = request.getParameter("category");
		//서비스한테 입력받은 카테고리명을 추가하라고 시킴
		boolean res = categoryService.insertCategory(categoryName, list);
		//추가에 성공하면
		if(res) {
			request.setAttribute("url", "category/manager");
			request.setAttribute("msg", "추가에성공했습니다.");
		}
		//실패하면
		else {
			request.setAttribute("url", "category/insert");
			request.setAttribute("msg", "추가에실패했습니다.");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
