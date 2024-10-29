package team5_servlet.kr.kh.team5.service;

import java.util.ArrayList;
import java.util.List;

import team5_servlet.kr.kh.team5.model.vo.AuthorityVO;
import team5_servlet.kr.kh.team5.model.vo.BoardVO;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.PostVO;
import team5_servlet.kr.kh.team5.pagination.Criteria;

public interface AdminService {

	ArrayList<AuthorityVO> getAuthList();

	int getAuthTotal();

	boolean deleteRole(String name);

	AuthorityVO checkDupRole(String name);

	AuthorityVO selectAuthByAname(String name);

	boolean insertRole(String name, String alist);

	ArrayList<MemberVO> getMemberList();

	boolean insertMA(String name, String[] list);

	boolean deleteMA(String prevName);

	List<String> selectMaByAname(String prevName);

}
