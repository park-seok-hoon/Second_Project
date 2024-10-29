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

public class BoardServiceImp implements BoardService{
	
	private BoardDAO boardDao;
	private PostDAO postDao;
	private Methods mt = new Methods();
	private PostService postService = new PostServiceImp();

	public BoardServiceImp() {
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			boardDao = session.getMapper(BoardDAO.class);
			postDao = session.getMapper(PostDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getTotalCount(Criteria cri,String id) {
		if(cri == null) {
			cri = new Criteria();
		}
		return boardDao.selectTotalCount(cri,id);
	}

	@Override
	public ArrayList<BoardVO> getBoardList(Criteria cri,String id) {
		//현재 페이지정보 null 처리 
		if(cri == null) {
			cri = new Criteria();
		}
		
		return boardDao.selectBoardList(cri,id);
	}
	
	@Override
	public ArrayList<BoardVO> getBoardByCategory(int num) {
		return boardDao.selectBoardByCategory(num);
	}

	@Override
	public boolean deleteBoard(int num, MemberVO user) {
		if(!mt.checkAuth(2, user.getA_list())) {
			return false;
		}

		//게시판에 소속된 게시글 삭제
		ArrayList<PostVO> postList = selectPostByBoard(num);
		for(PostVO tmp : postList) {			
			postService.deletePost(tmp);
		}
		System.out.println("postList 반복문 후까진 옴");
		boolean res = boardDao.deleteBoard(num);
		return res;
	}

	@Override
	public boolean insertBoard(String boardName, ArrayList<BoardVO> list, int num, MemberVO user) {
		ArrayList<BoardVO> boardList = boardDao.getBoardList();
		if(!mt.checkString(boardName)) {
			System.out.println("not String title: "+boardName);
			return false;
		}
		for(BoardVO tmp : boardList) {
			if(tmp.getB_name().equals(boardName)) {
				System.out.println("duplication Board : "+boardName);
				return false;
			}
		}
		if(!user.getMb_nickname().equals("admin")) {
			System.out.println("not admin");
			return false;
		}
		
		return boardDao.insertBoard(boardName, num);
	}
	@Override
	public ArrayList<BoardVO> getBoardList() {
		return boardDao.getBoardList();

	}

	@Override
	public boolean BoardUpdate(int bnum, String b_name, MemberVO user, int cnum) {
		if(!mt.checkString(b_name)) {
			return false;
		}
		ArrayList<BoardVO> board = boardDao.selectBoardByCategory(cnum);
		for(BoardVO tmp : board) {
			if(tmp.getB_name().equals(b_name)) {
				return false;
			}
		}
		return boardDao.updateBoard(bnum, b_name, cnum);
	}
	
	public ArrayList<BoardVO> getBoardListByCategory(int cnum) {
		
		return boardDao.selectBoardByCategory(cnum);
	}

	@Override
	public BoardVO getBoard(int num) {
		return boardDao.selectBoard(num);
	}

	@Override
	public ArrayList<PostVO> selectPostByBoard(int num) {
		return boardDao.selectPostByBoardList(num);
	}

	@Override
	public ArrayList<BoardVO> selectBoardByCagoryNumList(int cnum) {
		return boardDao.selectBoardByCagoryNumList(cnum);
	}
}
