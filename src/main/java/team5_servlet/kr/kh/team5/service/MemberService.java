package team5_servlet.kr.kh.team5.service;

import java.util.ArrayList;

import team5_servlet.kr.kh.team5.model.dto.LoginDTO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;

public interface MemberService {

	boolean signup(MemberVO member);

	MemberVO getMember(LoginDTO loginDto);
	
	boolean deleteMember(String id);
	
	boolean changeMember(MemberVO member);
	
	String checkId(String id);

	MemberVO findPw(MemberVO user);	//비번 찾기

	MemberVO findId(MemberVO user); //아이디 찾기

	ArrayList<String> selectAlistById(String id);

}
