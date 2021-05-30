package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;

public class LoginCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			User user = service.getUser(req.getParameter("login"));
			req.getSession().setAttribute("user", user);
			return new CommandOutput ("/products.jsp");
		}
		catch (DBException dbe) {
			return null;
		}
	}
}
