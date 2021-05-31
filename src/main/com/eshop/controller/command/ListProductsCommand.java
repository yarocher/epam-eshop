package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;

public class ListProductsCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {
			List <Product> products = service.getProducts();
			req.getServletContext().setAttribute("products", products);
			return new CommandOutput ("/products.jsp");
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getServletContext().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
