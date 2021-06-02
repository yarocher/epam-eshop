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

public class LoginCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			HttpSession session = req.getSession();
			System.out.println(session.getAttribute("user"));
			if (session.getAttribute("user") != null) throw new AuthorizationException ("already logged in");

			User user = service.getUser(req.getParameter("login"));
			Validator.validateUser(req, user); 

			session.setAttribute("user", user);

			@SuppressWarnings("unchecked")
			List <User> loggedUsers = (List<User>) req.getServletContext().getAttribute("logged-users");
			if (loggedUsers == null) loggedUsers = new ArrayList <User> ();
			if (loggedUsers.contains(user)) throw new AuthorizationException ("already logged in");
			else loggedUsers.add(user);
			req.getServletContext().setAttribute("logged-users", loggedUsers);

			return new CommandOutput ("/", true);
		}
		catch (AuthorizationException | DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
