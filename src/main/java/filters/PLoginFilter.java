package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PLoginFilter implements Filter{

	private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		this.config = config;
	}

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		Object account = session.getAttribute("account");
		if (account == null) {
//			session.setAttribute("location", req.getRequestURI());
			session.setAttribute("location", req.getServletPath());
			System.out.println(req.getContextPath());
			res.sendRedirect(req.getContextPath() + "/loginP");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

}
