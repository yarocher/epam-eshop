package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.OrdersService;
import com.eshop.model.entity.Order;

public class AccountCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		return new CommandOutput ("/user-account.jsp");
	}
}
