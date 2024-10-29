package team5_servlet.kr.kh.team5.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;

public interface MemberDAO {

	boolean insertMember(@Param("member") MemberVO member);

	MemberVO selectMember(@Param("mb_id")String id);

	boolean deleteMember(@Param("id")String id);

	boolean changeMember(@Param("member")MemberVO member);

	MemberVO findPw(@Param("user")MemberVO user); //비번 찾기

	MemberVO findId(@Param("user")MemberVO user); //아이디 찾기

	ArrayList<String> selectAlistById(@Param("id")String id);



}
