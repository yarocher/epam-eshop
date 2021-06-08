package com.eshop.controller.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

import com.eshop.controller.command.Command;
import com.eshop.controller.command.CommandContainer;

import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Path;
import com.eshop.controller.Attributes;

public class AuthFilter implements Filter {

	@Override
	public void init (FilterConfig config) {

	}

	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		User user = (User) req.getSession().getAttribute(Attributes.USER);
		String uri = req.getRequestURI();
		String commandName = uri.contains(Path.CONTROLLER) ? 
			uri.replaceAll(req.getContextPath() + Path.CONTROLLER + "/", "") : uri.replaceAll(req.getContextPath() + "/", "");
		Command command = CommandContainer.get(commandName);

		if (!command.checkUserRights(user)) res.sendError(HttpServletResponse.SC_FORBIDDEN);
		else chain.doFilter(request, response);
	}

	@Override
	public void destroy () {

	}

}
