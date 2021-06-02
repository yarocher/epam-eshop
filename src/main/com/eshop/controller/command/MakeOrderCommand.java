package com.eshop.controller.command;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.model.service.OrdersService;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.Order;

import com.eshop.model.dao.DBException;

public class MakeOrderCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			Map <Product, Integer> items = (Map <Product, Integer>) session.getAttribute("cart");
			User user = (User) session.getAttribute("user");

			Order order = new Order ();
			order.items().putAll(items);
			order.setUser(user);

			new OrdersService().createOrder(order);

			session.setAttribute("cart", new HashMap <Product, Integer> ());
			
			return new CommandOutput ("/account", true);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
