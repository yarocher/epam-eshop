package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.Order;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class CartCommand implements Command {

	Logger logger = Logger.getLogger(CartCommand.class.getName());

	@Override
	public CommandOutput execute (HttpServletRequest req) {
		return new CommandOutput (Path.CART_PAGE);
	}

	@Override
	public boolean checkUserRights (User user) {
		return user == null || user.getRole() == Role.CUSTOMER;
	}

}
