package com.eshop.model.db;

import java.util.List;

import com.eshop.model.entity.*;

public interface ProductsDAO {
	List <Product> getAllProducts () throws DBException;
	List <Category> getAllCategories () throws DBException;
	List <Key> getCategoryKeys (Category category) throws DBException;
	List <String> getKeyValues (Key key) throws DBException;
	Product getProductById (long id) throws DBException;
	Category getCategoryById (long id) throws DBException;
	List <Product> getCategoryProducts (Category category) throws DBException;
	/*
	Category getProductCategory (Product product) throws DBException;
	*/
}
