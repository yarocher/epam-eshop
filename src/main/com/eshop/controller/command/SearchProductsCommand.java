package com.eshop.controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.dao.DBException;
import com.eshop.model.service.ProductsService;
import com.eshop.model.entity.Product;
import com.eshop.model.ProductSearcher;

public class SearchProductsCommand implements Command {
	@Override
	public CommandOutput execute (HttpServletRequest req) {
		ProductsService service = new ProductsService();
		try {
			String name = req.getParameter("name");
			String category = req.getParameter("category");
			double priceMin = -1;
			double priceMax = -1;
			try {
				priceMin = Double.parseDouble(req.getParameter("price_min"));
			}
			catch (NumberFormatException | NullPointerException e) {

			}
			try {
				priceMax = Double.parseDouble(req.getParameter("price_max"));
			}
			catch (NumberFormatException | NullPointerException e) {

			}
			System.out.println("min: " + req.getParameter("price_min"));
			System.out.println("max: " + req.getParameter("price_max"));
			System.out.println("min: " + priceMin);
			System.out.println("max: " + priceMax);

			ProductSearcher pattern = new ProductSearcher ();
			pattern.addName("".equals(name) ? null : name)
				.addPriceMin(priceMin)
				.addPriceMax(priceMax)
				.addCategory("".equals(category) ? null : category);
			String sortBy = req.getParameter("sort_by");
			String desc = req.getParameter("desc");
			if (sortBy != null && !sortBy.equals("")) pattern.sortBy(sortBy, "on".equals(desc));

			System.out.println(pattern.get());

			List <Product> products = service.searchProducts(pattern);

			Pages <Product> pages = new Pages <> (Integer.parseInt(req.getServletContext().getInitParameter("pagePortion")));
			pages.getAll().addAll(products);

			int pageNum;
			try {
				pageNum = Integer.parseInt(req.getParameter("page"));
			}
			catch (NumberFormatException | NullPointerException e) {
				pageNum = 1;
			}

			req.getServletContext().setAttribute("products", pages.getPage(pageNum));
			return new CommandOutput ("/products.jsp");
		}
		catch (DBException e) {
			e.printStackTrace();
			req.getSession().setAttribute("exception", e);
			return new CommandOutput ("/error.jsp");
		}
	}
}
