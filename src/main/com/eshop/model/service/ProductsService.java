package com.eshop.model.service;

import java.util.List;

import com.eshop.model.dao.DBException;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.ProductsDao;
import com.eshop.model.entity.Product;
import com.eshop.model.ProductSearcher;

public class ProductsService {

	DaoFactory daoFactory = DaoFactory.getInstance();

	public void createProduct (Product p) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			dao.create(p);
		}
	}

	public Product getProduct (long id) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			Product p = dao.findById(id);
			return p;
		}
	}

	public List <Product> getProducts () throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			return dao.findAll();
		}
	}

	public List <Product> searchProducts (ProductSearcher pattern) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			List <Product> products = dao.findByPattern(pattern);
			return products;
		}
	}

	public void updateProduct (Product p) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			dao.update(p);
		}
	}

	public void deleteProduct (Product p) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			dao.delete(p);
		}
	}

}
