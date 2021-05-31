package com.eshop.model.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.eshop.model.dao.DBException;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.UsersDao;
import com.eshop.model.dao.OrdersDao;
import com.eshop.model.dao.ProductsDao;

public class JDBCDaoFactory extends DaoFactory {
	private Connection getConnection () throws DBException{
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/eshop_test_db?user=login&password=password");
			//return DriverManager.getConnection("jdbc:h2:~/eshop-test-db;user=login;password=password;");
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.GET_CONNECTION, sqle);
		}
	}
	@Override
	public UsersDao createUsersDao () throws DBException {
		return new JDBCUsersDao(getConnection());	
	}
	@Override
	public OrdersDao createOrdersDao () throws DBException {
		return new JDBCOrdersDao(getConnection());	
	}
	@Override
	public ProductsDao createProductsDao () throws DBException {
		return new JDBCProductsDao(getConnection());	
	}
}
