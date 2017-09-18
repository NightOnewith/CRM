package net.yuanma.filter;

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

public class UserInfoFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		// 获取用户请求的URI
		String uri = req.getRequestURI();
		if (!uri.endsWith("login.jsp") && !uri.endsWith("LoginServlet")) {
			if (uri.endsWith("/") || uri.contains("Servlet")) {
				if (session.getAttribute("currentUser") == null) {
					res.sendRedirect(req.getContextPath() + "/login.jsp");
				} else {
					chain.doFilter(request, response);
				}
			} else if(uri.endsWith(".jsp") && !uri.endsWith("login.jsp") && !uri.endsWith("404.jsp")) {
				res.sendRedirect(req.getContextPath() + "/404.jsp");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(req, res);
			return;
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
