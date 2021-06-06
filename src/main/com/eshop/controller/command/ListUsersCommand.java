package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.controller.Pages;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

public class ListUsersCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			List <User> users = service.getUsers();

			Pages <User> pages = new Pages <> (Integer.parseInt(req.getServletContext().getInitParameter(Attributes.PAGE_PORTION)));
			pages.getAll().addAll(users);

			int pageNum;
			try {
				pageNum = Integer.parseInt(req.getParameter(Attributes.PAGE));
			}
			catch (NumberFormatException | NullPointerException e) {
				pageNum = 1;
			}

			req.getServletContext().setAttribute(Attributes.USERS, pages.getPage(pageNum));
			return new CommandOutput (Path.USERS_PAGE);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user != null && user.getRole() == Role.ADMIN;
	}
}
