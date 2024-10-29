package team5_servlet.kr.kh.team5.model.vo;


import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //기본 생성자(매개변수가 없는 생성자)
public class MemberVO {

	private String mb_id; 
	private String mb_pw; 
	private String mb_nickname;
	private String mb_mail; 
	private String mb_phone; 
	private String mb_name;
	
	/*	a_list String의 n번째 번지 자리 별 의미:
	 * 
	 *  0 : 역할 관리 권한
	 *  1 : 카테고리 관리 권한
	 *  2 : 게시판 관리 권한
	 *  3 : 게시글 관리 권한
	 *  4 : 댓글 관리 권한
	 *  5 : 공지사항 작성 권한
	 *  
	 *  = 권한 추가가 필요할 경우 DB상에서 모든 값에 *10을 한 다음, 
	 *    가장 끝 번지 값을 새로운 권한 부여 여부로 설정하면 됨.
	 * */
	private ArrayList<String> a_list;
	
	
	public MemberVO(String id, String pw, String nickname, String mail, String phone, String name) {
		this.mb_id = id;
		this.mb_pw = pw;
		this.mb_nickname = nickname;
		this.mb_mail = mail;
		this.mb_phone = phone;
		this.mb_name = name;
	}


	public MemberVO(String id, String email) {
		this.mb_mail=email;
		this.mb_id=id;
	}


	public MemberVO(String name, String email, String phonenum) {
		this.mb_name=name;
		this.mb_mail=email;
		this.mb_phone=phonenum;
		
	}
	
	
	
}