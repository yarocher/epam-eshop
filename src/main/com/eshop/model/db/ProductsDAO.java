package com.eshop.model.db;

import java.sql.Connection;

import java.util.List;

import com.eshop.model.entity.*;

public interface ProductsDAO {
	List <Product> getAllProducts () throws DBException;
	List <Category> getAllCategories () throws DBException;
	Product getProductById (long id) throws DBException;
	Category getCategoryById (long id) throws DBException;
	List <Product> getCategoryProducts (Category category) throws DBException;
	boolean insertCategory (Category category) throws DBException;
	boolean insertProduct (Product product, Category category) throws DBException;
	boolean updateCategory (Category category) throws DBException;
	boolean updateProduct (Product product) throws DBException;
	boolean deleteCategory (Category category) throws DBException;
	boolean deleteProduct (Product product) throws DBException;
}
