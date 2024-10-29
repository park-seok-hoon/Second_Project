package team5_servlet.kr.kh.team5.service;

import java.util.ArrayList;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface BoardService {

	int getTotalCount(Criteria cri, String id);

	ArrayList<BoardVO> getBoardList(Criteria cri, String id);
	
	ArrayList<BoardVO> getBoardByCategory(int num);

	boolean deleteBoard(int num, MemberVO user);

	boolean insertBoard(String boardName, ArrayList<BoardVO> list, int num, MemberVO user);

	ArrayList<BoardVO> getBoardList();

	boolean BoardUpdate(int bnum, String b_name, MemberVO user, int cnum);

	BoardVO getBoard(int num);

	ArrayList<PostVO> selectPostByBoard(int num);

	ArrayList<BoardVO> selectBoardByCagoryNumList(int cnum);
}
