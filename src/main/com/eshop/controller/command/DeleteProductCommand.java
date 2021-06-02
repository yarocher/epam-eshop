package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.ProductState;

public class DeleteProductCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {
			long id = Long.parseLong(req.getParameter("product_id"));

			Product product = service.getProduct(id);

			service.deleteProduct(product);

			return new CommandOutput ("/controller/products", true);
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
