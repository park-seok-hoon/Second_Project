package team5_servlet.kr.kh.team5.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team5_servlet.kr.kh.team5.dao.AdminDAO;
import team5_servlet.kr.kh.team5.model.vo.AuthorityVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;

public class AdminServiceImp implements AdminService {
	private AdminDAO adminDao;
	
	public AdminServiceImp() {
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			adminDao = session.getMapper(AdminDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}


	@Override
	public ArrayList<AuthorityVO> getAuthList() {
		return adminDao.getAuthList();
	}


	@Override
	public int getAuthTotal() {
		return adminDao.getAutoTotal();
	}


	@Override
	public boolean insertRole(String name, String alist) {
		if(name == null) {
			System.out.println("null name: ");
			return false;
		}
		
		int intAlist = Integer.parseInt(alist);
		return adminDao.insertRole(name, intAlist);
	}


	@Override
	public boolean deleteRole(String name) {
		if(name == null) {
			System.out.println("null name");
			return false;
		}
		
		adminDao.deleteMA(name);	// MA 테이블 내용 삭제
		
		return adminDao.deleteRole(name);
	}


	@Override
	public AuthorityVO checkDupRole(String name) {
		if(name == null) {
			System.out.println("null name");
		}
		
		return adminDao.selectRoleByAname(name);
	}


	@Override
	public AuthorityVO selectAuthByAname(String name) {
		if(name == null) {
			System.out.println("null name");
		}
		return adminDao.selectRoleByAname(name);
	}


	@Override
	public ArrayList<MemberVO> getMemberList() {
		return adminDao.getMemberList();
		
	}


	@Override
	public boolean insertMA(String name, String [] list) {
			if(list.length == 0) {
				System.out.println("no MA");
				return true;
			}
			for(String tmp : list) {
				System.out.println(tmp);
				// 정상적으로 넘어옴
				if(!adminDao.insertMA(name, tmp)) {
					System.out.println("failed inserting MA");
					return false;
				}
			}
			return true;
	}


	@Override
	public boolean deleteMA(String prevName) {
		if(prevName == null) {
			System.out.println("null name: " + prevName);
			return false;
		}
		return adminDao.deleteMA(prevName);
	}


	@Override
	public List<String> selectMaByAname(String prevName) {
		if(prevName == null) {
			System.out.println("null name: " + prevName);
			return null;
		}
		return adminDao.selectMaByAname(prevName);
	}
	

}
