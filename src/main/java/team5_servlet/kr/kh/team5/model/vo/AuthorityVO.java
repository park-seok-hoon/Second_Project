package team5_servlet.kr.kh.team5.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorityVO {
	private String a_name;
	private String a_list;
	private int a_count;
	private String[] ma;

	public AuthorityVO(String a_name) {
		this.a_name = a_name;
	}
	
}
