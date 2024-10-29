package team5_servlet.kr.kh.team5.controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.service.ReplyService;
import team5_servlet.kr.kh.team5.service.ReplyServiceImp;

@WebServlet("/reply/update")
public class ReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyService replyService = new ReplyServiceImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면에서 보낸 댓글 내용과 댓글 번호를 가져옴
		int num =0;
		try {
			num = Integer.parseInt(request.getParameter("num"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		String content = request.getParameter("content");
		System.out.println(content);
		//회원 정보를 가져옴
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		//댓글 객체를 댓글 번호, 내용, 작성자를 이용하여 생성
		
		ReplyVO reply = new ReplyVO(content,user.getMb_id());
		reply.setR_num(num);
		boolean res = replyService.updateReply(reply);
		response.getWriter().write(res?"ok":"");
	
	}

}
