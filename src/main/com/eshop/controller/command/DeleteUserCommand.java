package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class DeleteUserCommand implements Command {

	Logger logger = Logger.getLogger(DeleteUserCommand.class.getName());

	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			long id = Long.parseLong(req.getParameter(Attributes.USER_ID));
			User user = service.getUser(id);
			service.deleteUser(user);
			return new CommandOutput (Path.USERS, true);
		}
		catch (DBException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}

	@Override
	public boolean checkUserRights (User user) {
		return user != null && user.getRole() == Role.ADMIN;
	}

}
