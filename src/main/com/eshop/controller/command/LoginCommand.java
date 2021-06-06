package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.Role;

import com.eshop.controller.Validator;
import com.eshop.controller.AuthorizationException;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

public class LoginCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		final String ALREADY_LOGGED_IN = "already logged in";

		UsersService service = new UsersService();
		try {
			HttpSession session = req.getSession();
			if (session.getAttribute(Attributes.USER) != null) throw new AuthorizationException (ALREADY_LOGGED_IN);

			User user = service.getUser(req.getParameter(Attributes.LOGIN));
			Validator.validateUser(req, user); 

			@SuppressWarnings("unchecked")
			List <User> loggedUsers = (List<User>) req.getServletContext().getAttribute(Attributes.LOGGED_USERS);
			if (loggedUsers == null) loggedUsers = new ArrayList <User> ();
			if (loggedUsers.contains(user)) throw new AuthorizationException (ALREADY_LOGGED_IN);
			else loggedUsers.add(user);
			session.setAttribute(Attributes.USER, user);
			req.getServletContext().setAttribute(Attributes.LOGGED_USERS, loggedUsers);

			return new CommandOutput ("/", true);
		}
		catch (AuthorizationException | DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user == null;
	}
}
