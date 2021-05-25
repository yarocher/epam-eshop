package com.eshop.model.dao.impl;

import java.sql.Connection;

import java.util.List;

import com.eshop.model.dao.UsersDao;
import com.eshop.model.entity.User;

public class JDBCUsersDao implements UsersDao {
	Connection connection;

	public JDBCUsersDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (User product) {

	}

	@Override
	public User findById (long id) {
		return null;
	}

	@Override
	public List <User> findAll () {
		return null;
	}

	@Override
	public void update (User product) {

	}

	@Override
	public void delete (User product) {

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
