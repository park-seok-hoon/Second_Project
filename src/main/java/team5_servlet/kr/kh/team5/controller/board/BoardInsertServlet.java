package team5_servlet.kr.kh.team5.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;

@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardServiceImp();
    
	private int num = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			num = Integer.parseInt(request.getParameter("num"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/views/board/baordinsert.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//카테고리 리스트를 가져옴
		System.out.println("board post");
		System.out.println(num);
		ArrayList<BoardVO> list = boardService.getBoardByCategory(num);
		request.setAttribute("list", list);
		System.out.println(list);
		//입력받은 카테고리명을 가져옴
		String boardName = request.getParameter("board");
		//로그인한 유저 정보 받아오기
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		//서비스한테 입력받은 카테고리명을 추가하라고 시킴
		boolean res = boardService.insertBoard(boardName, list, num, user);
		//추가에 성공하면
		if(res) {

			request.setAttribute("msg", "추가에성공했습니다.");
			request.setAttribute("url", "board/manager");
		}
		//실패하면
		else {
			request.setAttribute("msg", "추가에실패했습니다."+num);
			request.setAttribute("url", "board/manager");

		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}


}
