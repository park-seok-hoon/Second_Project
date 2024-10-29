package team5_servlet.kr.kh.team5.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team5_servlet.kr.kh.team5.dao.KeywordDAO;
import team5_servlet.kr.kh.team5.dao.MemberDAO;
import team5_servlet.kr.kh.team5.model.vo.KeywordVO;

public class KeywordServiceImp implements KeywordService {
	KeywordDAO keywordDao;
	
	public KeywordServiceImp() {
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			keywordDao = session.getMapper(KeywordDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
	@Override
	public boolean insertKey(KeywordVO keyword) {
		System.out.println(keywordDao.selectKey(keyword.getK_mb_id()));
		if(keywordDao.selectKey(keyword.getK_mb_id())==null) {
			return keywordDao.insertKey(keyword);
		}else {
			return keywordDao.updateKey(keyword);
		}
			
		
	}


	@Override
	public KeywordVO getKey(String mb_id) {
		
		return keywordDao.getKey(mb_id);
	}
	
}