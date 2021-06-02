
package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.ProductState;

public class CreateProductCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {
			String name = req.getParameter("name");
			String category = req.getParameter("category");
			String description = req.getParameter("description");
			String stateParam = req.getParameter("state");
			double price;
			int amount;
			try {
				price = Double.parseDouble(req.getParameter("price"));
				amount = Integer.parseInt(req.getParameter("amount"));
			}
			catch (NumberFormatException | NullPointerException e) {
				throw new IllegalArgumentException ("wrong input");
			}
			
			ProductState state = ProductState.ON_SALE; 
			
			for (ProductState ps: ProductState.values()) if (ps.toString().equals(stateParam)) state = ps; 
			
			Product product = new Product ();

			product.setName(name);
			product.setCategory(category);
			product.setDescription(description);
			product.setPrice(price);
			product.setAmount(amount);
			product.setState(state);

			service.createProduct(product);

			return new CommandOutput ("/controller/products", true);
		}
		catch (IllegalArgumentException | DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
