package team5_servlet.kr.kh.team5.controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.ReplyService;
import team5_servlet.kr.kh.team5.service.ReplyServiceImp;

@WebServlet("/reply/delete")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyService replyService = new ReplyServiceImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num =0;
		//삭제할 댓글 번호
		try {
			num = Integer.parseInt(request.getParameter("num"));
		}catch(Exception e){
			e.printStackTrace();
		}
		//회원 정보 가져옴
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");	
		boolean res= replyService.deleteReply(num,user);
		
		response.getWriter().write(res?"ok":"");
	}

}
