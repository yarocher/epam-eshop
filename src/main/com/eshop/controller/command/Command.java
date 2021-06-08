package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.User;

public interface Command {

	CommandOutput execute (HttpServletRequest req);
	boolean checkUserRights (User user);

}
