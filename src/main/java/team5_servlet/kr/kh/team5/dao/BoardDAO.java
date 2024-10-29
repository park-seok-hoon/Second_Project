package team5_servlet.kr.kh.team5.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;

import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface BoardDAO {

	int selectTotalCount(@Param("cri")Criteria cri,@Param("id") String id);

	ArrayList<BoardVO> selectBoardList(@Param("cri")Criteria cri,@Param("id") String id);
	
	ArrayList<BoardVO> selectBoardByCategory(@Param("num")int cnum);

	boolean deleteBoard(@Param("b_num")int num);

	boolean insertBoard(@Param("b_name")String boardName, @Param("b_c_num")int num);

	ArrayList<BoardVO> getBoardList();

	boolean updateBoard(@Param("b_num")int bnum,@Param("b_name") String b_name,@Param("b_c_num") int cnum);

	BoardVO selectBoard(@Param("b_num")int num);

	ArrayList<PostVO> selectPostByBoardList(@Param("b_num")int num);

	ArrayList<BoardVO> selectBoardByCagoryNumList(@Param("c_num")int cnum);


}
