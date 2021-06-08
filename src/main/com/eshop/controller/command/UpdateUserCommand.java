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

import java.util.logging.Logger;
import java.util.logging.Level;

public class UpdateUserCommand implements Command {

	Logger logger = Logger.getLogger(UpdateUserCommand.class.getName());

	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		try {
			long id = Long.parseLong(req.getParameter(Attributes.USER_ID));
			String stateParam = req.getParameter(Attributes.STATE);

			User user = service.getUser(id);

			UserState state = user.getState();
			for (UserState us: UserState.values()) if (us.toString().equals(stateParam)) state = us; 
			user.setState(state);

			service.updateUser(user);

			String encodedURL = Path.USERS + 
				"?" + Attributes.PAGE + "=" + req.getParameter(Attributes.PAGE) +
				"#" + Attributes.ELEMENTS;
			
			return new CommandOutput (encodedURL, true);
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
