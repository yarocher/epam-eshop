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

public class AuthFilter implements Filter {
	@Override
	public void init (FilterConfig config) {

	}
	@Override
	public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		User user = (User) req.getSession().getAttribute("user");
		String commandName = req.getRequestURI().replaceAll("/eshop/controller/", "");
		Command command = CommandContainer.get(commandName);

		if (user == null && !CommandContainer.isGuestCommand(command)
			|| user != null && user.getRole() == Role.CUSTOMER && !CommandContainer.isCustomerCommand(command)
			|| user != null && user.getRole() == Role.ADMIN && !CommandContainer.isAdminCommand(command)) res.sendError(HttpServletResponse.SC_FORBIDDEN);

		else chain.doFilter(request, response);
	}
	@Override
	public void destroy () {

	}
}
