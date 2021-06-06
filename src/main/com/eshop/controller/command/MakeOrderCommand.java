package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.model.service.OrdersService;
import com.eshop.model.service.UsersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;
import com.eshop.model.entity.UserState;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.Order;

import com.eshop.model.dao.DBException;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MakeOrderCommand implements Command {
	Logger logger = Logger.getLogger(MakeOrderCommand.class.getName());
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			Map <Product, Integer> items = (Map <Product, Integer>) session.getAttribute(Attributes.CART);
			User user = (User) session.getAttribute(Attributes.USER);
			user = new UsersService().getUser(user.getId());
			session.setAttribute(Attributes.USER, user);

			if (user.getState() == UserState.BLOCKED) return new CommandOutput (Path.ERROR_403_PAGE); 

			Order order = new Order ();
			order.getItems().putAll(items);
			order.setUser(user);

			new OrdersService().createOrder(order);

			session.setAttribute(Attributes.CART, new HashMap <Product, Integer> ());
			
			return new CommandOutput (Path.USER_ACCOUNT, true);
		}
		catch (DBException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user != null && user.getRole() == Role.CUSTOMER;
	}
}
