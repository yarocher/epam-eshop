package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;

public class ListProductsCommand implements Command {
	@Override
	public String execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {
			return service.getProducts().toString();
		}
		catch (DBException dbe) {
			return dbe.getCause().getMessage();
		}	
	}
}
