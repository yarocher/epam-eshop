package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.OrdersService;
import com.eshop.model.entity.Order;
import com.eshop.model.entity.OrderState;

public class UpdateOrderCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		OrdersService service = new OrdersService();
		try {
			long id = Long.parseLong(req.getParameter("order_id"));
			String stateParam = req.getParameter("state");

			Order order = service.getOrder(id);

			//todo: validate state
			OrderState state = order.getState();
			
			for (OrderState os: OrderState.values()) if (os.toString().equals(stateParam)) state = os; 
			System.out.println(stateParam);
			System.out.println(state);
	
			order.setState(state);

			service.updateOrder(order);

			return new CommandOutput ("/controller/orders", true);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
