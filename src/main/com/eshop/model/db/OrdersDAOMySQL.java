package com.eshop.model.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.eshop.model.entity.*;
import com.eshop.model.db.*;

public class OrdersDAOMySQL implements OrdersDAO  {
	private String url = "jdbc:mysql://localhost:3306/mydb?user=login&password=password";
	private EntityFactory ef = EntityFactory.getInstance();
	private ProductsDAO productsDAO = new ProductsDAOMySQL ();

	@Override
	public void setURL (String url) {
		this.url = url;
		productsDAO.setURL(url);
	}

	@Override
	public Connection getConnection () throws DBException {
		try {
			return DriverManager.getConnection(url); 
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get connection...", sqle);
		}
	}	
	@Override
	public List <Order> getAllOrders () throws DBException {
		Connection conn = getConnection();
		QueryExecutor <List<Order>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <Order> orders = new ArrayList <> ();
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				orders.add(parseOrder(conn, rs));
			}
			return orders;
			
		});
		qe.setErrorMessage("Something went wrong while trying to get all orders...");
		List <Order> orders = qe.execute(conn, MySQLQueries.GET_ALL_ORDERS, false);
		qe.close(conn);
		return orders;
	}
	@Override
	public Order getOrderById (Connection conn, long id, boolean terminal) throws DBException {
		QueryExecutor <Order> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				return parseOrder(conn, rs);
			}
			else throw new DBException ("Order with id " + id + " is not exist!"); 
			
		});
		qe.setErrorMessage("Something went wrong while trying to get order by id...");
		Order order = qe.execute(conn, MySQLQueries.GET_ORDER_BY_ID, false);
		if (terminal) qe.close(conn);
		return order;
	}
	@Override
	public List <Order> getUserOrders (User user) throws DBException {
		Connection conn = getConnection();
		QueryExecutor <List<Order>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <Order> orders = new ArrayList <> ();
			stmt.setString(1, Long.toString(user.getId()));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				orders.add(parseOrder(conn, rs));
			}
			return orders;
			
		});
		qe.setErrorMessage("Something went wrong while trying to get user orders...");
		List <Order> orders = qe.execute(conn, MySQLQueries.GET_USER_ORDERS, false);
		qe.close(conn);
		return orders;
	}
	@Override
	public Cart getCurrentUserCart (User user) throws DBException {
		Connection conn = getConnection();
		QueryExecutor <Cart> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(user.getId()));
			rs = stmt.executeQuery();

			if (rs.next()) {
				Cart cart = getCartById(conn, rs.getLong("id"));
				return cart;
			}
			else throw new DBException ("There is no current user's cart...");
		});
		qe.setErrorMessage("Something went wrong while trying to get user cart...");
		Cart cart = qe.execute(conn, MySQLQueries.GET_CURRENT_USER_CART, false);
		qe.close(conn);
		return cart;
	}
	@Override
	public boolean insertCart (Cart cart) throws DBException {

		return false;
	}
	@Override
	public boolean deleteCart (Cart cart) throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(cart.getId()));	
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to delete cart...");
		boolean res = qe.execute(conn, MySQLQueries.DELETE_CART, false);
		qe.close(conn);
		return res;
	}
	@Override
	public boolean insertOrder (Order order) throws DBException {

		return false;
	}
	@Override
	public boolean updateOrder (Order order) throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, order.getState());
			stmt.setString(2, Long.toString(order.getId()));	
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to update order...");
		boolean res = qe.execute(conn, MySQLQueries.UPDATE_ORDER, false);
		qe.close(conn);
		return res;
	}
	@Override
	public boolean deleteOrder (Order order) throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(order.getId()));	
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to delete order...");
		boolean res = qe.execute(conn, MySQLQueries.DELETE_ORDER, false);
		qe.close(conn);
		return res;
	}

	private Cart getCartById (Connection conn, long id) throws DBException {
		QueryExecutor <Cart> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				Cart cart = ef.newCart();
				cart.setId(rs.getLong("id"));
				Map <Product, Integer> items = getCartItems(conn, cart);
				cart.items().putAll(items);
				return cart;
			}
			else throw new DBException ("Cart with id " + id + " is not exist!"); 
			
		});
		qe.setErrorMessage("Something went wrong while trying to get cart by id...");
		return qe.execute(conn, MySQLQueries.GET_CART_BY_ID, false);
	}

	private Map <Product, Integer> getCartItems (Connection conn, Cart cart) throws DBException {
		QueryExecutor <Map <Product, Integer>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			Map <Product, Integer> items = new HashMap <> ();
			stmt.setString(1, Long.toString(cart.getId()));
			rs = stmt.executeQuery();

			while (rs.next()) {
				long productId = rs.getLong("product_id");
				Product product = productsDAO.getProductById(conn, productId, false);
				int amount = rs.getInt("amount");

				items.put(product, amount);
			}	
			return items;
		});
		qe.setErrorMessage("Something went wrong while trying to get cart products...");
		return qe.execute(conn, MySQLQueries.GET_CART_PRODUCTS, false);

	}

	private Order parseOrder (Connection conn, ResultSet rs) throws SQLException, DBException  {
		Order order = ef.newOrder(getCartById(conn, rs.getLong("cart_id")));
		order.setId(rs.getLong("id"));
		order.setState(rs.getString("state"));
		order.setDateCreation(rs.getDate("date_creation"));
		order.setDateModified(rs.getDate("date_modified"));
		return order;
	}
}
