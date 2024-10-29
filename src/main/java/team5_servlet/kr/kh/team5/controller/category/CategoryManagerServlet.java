package team5_servlet.kr.kh.team5.controller.category;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.CategoryVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;
import team5_servlet.kr.kh.team5.service.CategoryService;
import team5_servlet.kr.kh.team5.service.CategoryServiceImp;

@WebServlet("/category/manager")
public class CategoryManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CategoryService categoryService = new CategoryServiceImp();
	BoardService boardService = new BoardServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//카테고리 리스트를 가져옴
		ArrayList<CategoryVO> list = categoryService.getCategoryList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/categorymanager.jsp").forward(request, response);
	}
}
