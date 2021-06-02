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

public class JDBCOrdersDao implements OrdersDao {
	Connection connection;

	public JDBCOrdersDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (Order o) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, Long.toString(o.getUser().getId()));
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					o.setId(rs.getLong(1));
					o.getUser().getOrders().add(o);
					for (Map.Entry <Product, Integer> entry: o.items().entrySet()) addOrderItem(o, entry.getKey(), entry.getValue());
				}
			}
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.CREATE_ORDER, sqle);
		}
	}

	private void addOrderItem (Order o, Product p, int amount) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.INSERT_ORDER_ITEM)) {
			int k = 1;
			stmt.setString(k++, Long.toString(o.getId()));
			stmt.setString(k++, Long.toString(p.getId()));
			stmt.setString(k++, Integer.toString(amount));
			stmt.execute();
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.INSERT_ORDER_ITEM, sqle);
		}
	}

	private void setOrderItems (Order o) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_ORDER_ITEMS)) {
			stmt.setString(1, Long.toString(o.getId()));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					ProductMapper mapper = new ProductMapper();
					Product p = mapper.extractFromResultSet(rs);
					Integer amount = rs.getInt("item_amount");
					o.items().put(p, amount);
				}
			}
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_ORDER_ITEMS, sqle);
		}
	}

	@Override
	public Order findById (long id) throws DBException {
		try (PreparedStatement stmtO = connection.prepareStatement(SQL.SELECT_ORDER_BY_ID);
				PreparedStatement stmtU = connection.prepareStatement(SQL.SELECT_USER_BY_ID);) {
			stmtO.setString(1, Long.toString(id));
			try (ResultSet rsO = stmtO.executeQuery()) {
				if (rsO.next()) {
					Order o = new OrderMapper().extractFromResultSet(rsO);
					setOrderItems(o);
					stmtU.setString(1, Long.toString(rsO.getLong("user_id")));
					try (ResultSet rsU = stmtU.executeQuery();) {
						if (rsU.next()) {
							User user = new UserMapper().extractFromResultSet(rsU);
							o.setUser(user);
							user.getOrders().add(o);
						}
						else throw new DBException (DBException.USER_NOT_FOUND);
					}
					return o; 
				}
				else throw new DBException (DBException.ORDER_NOT_FOUND);
			}
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_ORDER, sqle);
		}
	}

	@Override
	public List <Order> findAll () throws DBException {
		try (PreparedStatement stmtO = connection.prepareStatement(SQL.SELECT_ALL_ORDERS);
			ResultSet rsO = stmtO.executeQuery();
			PreparedStatement stmtU = connection.prepareStatement(SQL.SELECT_USER_BY_ID);) {
			List <Order> orders = new ArrayList <> ();
			OrderMapper mapper = new OrderMapper ();
			while (rsO.next()) {
				Order o = new OrderMapper().extractFromResultSet(rsO);
				setOrderItems(o);
				stmtU.setString(1, Long.toString(rsO.getLong("user_id")));
				try (ResultSet rsU = stmtU.executeQuery()) {
					if (rsU.next()) {
						User user = new UserMapper().extractFromResultSet(rsU);
						o.setUser(user);
						user.getOrders().add(o);
					}
					else throw new DBException (DBException.USER_NOT_FOUND);
				}
				orders.add(o); 
			}
			return orders;
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_ORDERS, sqle);
		}
	}

	@Override
	public List <Order> findUserOrders (User u) throws DBException {
		try (PreparedStatement stmtO = connection.prepareStatement(SQL.SELECT_USER_ORDERS);
				PreparedStatement stmtU = connection.prepareStatement(SQL.SELECT_USER_BY_ID);) {
			stmtO.setString(1, Long.toString(u.getId()));
			try (ResultSet rsO = stmtO.executeQuery()) {
				List <Order> orders = new ArrayList <> ();
				OrderMapper mapper = new OrderMapper ();
				while (rsO.next()) {
					Order o = new OrderMapper().extractFromResultSet(rsO);
					setOrderItems(o);
					stmtU.setString(1, Long.toString(rsO.getLong("user_id")));
					try (ResultSet rsU = stmtU.executeQuery();) {
						if (rsU.next()) {
							User user = new UserMapper().extractFromResultSet(rsU);
							o.setUser(user);
							user.getOrders().add(o);
						}
						else throw new DBException (DBException.USER_NOT_FOUND);
					}
					orders.add(o); 
				}
				return orders;
			}
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_USER_ORDERS, sqle);
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
			throw new DBException (DBException.DELETE_ORDER, sqle);
		}

	}

	@Override
	public void close () {
		try {
			connection.close();
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}
	}
}
