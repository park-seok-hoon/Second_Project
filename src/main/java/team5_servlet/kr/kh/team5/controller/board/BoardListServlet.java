package team5_servlet.kr.kh.team5.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;


@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BoardService boardService = new BoardServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cnum = 0;
		try {
			cnum = Integer.parseInt(request.getParameter("categoryNum"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<BoardVO> list = boardService.getBoardByCategory(cnum);
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		request.setAttribute("user", user);

		JSONObject jobj = new JSONObject();
		
		jobj.put("boardList", list);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
		
	}
	

}
