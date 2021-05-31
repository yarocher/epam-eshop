package com.eshop.controller.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;

import com.eshop.model.dao.DBException;

public class AddItemCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			Map <Product, Integer> items = (Map <Product, Integer>) session.getAttribute("cart");
			long productId = Long.parseLong(req.getParameter("product_id"));

			Product product = new ProductsService().getProduct(productId);

			if (items.containsKey(product)) {
				Integer amount = items.get(product);
				items.put(product, amount + 1);
			}
			else items.put(product, 1);
			
			return new CommandOutput ("/controller/products", true);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getServletContext().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
