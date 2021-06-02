package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;

public class ListUsersCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			List <User> users = service.getUsers();

			Pages <User> pages = new Pages <> (Integer.parseInt(req.getServletContext().getInitParameter("pagePortion")));
			pages.getAll().addAll(users);

			int pageNum;
			try {
				pageNum = Integer.parseInt(req.getParameter("page"));
			}
			catch (NumberFormatException | NullPointerException e) {
				pageNum = 1;
			}

			req.getServletContext().setAttribute("users", pages.getPage(pageNum));
			return new CommandOutput ("/users.jsp");
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
