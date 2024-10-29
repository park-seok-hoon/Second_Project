package team5_servlet.kr.kh.team5.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain)
			throws IOException, ServletException {
		//화면에서 서버로 전송할 때
		request.setCharacterEncoding("UTF-8");
		//서버에서 화면으로 전달할 때
		response.setCharacterEncoding("UTF-8");
		//필터가 여러개인 경우 다음 필터에 적용하기 위한 작업
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}