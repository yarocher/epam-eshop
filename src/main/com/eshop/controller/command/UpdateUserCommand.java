package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;
import com.eshop.model.entity.UserState;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

public class UpdateUserCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			long id = Long.parseLong(req.getParameter(Attributes.USER_ID));
			String stateParam = req.getParameter(Attributes.STATE);

			User user = service.getUser(id);

			//todo: validate state
			UserState state = user.getState();
			
			for (UserState os: UserState.values()) if (os.toString().equals(stateParam)) state = os; 
			System.out.println(stateParam);
			System.out.println(state);
	
			user.setState(state);

			service.updateUser(user);

			return new CommandOutput (Path.USERS, true);
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
