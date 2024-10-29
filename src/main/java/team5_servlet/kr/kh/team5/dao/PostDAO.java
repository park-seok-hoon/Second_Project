package team5_servlet.kr.kh.team5.dao;


import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface PostDAO {

	ArrayList<PostVO> selectPostByBoard(@Param("num")int bnum);

	PostVO selectPostByPnum(@Param("num")int num);

	boolean insertPost(@Param("post")PostVO post);

	boolean deletePost(@Param("post")PostVO post);

	PostVO getPostByPnum(@Param("num")int num);

	boolean updatePost(@Param("post")PostVO post);

	boolean increaseView(@Param("num")int p_num);

	boolean deletePostById(@Param("id")String id);

	ArrayList<PostVO> selectPostById(@Param("id")String id);

	ArrayList<PostVO> selectPostByCheck();

	ArrayList<PostVO> selectSerchPostByBoard(@Param("cnum")int cnum,@Param("cri") Criteria cri);

	String getCategory(@Param("b_c_num")int b_c_num, @Param("b_num")int b_num);

	int selectTotalCount(@Param("cri")Criteria cri);

	int selectTotalCountByKey(@Param("cri")Criteria cri);

	ArrayList<BoardVO> selectPostList(@Param("cri")Criteria cri);


}

