package com.eshop.controller.command;

import java.util.List;
import java.math.BigDecimal;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;
import com.eshop.model.ProductSearcher;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Role;

import com.eshop.controller.Attributes;
import com.eshop.controller.Path;

import com.eshop.controller.Pages;

import java.util.logging.Logger;
import java.util.logging.Level;

public class SearchProductsCommand implements Command {
	Logger logger = Logger.getLogger(SearchProductsCommand.class.getName());
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		HttpSession session = req.getSession();
		int pagePortion = Integer.parseInt(req.getServletContext().getInitParameter(Attributes.PAGE_PORTION)); 
		try {
			String searchBy = req.getParameter(Attributes.SEARCH_BY);
			String filter = req.getParameter(Attributes.FILTER);
			String priceMin = req.getParameter(Attributes.PRICE_MIN);
			String priceMax = req.getParameter(Attributes.PRICE_MAX);
			String sortBy = req.getParameter(Attributes.SORT_BY);
			String desc = req.getParameter(Attributes.DESC);

			String page = req.getParameter(Attributes.PAGE);

			ProductSearcher pattern = new ProductSearcher ();
			if (Attributes.NAME.equals(searchBy)) pattern.addName(filter);
			if (Attributes.CATEGORY.equals(searchBy)) pattern.addCategory(filter);
			if (priceMin != null && !priceMin.isEmpty()) pattern.addPriceMin(new BigDecimal(priceMin));
			if (priceMax != null && !priceMax.isEmpty()) pattern.addPriceMax(new BigDecimal(priceMax));
			if (sortBy != null && !sortBy.isEmpty()) pattern.sortBy(sortBy, "on".equals(desc));

			Pages <Product> pages = new Pages <> (pagePortion);
			pages.getAll().addAll(service.searchProducts(pattern));

			logger.info("searchBy: " + searchBy + ", filter: " + filter + ", price_min: " + priceMin + ", price_max: " + priceMax + ", sort_by: " + sortBy + ", desc: " + desc);

			req.getServletContext().setAttribute(Attributes.PRODUCTS, pages.getPage((page == null || page.isEmpty()) ? 1 : Integer.parseInt(page)));
			return new CommandOutput (Path.PRODUCTS_PAGE);
		}
		catch (IllegalArgumentException | DBException e) {
			logger.log(Level.INFO, e.getMessage(), e);
			req.getSession().setAttribute(Attributes.EXCEPTION, e);
			return new CommandOutput (Path.EXCEPTION_PAGE);
		}
	}
	@Override
	public boolean checkUserRights (User user) {
		return true; 
	}
}
