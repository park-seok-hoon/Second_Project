package team5_servlet.kr.kh.team5.utils;

import java.util.ArrayList;

public class Methods {
	
	public boolean checkString(String str) {
		if(str.length() == 0 || str == null) {
			System.out.println("invalid String");
			System.out.println("length: "+str.length());
			System.out.println("str: "+str);
			return false;
		}
		return true;
	}
	
	// 체크박스가 체크되어있는지 여부를 확인하는 메서드
	public String checkOn(String str) {
		if(str==null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	// 권한 부여 여부를 확인하는 메서드
	public boolean checkAuth(int idx, ArrayList<String> alist) {
		for(String auth : alist) {
			if(auth.charAt(idx) == '1')
				return true;	// 갖고있는 역할 중 하나라도 권한이 있는 역할이 있다면
		}
		// 권한 없음
		return false;
	}
}
