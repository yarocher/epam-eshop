
package com.eshop.controller.command;

import java.util.List;
import java.math.BigDecimal;

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

public class UpdateProductCommand implements Command {

	Logger logger = Logger.getLogger(UpdateProductCommand.class.getName());

	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {
			long id = Long.parseLong(req.getParameter(Attributes.PRODUCT_ID));
			Product product = service.getProduct(id);
			String name = req.getParameter(Attributes.NAME);
			String category = req.getParameter(Attributes.CATEGORY);
			String description = req.getParameter(Attributes.DESCRIPTION);
			String stateParam = req.getParameter(Attributes.STATE);
			BigDecimal price;
			int amount;

			try {
				price = new BigDecimal(req.getParameter(Attributes.PRICE));
				amount = Integer.parseInt(req.getParameter(Attributes.AMOUNT));
			}
			catch (NumberFormatException | NullPointerException e) {
				throw new IllegalArgumentException ("wrong input");
			}

			ProductState state = product.getState();
			for (ProductState ps: ProductState.values()) if (ps.toString().equals(stateParam)) state = ps; 
			
			//builder??
			product.setName(name);
			product.setCategory(category);
			product.setDescription(description);
			product.setPrice(price);
			product.setAmount(amount);
			product.setState(state);

			service.updateProduct(product);

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
