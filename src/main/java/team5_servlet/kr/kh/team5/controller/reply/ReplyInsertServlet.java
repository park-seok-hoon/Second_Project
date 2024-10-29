package team5_servlet.kr.kh.team5.controller.reply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.service.BoardService;
import team5_servlet.kr.kh.team5.service.BoardServiceImp;
import team5_servlet.kr.kh.team5.service.ReplyService;
import team5_servlet.kr.kh.team5.service.ReplyServiceImp;

@WebServlet("/reply/insert")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService replyService = new ReplyServiceImp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//화면에서 보낸 댓글 내용 가져옴
		String content = request.getParameter("content");
		int num=0;
		try {
			num=Integer.parseInt(request.getParameter("num"));	//화면에서 게시글 번호를 가져옴
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//댓글 작성자를 가져옴 =>회원 정보를 가져옴
		MemberVO user = (MemberVO) request.getSession().getAttribute("user");
		
		//댓글 등록	게시글 번호와 유저 아이디를 담은 생성자
		ReplyVO replyVO = new ReplyVO(num,content,user.getMb_id());
		boolean res = replyService.insertReply(replyVO);
		
		response.getWriter().write(res?"ok": "");
	}

}
