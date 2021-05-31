package com.eshop.controller.command;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;

public class LogoutCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			HttpSession session = req.getSession();
			if (session.getAttribute("user") == null) throw new AuthorizationException ("not logged in yet");

			session.invalidate();

			return new CommandOutput ("", true);
		}
		catch (AuthorizationException e) {
			e.printStackTrace();
			req.getServletContext().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
