package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.User;

public class IndexCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		return new CommandOutput ("", true);
	}
	@Override
	public boolean checkUserRights (User user) {
		return true; 
	}

}
