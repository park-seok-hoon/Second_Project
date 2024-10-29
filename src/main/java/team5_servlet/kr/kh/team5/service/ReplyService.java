package team5_servlet.kr.kh.team5.service;

import java.util.ArrayList;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface ReplyService {
	ArrayList<ReplyVO> getReplyList(Criteria cri, String id);

	int getTotalCount(Criteria cri, String id);

	boolean deleteReplyByPnum(int p_num);


	boolean insertReply(ReplyVO replyVO);	//댓글 추가

	ArrayList<ReplyVO> getReplyList(Criteria cri);

	int getTotalCountReply(Criteria cri);

	boolean deleteReply(int num, MemberVO user);

	boolean updateReply(ReplyVO reply);

	boolean deleteReplyById(String id);


}

