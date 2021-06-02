package com.eshop.controller.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

public class LocalizationFilter implements Filter {
	@Override
	public void init (FilterConfig config) {

	}
	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;

		String paramLocale = req.getParameter("lang");
		String sessionLocale = (String) req.getSession().getAttribute("locale");

		if (paramLocale != null && !paramLocale.equals(sessionLocale)) req.getSession().setAttribute("locale", paramLocale);
		
		chain.doFilter(request, response);
	}
	@Override
	public void destroy () {

	}
}
