package team5_servlet.kr.kh.team5.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.CategoryVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;
import team5_servlet.kr.kh.team5.service.CategoryService;
import team5_servlet.kr.kh.team5.service.CategoryServiceImp;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService boardService = new BoardServiceImp();	
	private int bnum = 0, cnum= 0;
	private CategoryService categoryService = new CategoryServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면에서 보낸 번호 받기
		try {
			bnum=Integer.parseInt(request.getParameter("bnum"));
			cnum=Integer.parseInt(request.getParameter("cnum"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<CategoryVO> list = categoryService.getCategoryList();
		request.setAttribute("list", list);
		//기존 카테고리를 보여줄 sql문 작성을 위한 작업
		BoardVO ori_catgory = boardService.getBoard(bnum);
		request.setAttribute("ori_category", ori_catgory);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/board/boardupdate.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인한 유저 정보 가져오기
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		//화면에서 보낸 업데이트할 카테고리 번호를 가져옴
		//화면에서 보낸 새로운 카테고리면 받기		
		String b_name = request.getParameter("new_board");
		int c_num = Integer.parseInt(request.getParameter("category"));
		//새로운 카테고리명으로 이름 바꾸기
		boolean res = boardService.BoardUpdate(bnum, b_name, user, c_num);
		//수정에 성공하면
		if(res) {
			request.setAttribute("url", "board/manager");
			request.setAttribute("msg", "수정에성공했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
		//실패하면
		else {
			request.setAttribute("url", "board/manager");
			request.setAttribute("msg", "수정에실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
	}

}
