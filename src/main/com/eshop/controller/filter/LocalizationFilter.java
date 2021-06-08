
package com.eshop.controller.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import com.eshop.controller.Attributes;

import java.util.logging.Logger;
import java.util.logging.Level;

public class LocalizationFilter implements Filter {

	Logger logger = Logger.getLogger(LocalizationFilter.class.getName());

	@Override
	public void init (FilterConfig config) {

	}

	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String currentLang = (String) session.getAttribute(Attributes.LANG);
		String newLang = req.getParameter(Attributes.LANG); 

		if (newLang != null && !newLang.equals(currentLang)) session.setAttribute(Attributes.LANG, newLang);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy () {

	}
}
