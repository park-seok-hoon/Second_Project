package team5_servlet.kr.kh.team5.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import team5_servlet.kr.kh.team5.model.vo.MemberVO;
import team5_servlet.kr.kh.team5.utils.Methods;

@WebFilter({
	"/category/update", "/category/manager", "/category/insert", "/category/delete"
})
public class CategoryAuthFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private Methods mthds = new Methods();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		MemberVO user = (MemberVO)httpServletRequest.getSession().getAttribute("user");
		
		// 권한이 있는 사용자가 아닐 경우,
		if(!mthds.checkAuth(1, user.getA_list())) {
			request.setAttribute("msg", "카테고리 관리 권한이 없습니다.");
			request.setAttribute("url", "");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}