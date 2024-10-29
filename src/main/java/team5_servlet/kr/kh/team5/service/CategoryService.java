package team5_servlet.kr.kh.team5.service;

import java.util.ArrayList;

import team5_servlet.kr.kh.team5.model.vo.CategoryVO;
import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.model.vo.BoardVO;

public interface CategoryService {

	ArrayList<CategoryVO> getCategoryList();

	boolean insertCategory(String categoryName, ArrayList<CategoryVO> list);

	boolean categoryDelete(int cnum, MemberVO user);

	CategoryVO getCategory(int num);

	boolean CategoryUpdate(int num, String c_name);

}
