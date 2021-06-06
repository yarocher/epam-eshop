package com.eshop.model.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.eshop.model.dao.SQL;
import com.eshop.model.dao.DBException;
import com.eshop.model.dao.OrdersDao;
import com.eshop.model.entity.Order;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Product;
import com.eshop.model.dao.mapper.OrderMapper;
import com.eshop.model.dao.mapper.UserMapper;
import com.eshop.model.dao.mapper.ProductMapper;

import java.util.logging.Logger;
import java.util.logging.Level;

public class JDBCOrdersDao implements OrdersDao {
	Logger logger = Logger.getLogger(JDBCOrdersDao.class.getName());
	Connection connection;

	public JDBCOrdersDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (Order o) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
			connection.setAutoCommit(false);
			stmt.setString(1, Long.toString(o.getUser().getId()));
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					o.setId(rs.getLong(1));
					o.getUser().getOrders().add(o);
					for (Map.Entry <Product, Integer> entry: o.getItems().entrySet()) addOrderItem(o, entry.getKey(), entry.getValue());
				}
			}
			connection.commit();
		}
		catch (SQLException sqle) {
			rollback();
			logger.log(Level.WARNING, DBException.CREATE_ORDER, sqle);
			throw new DBException (DBException.CREATE_ORDER, sqle);
		}
		finally {
			setAutoCommitTrue();
		}
	}

	private void addOrderItem (Order o, Product p, int amount) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.INSERT_ORDER_ITEM)) {
			int k = 1;
			stmt.setString(k++, Long.toString(o.getId()));
			stmt.setString(k++, Long.toString(p.getId()));
			stmt.setString(k++, Integer.toString(amount));
			stmt.execute();
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.INSERT_ORDER_ITEM, sqle);
			throw new SQLException (DBException.INSERT_ORDER_ITEM, sqle);
		}
	}

	private void setOrderItems (Order o) throws SQLException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_ORDER_ITEMS)) {
			stmt.setString(1, Long.toString(o.getId()));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ProductMapper mapper = new ProductMapper();
					Product p = mapper.extractFromResultSet(rs);
					Integer amount = rs.getInt(SQL.ITEM_AMOUNT);
					o.getItems().put(p, amount);
				}
			}
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.FIND_ORDER_ITEMS, sqle);
			throw new SQLException (DBException.FIND_ORDER_ITEMS, sqle);
		}
	}

	@Override
	public Order findById (long id) throws DBException {
		Order o = null; 
		try (PreparedStatement stmtOrder = connection.prepareStatement(SQL.SELECT_ORDER_BY_ID);
				PreparedStatement stmtUser = connection.prepareStatement(SQL.SELECT_USER_BY_ID);) {
			connection.setAutoCommit(false);
			stmtOrder.setString(1, Long.toString(id));
			try (ResultSet rsOrder = stmtOrder.executeQuery()) {
				if (rsOrder.next()) {
					o = new OrderMapper().extractFromResultSet(rsOrder);
					setOrderItems(o);
					stmtUser.setString(1, Long.toString(rsOrder.getLong(SQL.USER_ID)));
					try (ResultSet rsUser = stmtUser.executeQuery();) {
						if (rsUser.next()) {
							User user = new UserMapper().extractFromResultSet(rsUser);
							o.setUser(user);
							user.getOrders().add(o);
						}
						else throw new DBException (DBException.USER_NOT_FOUND);
					}
				}
				else throw new DBException (DBException.ORDER_NOT_FOUND);
			}
			connection.commit();
			return o;
		}
		catch (SQLException sqle) {
			rollback();
			logger.log(Level.WARNING, DBException.FIND_ORDER, sqle);
			throw new DBException (DBException.FIND_ORDER, sqle);
		}
		finally {
			setAutoCommitTrue();
		}
	}

	@Override
	public List <Order> findAll () throws DBException {
		List <Order> orders = new ArrayList <> ();
		try (PreparedStatement stmtOrder = connection.prepareStatement(SQL.SELECT_ALL_ORDERS);
				ResultSet rsOrder = stmtOrder.executeQuery();
				PreparedStatement stmtUser = connection.prepareStatement(SQL.SELECT_USER_BY_ID);) {
			connection.setAutoCommit(false);
			OrderMapper mapper = new OrderMapper ();
			while (rsOrder.next()) {
				Order o = new OrderMapper().extractFromResultSet(rsOrder);
				setOrderItems(o);
				stmtUser.setString(1, Long.toString(rsOrder.getLong(SQL.USER_ID)));
				try (ResultSet rsUser = stmtUser.executeQuery()) {
					if (rsUser.next()) {
						User user = new UserMapper().extractFromResultSet(rsUser);
						o.setUser(user);
						user.getOrders().add(o);
					}
					else throw new DBException (DBException.USER_NOT_FOUND);
				}
				orders.add(o); 
			}
			connection.commit();
			return orders;
		}
		catch (SQLException sqle) {
			rollback();
			logger.log(Level.WARNING, DBException.FIND_ORDERS, sqle);
			throw new DBException (DBException.FIND_ORDERS, sqle);
		}
		finally {
			setAutoCommitTrue();
		}
	}

	@Override
	public List <Order> findUserOrders (User u) throws DBException {
		List <Order> orders = new ArrayList <> ();
		try (PreparedStatement stmtOrder = connection.prepareStatement(SQL.SELECT_USER_ORDERS);
				PreparedStatement stmtUser = connection.prepareStatement(SQL.SELECT_USER_BY_ID);) {
			connection.setAutoCommit(false);
			stmtOrder.setString(1, Long.toString(u.getId()));
			try (ResultSet rsOrder = stmtOrder.executeQuery()) {
				OrderMapper mapper = new OrderMapper ();
				while (rsOrder.next()) {
					Order o = new OrderMapper().extractFromResultSet(rsOrder);
					setOrderItems(o);
					stmtUser.setString(1, Long.toString(rsOrder.getLong(SQL.USER_ID)));
					try (ResultSet rsUser = stmtUser.executeQuery();) {
						if (rsUser.next()) {
							User user = new UserMapper().extractFromResultSet(rsUser);
							o.setUser(user);
							user.getOrders().add(o);
						}
						else throw new DBException (DBException.USER_NOT_FOUND);
					}
					orders.add(o); 
				}
			}
			connection.commit();
			return orders;
		}
		catch (SQLException sqle) {
			rollback();
			logger.log(Level.WARNING, DBException.FIND_USER_ORDERS, sqle);
			throw new DBException (DBException.FIND_USER_ORDERS, sqle);
		}
		finally {
			setAutoCommitTrue();
		}
	}

	@Override
	public void update (Order o)  throws DBException{
		try (PreparedStatement stmt = connection.prepareStatement(SQL.UPDATE_ORDER)) {
			int k = 1;
			stmt.setString(k++, o.getState().toString());
			stmt.setString(k++, Long.toString(o.getId()));
			stmt.execute();
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.UPDATE_ORDER, sqle);
			throw new DBException (DBException.UPDATE_ORDER, sqle);
		}

	}

	@Override
	public void delete (Order o) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.DELETE_ORDER)) {
			stmt.setString(1, Long.toString(o.getId()));
			stmt.execute();
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.DELETE_ORDER, sqle);
			throw new DBException (DBException.DELETE_ORDER, sqle);
		}

	}

	@Override
	public void close () {
		try {
			connection.close();
		}
		catch (Exception e) {
			logger.log(Level.WARNING, DBException.CLOSE_CONNECTION, e);
		}
	}

	private void rollback () {
		try {
			connection.rollback();
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.ROLLBACK, sqle);
		}
	}

	private void setAutoCommitTrue() {
		try {
			connection.setAutoCommit(true);
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.RESET_AUTOCOMMIT, sqle);
		}
	}
}
