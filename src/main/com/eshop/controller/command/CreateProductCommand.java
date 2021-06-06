
package com.eshop.controller.command;

import java.util.List;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;
import com.eshop.model.entity.ProductState;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import java.util.logging.Logger;
import java.util.logging.Level;

public class CreateProductCommand implements Command {
	Logger logger = Logger.getLogger(CreateProductCommand.class.getName());
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {

			String name = new String (req.getParameter(Attributes.NAME).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			String category = new String (req.getParameter(Attributes.CATEGORY).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			String description = new String (req.getParameter(Attributes.DESCRIPTION).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
			BigDecimal price;
			int amount;
			try {
				price = new BigDecimal(req.getParameter(Attributes.PRICE));
				amount = Integer.parseInt(req.getParameter(Attributes.AMOUNT));
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

			service.createProduct(product);

			return new CommandOutput (Path.PRODUCTS, true);
		}
		catch (IllegalArgumentException | DBException e) {
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
