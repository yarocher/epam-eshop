package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.OrdersService;
import com.eshop.model.entity.Order;
import com.eshop.model.entity.OrderState;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class UpdateOrderCommand implements Command {

	Logger logger = Logger.getLogger(UpdateOrderCommand.class.getName());

	@Override
	public CommandOutput execute (HttpServletRequest req) {
		OrdersService service = new OrdersService();
		try {
			long id = Long.parseLong(req.getParameter(Attributes.ORDER_ID));
			String stateParam = req.getParameter(Attributes.STATE);

			Order order = service.getOrder(id);

			OrderState state = order.getState();
			for (OrderState os: OrderState.values()) if (os.toString().equals(stateParam)) state = os; 
			order.setState(state);

			service.updateOrder(order);

			String encodedURL = Path.ORDERS + 
				"?" + Attributes.PAGE + "=" + req.getParameter(Attributes.PAGE) +
				"#" + Attributes.ELEMENTS;
			
			return new CommandOutput (encodedURL, true);
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
