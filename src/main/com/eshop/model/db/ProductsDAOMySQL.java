package com.eshop.model.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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
		Connection conn = getConnection ();
		QueryExecutor <List<Product>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <Product> products = new ArrayList <> ();
			rs = stmt.executeQuery(MySQLQueries.GET_ALL_PRODUCTS);

			while (rs.next()) {
				Product product = parseProduct(conn, rs);
				products.add(product);
			}	
			return products;
		});
		qe.setErrorMessage("Something went wrong while trying to get all products...");
		List <Product> products = qe.execute(conn, MySQLQueries.GET_ALL_PRODUCTS, false);
		qe.close(conn);
		return products;
	}

	@Override
	public List <Category> getAllCategories() throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <List <Category>> qe = new QueryExecutor<> ();
		qe.setQuery((stmt, rs) -> {
			List <Category> categories = new ArrayList <> ();
			rs = stmt.executeQuery(MySQLQueries.GET_ALL_CATEGORIES);
			while (rs.next()) {
				Category category = parseCategory(conn, rs);
				categories.add(category);
			}	
			return categories;

		});
		qe.setErrorMessage("Something went wrong while trying to get all categories...");
		List <Category> categories = qe.execute(conn, MySQLQueries.GET_ALL_CATEGORIES, false);
		qe.close(conn);
		return categories;
	}

	private List <Key> getCategoryKeys (Connection conn, Category category) throws DBException {
		QueryExecutor <List <Key>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <Key> keys = new ArrayList <> ();
			stmt.setString(1, Long.toString(category.getId()));
			rs = stmt.executeQuery();
			while (rs.next()) {
				Key key = parseKey (conn, rs);
				keys.add(key);
			}
			return keys;
		});
		qe.setErrorMessage("Something went wrong while trying to get category keys...");
		return qe.execute(conn, MySQLQueries.GET_CATEGORY_KEYS, false);
	}

	private Key getKey (Connection conn, String name) throws DBException {
		QueryExecutor <Key> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, name); 
			rs = stmt.executeQuery();
			if (rs.next()) {
				Key key = ef.newKey(rs.getString("name"));
				key.setId(rs.getLong("id"));	
				return key;
			}
			else throw new DBException ("There is no key with name " + name + "!");
	
		});
		qe.setErrorMessage("Something went wrong while trying to get key...");
		return qe.execute(conn, MySQLQueries.GET_KEY_BY_NAME,false);
	}

	private Value getValue (Connection conn, String name) throws DBException {
		QueryExecutor <Value> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, name); 
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				Value value = ef.newValue(rs.getString("name"));
				value.setId(rs.getLong("id"));	
				return value; 
			}
			else throw new DBException ("There is no value with name " + name + "!");
	
		});
		qe.setErrorMessage("Something went wrong while trying to get key...");
		return qe.execute(conn, MySQLQueries.GET_VALUE_BY_NAME,false);
	}


	private List <Value> getKeyValues (Connection conn, Key key) throws DBException {
		QueryExecutor <List <Value>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <Value> values = new ArrayList <> ();
			stmt.setString(1, Long.toString(key.getId()));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Value value = ef.newValue(rs.getString("name")); 
				value.setId(rs.getLong("id"));
				values.add(value);
			}
			return values;
		});
		qe.setErrorMessage("Something went wrong while trying to get key values...");
		return qe.execute(conn, MySQLQueries.GET_KEY_VALUES, false);
	}

	@Override
	public Product getProductById (long id)  throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <Product> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				Product product = parseProduct(conn, rs);
				return product;
			}
			else throw new DBException ("Product with id " + id + "is not exist!"); 
			
		});
		qe.setErrorMessage("Something went wrong while trying to get product by id...");
		Product product = qe.execute(conn, MySQLQueries.GET_PRODUCT_BY_ID, false);
		qe.close(conn);
		return product;
	}

	@Override
	public Category getCategoryById (long id) throws DBException  {
		Connection conn = getConnection();
		QueryExecutor<Category> qe = new QueryExecutor<> ();
		qe.setQuery((stmt, rs) -> {
			stmt = conn.prepareStatement(MySQLQueries.GET_CATEGORY_BY_ID);
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				Category category = parseCategory(conn, rs);
				return category;
			}
			else throw new DBException ("Category with id " + id + "is not exist!"); 

		});
		qe.setErrorMessage("Something went wrong while trying to get category by id...");
		Category category = qe.execute(conn, MySQLQueries.GET_CATEGORY_BY_ID, false);
		qe.close(conn);
		return category;
	}

	@Override
	public List <Product> getCategoryProducts (Category category) throws DBException {
		Connection conn = getConnection();
		QueryExecutor <List <Product>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <Product> products = new ArrayList <> ();
			long categoryId = category.getId();
			stmt.setString(1, Long.toString(categoryId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = parseProduct(conn, rs);
				products.add(product);
			}
			return products;
		});
		qe.setErrorMessage("Something went wrong while trying to get category products...");
		List <Product> products = qe.execute(conn, MySQLQueries.GET_CATEGORY_PRODUCTS, false);
		qe.close(conn);
		return products;
	}

	private Map <Key, Value> getProductAttributes (Connection conn, Product product) throws DBException {
		QueryExecutor <Map <Key, Value>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			long productId = product.getId();
			Map <Key, Value> attributes = new HashMap <> ();
			stmt.setString(1, Long.toString(productId));
			rs = stmt.executeQuery();

			while (rs.next()) {
				Key key = ef.newKey(rs.getString("key"));
				key.setId(rs.getLong("key_id"));
				Value value = ef.newValue(rs.getString("value"));
				value.setId(rs.getLong("value_id"));
				attributes.put(key, value);
			}
			return attributes;
		});
		qe.setErrorMessage("Something went wrong while trying to get product attributes...");
		return qe.execute(conn, MySQLQueries.GET_PRODUCT_ATTRIBUTES, false);
	}

	//LINKING OPERATIONS
	private boolean addValueToKey (Connection conn, Value value, Key key) throws DBException{
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(key.getId()));
			stmt.setString(2, Long.toString(value.getId()));
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to add value to key...");
		return qe.execute(conn, MySQLQueries.ADD_VALUE_TO_KEY, false).booleanValue();
	}

	private boolean addKeyToCategory (Connection conn, Key key, Category category) throws DBException {
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(category.getId()));
			stmt.setString(2, Long.toString(key.getId()));
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to add key to category...");
		return qe.execute(conn, MySQLQueries.ADD_KEY_TO_CATEGORY, false).booleanValue();
	}

	private boolean addProductAttribute (Connection conn, Product product, Category category, Key key, Value value) throws DBException {
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(product.getId()));
			stmt.setString(2, Long.toString(category.getId()));
			stmt.setString(3, Long.toString(key.getId()));
			stmt.setString(4, Long.toString(value.getId()));
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to insert attribute...");
		return qe.execute(conn, MySQLQueries.INSERT_ATTRIBUTE, false).booleanValue();
	}

	//INSERTING ENITITIES
	@Override
	public boolean insertCategory (Category category) throws DBException {
		Connection conn = getConnection();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, category.getName());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			boolean res = rs.next();

			if (res) { 
				category.setId(rs.getLong(1));
				for (Key key: category.keys()) {
					insertKey(conn, key);
					for (Value value: key.values()) {
						insertValue(conn, value);
						addValueToKey(conn, value, key);
					}
					addKeyToCategory(conn, key, category);
				}
			}
			return res;
		});
		qe.setErrorMessage("Something went wrong while trying to insert category...");
		boolean res = qe.execute(conn, MySQLQueries.INSERT_CATEGORY, true).booleanValue();
		qe.close(conn);
		return res;
	}

	@Override
	public boolean insertProduct (Product product, Category category) throws DBException {
		Connection conn = getConnection();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			setProduct(stmt, product, category);
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			boolean res = rs.next();
			if (res) { 
				product.setId(rs.getLong(1));
				for (Map.Entry<Key, Value> entry: product.attributes().entrySet()) {
					Key key = getKey (conn, entry.getKey().getName());
					Value value = getValue (conn, entry.getValue().getName());
					addProductAttribute(conn, product, category, key, value);
				}
			}	
			return res;
		});
		qe.setErrorMessage("Something went wrong while trying to insert product...");
		boolean res = qe.execute(conn, MySQLQueries.INSERT_PRODUCT, true).booleanValue();
		qe.close(conn);
		return res;
	}

	private boolean insertValue (Connection conn, Value value) throws DBException {
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, value.getName());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			boolean res = rs.next();
			if (res) { 
				value.setId(rs.getLong(1));
			}
			return res;
		});
		qe.setErrorMessage("Something went wrong while trying to insert value...");
		return qe.execute(conn, MySQLQueries.INSERT_VALUE, true).booleanValue();
	}


	private boolean insertKey (Connection conn, Key key) throws DBException {
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, key.getName());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			boolean res = rs.next();
			if (res) { 
				key.setId(rs.getLong(1));
			}
			return res;
		});
		qe.setErrorMessage("Something went wrong while trying to insert key...");
		return qe.execute(conn, MySQLQueries.INSERT_KEY, true).booleanValue();
	}


	private Product parseProduct (Connection conn, ResultSet rs) throws SQLException, DBException  {
		Product product = ef.newProduct(rs.getString("name"), rs.getDouble("price"), rs.getInt("amount")); 
		product.setId(rs.getLong("id"));
		product.setDescription(rs.getString("description"));
		product.setState(rs.getString("state"));
		product.setDateCreation(rs.getDate("date_creation"));
		product.setDateModified(rs.getDate("date_modified"));
		for (Map.Entry<Key, Value> entry: getProductAttributes(conn, product).entrySet()) {
			product.attributes().put(entry.getKey(), entry.getValue());	
		}
		return product;
	}

	private Category parseCategory (Connection conn, ResultSet rs) throws SQLException, DBException  {
		Category category = ef.newCategory(rs.getString("name"));
		category.setId(rs.getLong("id"));
		List <Key> categoryKeys = getCategoryKeys (conn, category);	
		category.keys().addAll(categoryKeys);
		return category;
	}

	private Key parseKey (Connection conn, ResultSet rs) throws SQLException, DBException  {
		Key key = ef.newKey(rs.getString("name")); 
		key.setId(rs.getLong("id"));
		List <Value> values = getKeyValues (conn, key);
		key.values().addAll(values);
		return key;
	}

	private void setProduct (PreparedStatement stmt, Product product, Category category) throws SQLException {
		stmt.setString(1, product.getName());
		String description = product.getDescription();
			if (description != null) stmt.setString(2, description);
			else stmt.setNull(2, Types.VARCHAR); 
		stmt.setString(3, Double.toString(product.getPrice()));
		stmt.setString(4, Integer.toString(product.getAmount()));
		stmt.setString(5, Long.toString(category.getId()));
	}
}
