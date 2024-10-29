package team5_servlet.kr.kh.team5.service;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team5_servlet.kr.kh.team5.dao.ReplyDAO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.ReplyVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;
import team5_servlet.kr.kh.team5.utils.Methods;

public class ReplyServiceImp implements ReplyService {
	private ReplyDAO replyDao;
	private Methods mthds = new Methods();
	
	public ReplyServiceImp() {
		
	
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			replyDao = session.getMapper(ReplyDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public ArrayList<ReplyVO> getReplyList(Criteria cri, String id) {
		if(cri == null) {
			cri = new Criteria();
		}
		
		return replyDao.selectMyReplyList(cri,id);
	}

	@Override
	public int getTotalCount(Criteria cri,String id) {
		if(cri == null) {
			cri = new Criteria();
		}
		return replyDao.selectTotalCount(cri,id);
	}

	@Override
	public boolean deleteReplyByPnum(int p_num) {
		return replyDao.deleteReplyByPnum(p_num);
	}

	@Override
	public boolean insertReply(ReplyVO replyVO) {
		if(replyVO == null ||
				!checkString(replyVO.getR_content())) {
			System.out.println("null reply");
			return false;
		}
		return replyDao.insertReply(replyVO);
		}

	
	public boolean checkString(String str) {
		if(str == null || str.length() == 0) {
			return false;
		}
		return true;
		
	}

	@Override
	public ArrayList<ReplyVO> getReplyList(Criteria cri) {
		if(cri == null) {
			cri = new Criteria(1,2);
		}
		
		return replyDao.selectReplyList(cri);
	}

	@Override
	public int getTotalCountReply(Criteria cri) {
		if(cri == null) {
			return 0;
		}
		return replyDao.selectTotalCountReply(cri);
	}

	@Override
	public boolean deleteReply(int num, MemberVO user) {
		if(user == null) {
			return false;
		}
		//댓글 번호와 일치하는 댓글을 가져옴
		ReplyVO reply = replyDao.selectReply(num);
		
		// reply가 null이거나, 게시글의 작성자가 아니면서 게시글 관리 권한이 없을 경우
		if(reply == null ||
			(!reply.getR_mb_id().equals(user.getMb_id()))
			&& !mthds.checkAuth(4, user.getA_list())
			){	
			
			System.out.println("null userlogin or reply");
			System.out.println(num);
			System.out.println(user);
			return false;
		}
		
		//유저의 아이디가 댓글 아이디와 로그인된 아이디와 일치하면
		return replyDao.deleteReplyByRnum(num);
	}

	@Override
	public boolean updateReply(ReplyVO reply) {
		//댓글이 null이거나 댓글의 내용과 댓글의 아이디가 null이면 false 반환
		if(reply == null ||
			!checkString(reply.getR_content()) ||
			!checkString(reply.getR_mb_id())) {
			//System.out.println("reply null or reply 내용 id가 null");
			//System.out.println(reply.getR_content()+reply.getR_mb_id()+ reply);
			return false;
		}

		ReplyVO dbReply = replyDao.selectReply(reply.getR_num());
		
		if(dbReply == null ||
				!dbReply.getR_mb_id().equals(reply.getR_mb_id())) {
			//System.out.println(dbReply.getR_num()+"번호 dbReply가 null" + "현재 로그인 아이디와 댓글 아이디가 일치하지 않음");
			return false;
		}
		
		return replyDao.updateReply(reply);
	
	}

	@Override
	public boolean deleteReplyById(String id) {
		return replyDao.deleteReplyById(id);
	}

}
