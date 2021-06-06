package com.eshop.controller.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.model.dao.DBException;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

public class AddItemCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			Map <Product, Integer>  items  = (Map <Product, Integer>) session.getAttribute(Attributes.CART);
			long productId = Long.parseLong(req.getParameter(Attributes.PRODUCT_ID));

			Product product = new ProductsService().getProduct(productId);

			if (items.containsKey(product)) {
				Integer amount = items.get(product);
				items.put(product, amount + 1);
			}
			else items.put(product, 1);
			
			return new CommandOutput (Path.PRODUCTS, true);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return user == null || user.getRole() == Role.CUSTOMER;
	}
}
