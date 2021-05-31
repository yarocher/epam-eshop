package com.eshop.controller.command;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;

public class RegistrationCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			if (req.getSession().getAttribute("user") != null) throw new AuthorizationException ("already logged in");

			User user = new User (req.getParameter("login"), req.getParameter("password"));
			service.createUser(user);

			req.getSession().setAttribute("user", user);

			List <User> loggedUsers = (List<User>) req.getServletContext().getAttribute("logged-users");
			if (loggedUsers == null) loggedUsers = new ArrayList <User> ();
			loggedUsers.add(user);
			req.getServletContext().setAttribute("logged-users", loggedUsers);

			return new CommandOutput ("/controller/products", true);
		}
		catch (AuthorizationException | DBException e) {
			e.printStackTrace();
			req.getServletContext().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
