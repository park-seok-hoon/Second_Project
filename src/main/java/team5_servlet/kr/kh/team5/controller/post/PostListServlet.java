package team5_servlet.kr.kh.team5.controller.post;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;
import team5_servlet.kr.kh.team5.pagination.PageMaker;
import team5_servlet.kr.kh.team5.service.PostService;
import team5_servlet.kr.kh.team5.service.PostServiceImp;

@WebServlet("/post/list")
public class PostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private PostService postService = new PostServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = 0;
		//검색어와 검색타입을 가져옴 현재페이지 정보도 가져옴
		try {
			bnum = Integer.parseInt(request.getParameter("boardNum"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		ArrayList<PostVO> postList = postService.getPostByBoard(bnum);
		ArrayList<String> postDateList = dateList(postList);
		JSONObject jobj = new JSONObject();
		jobj.put("postList", postList);
		jobj.put("postDateList", postDateList);
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cnum = 0;
		//검색어와 검색타입을 가져옴 현재페이지 정보도 가져옴
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		try {
			cnum = Integer.parseInt(request.getParameter("caNum"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Criteria cri = new Criteria(1, 2, type, search);
		ArrayList<PostVO> searchpostList = postService.getSearchPostByBoard(cnum, cri);
		ArrayList<String> postDateList = dateList(searchpostList);
		JSONObject jobj = new JSONObject();
		jobj.put("postDateList", postDateList);
		jobj.put("searchpostList", searchpostList);

		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);

		
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
