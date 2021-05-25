package com.eshop.model.dao.impl;

import java.sql.Connection;

import java.util.List;

import com.eshop.model.dao.OrdersDao;
import com.eshop.model.entity.Order;

public class JDBCOrdersDao implements OrdersDao {
	Connection connection;

	public JDBCOrdersDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (Order product) {

	}

	@Override
	public Order findById (long id) {
		return null;
	}

	@Override
	public List <Order> findAll () {
		return null;
	}

	@Override
	public void update (Order product) {

	}

	@Override
	public void delete (Order product) {

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
