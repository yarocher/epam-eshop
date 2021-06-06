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

import java.util.logging.Logger;
import java.util.logging.Level;

public class AddItemCommand implements Command {
	Logger logger = Logger.getLogger(AddItemCommand.class.getName());
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
			
			String encodedURL = Path.PRODUCTS + 
					"?" + Attributes.SEARCH_BY + "=" + req.getParameter(Attributes.SEARCH_BY) + 
					"&" + Attributes.FILTER + "=" + req.getParameter(Attributes.FILTER) + 
					"&" + Attributes.PRICE_MIN + "=" + req.getParameter(Attributes.PRICE_MIN) + 
					"&" + Attributes.PRICE_MAX + "=" + req.getParameter(Attributes.PRICE_MAX) +
					"&" + Attributes.SORT_BY + "=" + req.getParameter(Attributes.SORT_BY) + 
					"&" + Attributes.PAGE + "=" + req.getParameter(Attributes.PAGE) + 
					"#" + Attributes.ELEMENTS;

			logger.info("encodedURL: " + encodedURL);
			
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
		return user == null || user.getRole() == Role.CUSTOMER;
	}
}
