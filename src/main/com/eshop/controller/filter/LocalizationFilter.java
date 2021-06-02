package com.eshop.controller.filter;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		HttpServletResponse res = (HttpServletResponse) response;

		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		String localeName = req.getParameter("lang");

		ResourceBundle content = null;

		if (localeName == null) content = ResourceBundle.getBundle("content");
		else {
			Locale locale = new Locale (localeName);
			content = ResourceBundle.getBundle("content", locale);
		}
		
		req.getSession().setAttribute("content", content);

		chain.doFilter(request, response);
	}
	@Override
	public void destroy () {

	}
}
