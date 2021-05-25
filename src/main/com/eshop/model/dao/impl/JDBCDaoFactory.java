package com.eshop.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.UsersDao;
import com.eshop.model.dao.OrdersDao;
import com.eshop.model.dao.ProductsDao;

public class JDBCDaoFactory extends DaoFactory {
	private Connection getConnection () {
		try {
			return DriverManager.getConnection("jdbc:h2:~/eshop-test-db;user=login;password=password;");
			//return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=login&password=password");
		}
		catch (SQLException sqle) {
			throw new RuntimeException ("Can't get connection", sqle);
		}
	}
	@Override
	public UsersDao createUsersDao () {
		return new JDBCUsersDao(getConnection());	
	}
	@Override
	public OrdersDao createOrdersDao () {
		return new JDBCOrdersDao(getConnection());	
	}
	@Override
	public ProductsDao createProductsDao () {
		return new JDBCProductsDao(getConnection());	
	}
}
