package team5_servlet.kr.kh.team5.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeywordVO {
	private int k_num ; 
	private String k_content; 
	private String k_mb_id;
	public KeywordVO(String key, String id) {
		this.k_content=key;
		this.k_mb_id=id;
	}
	
}