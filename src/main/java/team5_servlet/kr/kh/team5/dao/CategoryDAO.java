package team5_servlet.kr.kh.team5.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.BoardVO;
import team5_servlet.kr.kh.team5.model.vo.CategoryVO;

public interface CategoryDAO {

	ArrayList<CategoryVO> selectCategoryList();

	boolean insertCategory(@Param("c_name")String categoryName);

	boolean deleteCategory(@Param("c_num")int num);

	CategoryVO selectCategory(@Param("c_num")int num);

	boolean updateCategory(@Param("c_num")int num, @Param("c_name")String c_name);

}
