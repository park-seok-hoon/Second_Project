package team5_servlet.kr.kh.team5.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team5_servlet.kr.kh.team5.dao.MemberDAO;
import team5_servlet.kr.kh.team5.model.dto.LoginDTO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;

public class MemberServiceImp implements MemberService {
	
	private MemberDAO memberDao;

	public MemberServiceImp() {
		String resource = "team5_servlet/kr/kh/team5/config/config.xml";
		
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sessionFactory.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public boolean signup(MemberVO member) {
		if(	member == null
			|| !checkString(member.getMb_id())
			|| !checkString(member.getMb_pw())
			|| !checkString(member.getMb_nickname())
			|| !checkString(member.getMb_mail())
			|| !checkString(member.getMb_phone())
			|| !checkString(member.getMb_name()))
		{
			return false;
	
		}
		
		try {
			boolean res = memberDao.insertMember(member);
			return res;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
		}
	@Override
	public MemberVO getMember(LoginDTO loginDto) {
		if(loginDto == null) {
			return null;
		}
		
		//다오에게 아이디를 주면서 회원 정보를 가져오라고 시킴
		MemberVO user = memberDao.selectMember(loginDto.getId());
		//가져온 회원정보가 없으면 null을 리턴
		if(user == null) {
			return null;
		}
		//있으면 DB 정보의 비번과 사용자가 입력한 비번이 같으면 DB정보를, 다르면 null를 리턴 
		//비번은 회원가입시 암호화가 되어 관리되기 때문에 DB에서 직접 비교할 수 없다
		//그래서 비번 확인을 서버쪽에서 해야 함
		if(user.getMb_pw().equals(loginDto.getPw())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean deleteMember(String id) {
		
		return memberDao.deleteMember(id);
	}

	

	@Override
	public boolean changeMember(MemberVO member) {
		if(member.getMb_id()==null||
			member.getMb_mail()==null||
			member.getMb_name()==null||
			member.getMb_phone()==null||
			member.getMb_pw()==null) {
				System.out.println(member.getMb_id());
				System.out.println(member.getMb_mail());
				System.out.println(member.getMb_name());
				System.out.println(member.getMb_phone());
				System.out.println(member.getMb_pw());
				
				return false;
			}
		return memberDao.changeMember(member);
	}
	
	public boolean checkString(String str) {
		if(str.length()==0 || str == null) {
			return false;
		}
		return true;
	}

	@Override
	public String checkId(String id) {
		MemberVO member = memberDao.selectMember(id);
		return member == null ? "1" : "";
	}

	@Override
	public MemberVO findPw(MemberVO user) {
		if(user.getMb_mail() == null ||
			user.getMb_id() == null) {
			return null;
		}
		
		return memberDao.findPw(user);
	}

	@Override
	public MemberVO findId(MemberVO user) {
		if( user.getMb_name() == null  ||
			user.getMb_mail() == null ||
			user.getMb_phone() == null) {
		return null;
		}
		return memberDao.findId(user);
	}

	public ArrayList<String> selectAlistById(String id) {
		return memberDao.selectAlistById(id);

	}
	
}
