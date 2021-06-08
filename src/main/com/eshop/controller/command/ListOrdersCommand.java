package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.controller.Pages;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.OrdersService;
import com.eshop.model.entity.Order;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ListOrdersCommand implements Command {

	Logger logger = Logger.getLogger(ListOrdersCommand.class.getName());

	@Override
	public CommandOutput execute (HttpServletRequest req) {
		OrdersService service = new OrdersService();
		try {
			List <Order> orders = service.getOrders();
			Pages <Order> pages = new Pages <> (Integer.parseInt(req.getServletContext().getInitParameter(Attributes.PAGE_PORTION)));
			pages.getAll().addAll(orders);
			int pageNum;

			try {
				pageNum = Integer.parseInt(req.getParameter(Attributes.PAGE));
			}
			catch (NumberFormatException | NullPointerException e) {
				pageNum = 1;
			}

			req.getServletContext().setAttribute(Attributes.ORDERS, pages.getPage(pageNum));
			return new CommandOutput (Path.ORDERS_PAGE);
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
