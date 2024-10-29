package team5_servlet.kr.kh.team5.controller.reply;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.pagination.PageMaker;
import team5_servlet.kr.kh.team5.pagination.ReplyCriteria;
import team5_servlet.kr.kh.team5.service.ReplyService;
import team5_servlet.kr.kh.team5.service.ReplyServiceImp;

@WebServlet("/reply/list")
public class ReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReplyService replyService = new ReplyServiceImp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page=1;
		int pNum=0;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
			pNum = Integer.parseInt(request.getParameter("pNum"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		//한 페이지에 댓글 2개씩 보여줌
		ReplyCriteria cri = new ReplyCriteria(page, 2, pNum);
		
		//현재 페이지에 맞는 댓글을 가져오라고 시킴
		ArrayList<ReplyVO> list = replyService.getReplyList(cri);
		
		int totalCount = replyService.getTotalCountReply(cri);
		
		PageMaker pm = new PageMaker(5, cri, totalCount);
		
		JSONObject jobj = new JSONObject();
		
		ObjectMapper om = new ObjectMapper();
		String pmStr = "";
		
		try {
			pmStr = om.writeValueAsString(pm);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//문자열을 객체로 변환해주는 작업
		//list를 문자열로 넣어주는 작업
		jobj.put("list", list);
		jobj.put("pm", pmStr);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
		
	}

}
