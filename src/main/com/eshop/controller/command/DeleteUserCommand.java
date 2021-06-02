package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.UserState;

public class DeleteUserCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			long id = Long.parseLong(req.getParameter("user_id"));

			User user = service.getUser(id);

			service.deleteUser(user);

			return new CommandOutput ("/controller/users", true);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
