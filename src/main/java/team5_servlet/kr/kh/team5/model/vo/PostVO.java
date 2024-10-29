package team5_servlet.kr.kh.team5.model.vo;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	private int p_num;
	private String p_title;
	private String p_content;
	private Date p_date;
	private int p_view;
	private String p_mb_id; 
	private int p_b_num;
	
	private String board;
	private String category;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public PostVO(String title, String content, int bo_num, String writer) {
		this.p_title = title;
		this.p_content = content;
		this.p_b_num = bo_num;
		this.p_mb_id = writer;
		this.p_date = new Date(); // 현재시간을 formatter 형태로 변경하여 저장
	}

	public PostVO(int num, String title, String content, int bo_num, String writer) {
		this.p_num = num;
		this.p_title = title;
		this.p_content = content;
		this.p_b_num = bo_num;
		this.p_mb_id = writer;
	}
}
