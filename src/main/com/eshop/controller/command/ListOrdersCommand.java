package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.OrdersService;
import com.eshop.model.entity.Order;

public class ListOrdersCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		OrdersService service = new OrdersService();
		try {
			List <Order> orders = service.getOrders();

			Pages <Order> pages = new Pages <> (Integer.parseInt(req.getServletContext().getInitParameter("pagePortion")));
			pages.getAll().addAll(orders);

			int pageNum;
			try {
				pageNum = Integer.parseInt(req.getParameter("page"));
			}
			catch (NumberFormatException | NullPointerException e) {
				pageNum = 1;
			}

			req.getServletContext().setAttribute("orders", pages.getPage(pageNum));
			return new CommandOutput ("/orders.jsp");
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
