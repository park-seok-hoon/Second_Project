package team5_servlet.kr.kh.team5.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.AuthorityVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;

public interface AdminDAO {

	ArrayList<AuthorityVO> getAuthList();

	int getAutoTotal();

	boolean insertRole(@Param("name")String name, @Param("alist")int alist);

	boolean deleteRole(@Param("a_name")String name);

	boolean deleteMA(@Param("a_name")String name);

	AuthorityVO selectRoleByAname(@Param("a_name")String name);

	ArrayList<MemberVO> getMemberList();

	boolean insertMA(@Param("a_name")String name, @Param("mb_id")String tmp);

	List<String> selectMaByAname(@Param("a_name")String prevName);

}
