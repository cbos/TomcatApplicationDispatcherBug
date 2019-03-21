package org.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CrossContextRedirectFilter implements Filter {

	private String contextPath;

	@Override
	public void init(FilterConfig filterConfig){
		contextPath = filterConfig.getServletContext().getContextPath();
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		ServletContext crossContext = request.getServletContext().getContext(contextPath);
		if (crossContext == null) {
			throw new IllegalStateException("crossContext should not be null");
		}

		String path = request.getRequestURI();
		int lastIndexOf = path.lastIndexOf('/');

		String forwardPath = path.substring(lastIndexOf);
		crossContext.getRequestDispatcher(forwardPath).forward(request, response);
	}
}
