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
			Pages <Product> pages = new Pages <> (2);
			pages.getAll().addAll(products);
			req.getServletContext().setAttribute("productsPages", pages);
			System.out.println(req.getServletContext().getAttribute("productsPage"));
			return new CommandOutput ("/products.jsp");
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
