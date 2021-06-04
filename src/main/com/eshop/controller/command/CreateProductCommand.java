
package com.eshop.controller.command;

import java.util.List;
import java.nio.charset.StandardCharsets;

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

			String name = new String (req.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			String category = new String (req.getParameter("category").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			String description = new String (req.getParameter("description").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			double price;
			int amount;
			try {
				price = Double.parseDouble(req.getParameter("price"));
				amount = Integer.parseInt(req.getParameter("amount"));
			}
			catch (NumberFormatException | NullPointerException e) {
				throw new IllegalArgumentException ("wrong input");
			}
			
			Product product = new Product ();

			product.setName(name);
			product.setCategory(category);
			product.setDescription(description);
			product.setPrice(price);
			product.setAmount(amount);

			System.out.println("Content-Type: " + req.getHeader("Content-Type"));
			System.out.println(req.getCharacterEncoding());
			System.out.println("inserting.." + product);


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
