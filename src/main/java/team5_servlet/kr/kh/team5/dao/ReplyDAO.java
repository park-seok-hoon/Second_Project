package team5_servlet.kr.kh.team5.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface ReplyDAO {

	// 내가 댓글 쓴 글?
	ArrayList<ReplyVO> selectMyReplyList(@Param("cri")Criteria cri, @Param("id")String id);

	int selectTotalCount(@Param("cri")Criteria cri,@Param("id") String id);

	boolean deleteReplyByPnum(@Param("p_num")int num);	//댓글 삭제


	boolean insertReply(@Param("reply")ReplyVO replyVO);

	boolean deleteReplyById(@Param("id")String id);


	ArrayList<ReplyVO> selectReplyList(@Param("cri")Criteria cri);

	int selectTotalCountReply(@Param("cri")Criteria cri);

	ReplyVO selectReply(@Param("r_num")int num);

	boolean updateReply(@Param("rp")ReplyVO reply);

	boolean deleteReplyByRnum(@Param("r_num")int num);

}
