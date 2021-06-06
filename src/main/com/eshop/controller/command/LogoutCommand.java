package com.eshop.controller.command;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.AuthorizationException;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

public class LogoutCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			HttpSession session = req.getSession();
			if (session.getAttribute(Attributes.USER) == null) throw new AuthorizationException ("not logged in yet");

			session.invalidate();

			return new CommandOutput ("", true);
		}
		catch (AuthorizationException e) {
			e.printStackTrace();
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user != null;
	}
}
