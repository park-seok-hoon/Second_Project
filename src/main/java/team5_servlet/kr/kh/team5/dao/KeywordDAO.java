package team5_servlet.kr.kh.team5.dao;

import org.apache.ibatis.annotations.Param;

import team5_servlet.kr.kh.team5.model.vo.KeywordVO;

public interface KeywordDAO {

	KeywordVO selectKey(@Param("id") String k_mb_id);

	boolean updateKey(@Param("keyword")KeywordVO keyword);

	boolean insertKey(@Param("keyword")KeywordVO keyword);

	KeywordVO getKey(@Param("id")String mb_id);

	

}