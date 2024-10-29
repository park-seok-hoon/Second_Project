package team5_servlet.kr.kh.team5.service;

import team5_servlet.kr.kh.team5.model.vo.KeywordVO;

public interface KeywordService {

	boolean insertKey(KeywordVO keyword);

	KeywordVO getKey(String mb_id);
	
}