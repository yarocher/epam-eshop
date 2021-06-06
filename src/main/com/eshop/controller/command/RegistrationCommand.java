package com.eshop.controller.command;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;
import com.eshop.model.entity.UserState;

import com.eshop.controller.Validator;
import com.eshop.controller.AuthorizationException;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class RegistrationCommand implements Command {
	Logger logger = Logger.getLogger(RegistrationCommand.class.getName());
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			if (req.getSession().getAttribute(Attributes.USER) != null) throw new AuthorizationException ("already logged in");

			String login = req.getParameter(Attributes.LOGIN);
			String password = req.getParameter(Attributes.PASSWORD);

			Validator.validateLogin(login);
			Validator.validatePassword(password);

			User user = new User (login, password);
			user.setState(UserState.ACTIVE);
			service.createUser(user);

			req.getSession().setAttribute(Attributes.USER, user);

			@SuppressWarnings("unchecked")
			List <User> loggedUsers = (List<User>) req.getServletContext().getAttribute(Attributes.LOGGED_USERS);
			if (loggedUsers == null) loggedUsers = new ArrayList <User> ();
			loggedUsers.add(user);
			req.getServletContext().setAttribute(Attributes.LOGGED_USERS, loggedUsers);

			return new CommandOutput (Path.PRODUCTS, true);
		}
		catch (AuthorizationException | DBException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user == null;
	}
}
