package team5_servlet.kr.kh.team5.controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.service.MemberService;
import team5_servlet.kr.kh.team5.service.MemberServiceImp;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;
import team5_servlet.kr.kh.team5.service.ReplyService;
import team5_servlet.kr.kh.team5.service.ReplyServiceImp;


@WebServlet("/deleteMember")
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImp();
    private PostService postService=new PostServiceImp();
    private ReplyService replyService=new ReplyServiceImp();
   
	public DeleteMemberServlet() {
    }
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/mypage/deleteMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
	
		if(user.getMb_id().equals(pw) && user.getMb_pw().equals(pw)) {
		
			boolean res3=replyService.deleteReplyById(id);
			boolean res2= postService.deletePost(id);
			boolean res = memberService.deleteMember(id);
			if(res==true) {
				System.out.println("삭제 성공");
				request.getSession().removeAttribute("user");
				response.sendRedirect(request.getContextPath() + "/");
				
			}
			else {
				//실패하면 회원가입 페이지 유지
				System.out.println("삭제 실패");
				doGet(request, response);
			}
		}
		else {
		
		}


	}
}
