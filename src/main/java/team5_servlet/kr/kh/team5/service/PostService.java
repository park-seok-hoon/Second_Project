package team5_servlet.kr.kh.team5.service;


import java.util.ArrayList;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface PostService {

	ArrayList<PostVO> getPostByBoard(int bnum);

	ArrayList<PostVO> getSearchPostByBoard(int cnum, Criteria cri);

	PostVO selectPostByPnum(int num);

	boolean insertPost(PostVO post);
  
	boolean deletePost(PostVO post, MemberVO user);

	PostVO getPostByPnum(int num);

	boolean updatePost(PostVO post);

	boolean increaseView(int p_num);

	boolean deletePost(String id);

	ArrayList<PostVO> getPostByDesc();

	String getCategory(int b_c_num, int b_num);

	ArrayList<PostVO> getPostTitle(String id);

	boolean deletePost(PostVO tmp);

	int getTotalCountByKey(Criteria cri);

	ArrayList<BoardVO> getPostList(Criteria cri);

}

