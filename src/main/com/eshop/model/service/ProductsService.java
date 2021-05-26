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
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
	public Product getProduct (long id) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			Product p = dao.findById(id);
			return p;
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public List <Product> getProducts () throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			return dao.findAll();
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public List <Product> searchProducts (ProductSearcher pattern) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			List <Product> products = dao.findByPattern(pattern);
			return products;
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public void updateProduct (Product p) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			dao.update(p);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public void deleteProduct (Product p) throws DBException {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			dao.delete(p);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
}
