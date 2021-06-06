package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

public class AccountCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		try {
			User user = (User) req.getSession().getAttribute(Attributes.USER);
			user = new UsersService().getUser(user.getId());
			req.getSession().setAttribute(Attributes.USER, user);
			return new CommandOutput (Path.USER_ACCOUNT_PAGE);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user != null && user.getRole() == Role.CUSTOMER;
	}
}
