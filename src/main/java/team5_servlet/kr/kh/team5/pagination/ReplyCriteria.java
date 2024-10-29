package team5_servlet.kr.kh.team5.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyCriteria extends Criteria  {

	
	private int poNum;

	public ReplyCriteria(int page, int perPageNum, int poNum) {	
		super(page, perPageNum);
		this.poNum = poNum;
	}
}
