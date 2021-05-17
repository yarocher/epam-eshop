package com.eshop.model.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.eshop.model.entity.*;

public class ProductsDAOMySQL implements ProductsDAO {
	private String URL = "jdbc:mysql://localhost:3306/mydb?user=login&password=password";
	private EntityFactory ef = EntityFactory.getInstance();

	private Connection getConnection () throws DBException {
		try {
			return DriverManager.getConnection(URL); 
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get connection...", sqle);
		}
	}	

	@Override
	public List <Product> getAllProducts () throws DBException {
		List <Product> products = new ArrayList <> ();
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection ();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(MySQLQueries.GET_ALL_PRODUCTS);

			while (rs.next()) {
				Product product = parseProduct(rs);
				products.add(product);
			}	
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get all products...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);
		}	
		return products;
	}

	@Override
	public List <Category> getAllCategories() throws DBException {
		List <Category> categories = new ArrayList <> ();
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection ();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(MySQLQueries.GET_ALL_CATEGORIES);

			while (rs.next()) {
				Category category = parseCategory(rs);
				categories.add(category);
			}	
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get all categories...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);
		}	
		return categories;
	}

	@Override
	public List <Key> getCategoryKeys (Category category) throws DBException {
		List <Key> keys = new ArrayList <> ();
		long categoryId = category.getId();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(MySQLQueries.GET_CATEGORY_KEYS);	
			stmt.setString(1, Long.toString(categoryId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Key key = parseKey (rs);
				keys.add(key);
			}

		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get category keys...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);	
		}
		return keys;	
	}

	@Override
	public List <String> getKeyValues (Key key) throws DBException {
		List <String> values = new ArrayList <> ();
		long keyId = key.getId();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(MySQLQueries.GET_KEY_VALUES);	
			stmt.setString(1, Long.toString(keyId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				values.add(rs.getString("name"));
			}

		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get key values...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);	
		}
		return values;	
	}

	@Override
	public Product getProductById (long id)  throws DBException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection ();
			stmt = conn.prepareStatement(MySQLQueries.GET_PRODUCT_BY_ID);
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				Product product = parseProduct(rs);
				return product;
			}
			else throw new DBException ("Product with id " + id + "is not exist!"); 
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get product by id...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);
		}
	}

	@Override
	public Category getCategoryById (long id) throws DBException  {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection ();
			stmt = conn.prepareStatement(MySQLQueries.GET_CATEGORY_BY_ID);
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				Category category = parseCategory(rs);
				return category;
			}
			else throw new DBException ("Category with id " + id + "is not exist!"); 
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get category by id...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);
		}
	}

	@Override
	public List <Product> getCategoryProducts (Category category) throws DBException {
		List <Product> products = new ArrayList <> ();
		long categoryId = category.getId();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(MySQLQueries.GET_CATEGORY_PRODUCTS);
			stmt.setString(1, Long.toString(categoryId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = parseProduct(rs);
				products.add(product);
			}
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get category products...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		return products;
	}

	public Map <String, String> getProductAttributes (Product product) throws DBException {
		long productId = product.getId();
		Map <String, String> attributes = new HashMap <> ();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement(MySQLQueries.GET_PRODUCT_ATTRIBUTES);
			stmt.setString(1, Long.toString(productId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				attributes.put(rs.getString("key"), rs.getString("value"));
			}
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get product attributes...", sqle);
		}
		finally {
			close(rs);
			close(stmt);
			close(conn);
		}
		return attributes;
	}

	private Product parseProduct (ResultSet rs) throws SQLException, DBException  {
		Product product = ef.newProduct(rs.getString("name"), rs.getDouble("price"), rs.getInt("amount")); 
		product.setId(rs.getLong("id"));
		product.setDescription(rs.getString("description"));
		product.setState(rs.getString("state"));
		product.setDateCreation(rs.getDate("date_creation"));
		product.setDateModified(rs.getDate("date_modified"));
		for (Map.Entry<String, String> entry: getProductAttributes(product).entrySet()) {
			product.attributes().put(entry.getKey(), entry.getValue());	
		}
		return product;
	}

	private Category parseCategory (ResultSet rs) throws SQLException, DBException  {
		Category category = ef.newCategory(rs.getString("name"));
		category.setId(rs.getLong("id"));
		List <Key> categoryKeys = getCategoryKeys (category);	
		category.keys().addAll(categoryKeys);
		return category;
	}

	private Key parseKey (ResultSet rs) throws SQLException, DBException  {
		Key key = ef.newKey(rs.getString("name")); 
		key.setId(rs.getLong("id"));
		List <String> values = getKeyValues (key);
		key.values().addAll(values);
		return key;
	}

	private void close (AutoCloseable res) {
		try {
			if (res != null) res.close();
		}
		catch (Exception ioe) {
			System.out.println("Can't close resource " + res);
		}
	}
}
