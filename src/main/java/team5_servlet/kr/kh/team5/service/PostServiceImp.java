package team5_servlet.kr.kh.team5.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team5_servlet.kr.kh.team5.dao.BoardDAO;
import team5_servlet.kr.kh.team5.dao.PostDAO;
import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;
import team5_servlet.kr.kh.team5.utils.Methods;

public class PostServiceImp implements PostService{

	private PostDAO postDao;
	private ReplyService rplyService = new ReplyServiceImp();
	private Methods methods = new Methods();
	
	public PostServiceImp() {
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";

		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<PostVO> getSearchPostByBoard(int cnum, Criteria cri) {
		if(cri == null) {
			cri = new Criteria();
		}
		System.out.println("여기는 잘 들어옴 " + cri);
		return postDao.selectSerchPostByBoard(cnum, cri);
	}
	
	@Override
	public ArrayList<PostVO> getPostByBoard(int bnum) {
		
		return postDao.selectPostByBoard(bnum);
	}
	
	@Override
	public PostVO selectPostByPnum(int num) {
		if(num == -1) {
			// 게시글 번호를 전달받지 못했을 시
			System.out.println("error: no post");
			return null;
		}
		return postDao.selectPostByPnum(num);
	}

	@Override
	public boolean insertPost(PostVO post) {
		if(post == null || !methods.checkString(post.getP_title()) || !methods.checkString(post.getP_content())) {
			System.out.println("invalid post");
			return false;
		}
		return postDao.insertPost(post);
	}

	@Override
	public boolean deletePost(PostVO post, MemberVO user) {
		if(post == null) {
			System.out.println("null post");
			return false;
		}
		
		// 현재 로그인 정보와 게시글 아이디 정보가 다르고 삭제 권한이 없는 경우
		if(!user.getMb_id().equals(post.getP_mb_id()) && !methods.checkAuth(3, user.getA_list())) {
			System.out.println("not same writer or authorized");
			return false;
		}
    
		// 게시글에 달려있는 댓글 삭제
		rplyService.deleteReplyByPnum(post.getP_num());
		System.out.println("reply deleted");
		
		return postDao.deletePost(post);
	}

	@Override
	public PostVO getPostByPnum(int num) {
		return postDao.getPostByPnum(num);
	}

	@Override
	public boolean updatePost(PostVO post) {
		if(post == null || !methods.checkString(post.getP_title()) || !methods.checkString(post.getP_content())) {
			System.out.println("invalid post");
			return false;
		}
		return postDao.updatePost(post);
	}

	@Override
	public boolean increaseView(int p_num) {
		if(p_num == 0) {
			return false;
		}
		return postDao.increaseView(p_num);
	}

	@Override
	public boolean deletePost(String id) {
		if(!methods.checkString(id)) {
			return false;
		}

		return postDao.deletePostById(id);

	}

	@Override
	public String getCategory(int b_c_num, int b_num) {
		if(b_c_num == 0 || b_num == 0) {
			System.out.println("invalid b_c_num or b_num");
			System.out.println("b_c_num: "+b_c_num);
			System.out.println("b_num: "+b_num);
			return null;
		}
		return postDao.getCategory(b_c_num, b_num);
	}

  @Override
	public ArrayList<PostVO> getPostTitle(String id) {
		
		return postDao.selectPostById(id);
	}

	public ArrayList<PostVO> getPostByDesc() {
		return postDao.selectPostByCheck();
	}

	@Override
	public boolean deletePost(PostVO tmp) {
		return postDao.deletePost(tmp);
		
	}

	@Override
	public int getTotalCountByKey(Criteria cri) {
		System.out.println(cri);
		if(cri == null) {
			cri = new Criteria();
		}
		return postDao.selectTotalCountByKey(cri);
	}

	@Override
	public ArrayList<BoardVO> getPostList(Criteria cri) {
		if(cri == null) {
			cri = new Criteria();
		}
		
		return postDao.selectPostList(cri);
	}
	


}

