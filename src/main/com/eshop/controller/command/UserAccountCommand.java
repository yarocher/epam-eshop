package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;

public class UserAccountCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		UsersService service = new UsersService();
		return new CommandOutput ("/user.jsp");
	}
}
