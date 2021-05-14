package com.eshop.model.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import com.eshop.model.entity.products.*;

public class ProductsDAO {
	private String url = "";

	public ProductsDAO (String url) {
		this.url = url;
	}

	public Connection getConnection (String url) throws DBException {
		try {
			return DriverManager.getConnection(url);
		}
		catch (SQLException sqle) {
			throw new DBException ("Can't get connection to " + url, sqle);
		}
	}

	public void insertProduct (Product product) {

	}
	public void insertCategory (Category category) {

	}
	public void updateProduct (Product product) {

	}
	public void updateCategory (Category category) {

	}
	public void deleteProduct (Product product) {

	}
	public void deleteCategory (Category category) {

	}
	public List <Product> getAllProducts () {

		return null;
	}
	public List <Category> getAllCategories() {

		return null;
	}
	public List <Product> getCategoryProducts (Category category) {

		return null;
	}
	public Category getCategoryById (long id) {

		return null;
	}
	public Category getCategoryByName (String name) {

		return null;
	}
	public Product getProductById (long id) {

		return null;
	}
	public Product getProductByPattern (Product pattern) {

		return null;
	}
}
