package team5_servlet.kr.kh.team5.controller.post;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;

@WebServlet("/post/allpost")

public class AllPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private PostService postService = new PostServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<PostVO> list = postService.getPostByDesc();
		ArrayList<String> Datelist = dateList(list);
		request.setAttribute("postlist", list);
		request.setAttribute("Datelist", Datelist);
		request.getRequestDispatcher("/WEB-INF/views/post/postall.jsp").forward(request, response);
	
	}
	
	protected ArrayList<String> dateList(ArrayList<PostVO> datePostVoList) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
		ArrayList<String> dateList = new ArrayList<String>();
		for(PostVO tmp : datePostVoList) {
			dateList.add(sdf2.format(tmp.getP_date()));
		}
		return dateList;
	}
}
