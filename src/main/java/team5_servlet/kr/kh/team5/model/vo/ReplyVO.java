package team5_servlet.kr.kh.team5.model.vo;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyVO {
	
	private int r_num;	//댓글 번호
	private String r_content; 
	private Date r_date;
	private String r_mb_id; 
	private int r_p_num;	//게시글 번호
	
	public ReplyVO(int r_p_num, String content, String mb_id) {
		
		this.r_p_num=r_p_num;
		this.r_content=content;
		this.r_date = new Date();
		this.r_mb_id=mb_id;
		this.r_date=new Date();
	}

	public ReplyVO(String content, String mb_id) {
		this.r_content=content;
		this.r_mb_id=mb_id;
	}
	
	
}