package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.Order;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Path;

public class CartCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		return new CommandOutput (Path.CART_PAGE);
	}
	@Override
	public boolean checkUserRights (User user) {
		return user == null || user.getRole() == Role.CUSTOMER;
	}
}
