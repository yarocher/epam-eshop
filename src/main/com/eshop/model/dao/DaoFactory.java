package com.eshop.model.dao;

import com.eshop.model.dao.impl.*;

public abstract class DaoFactory {
	private static DaoFactory instance;
//	private DaoFactory () {}
	public static synchronized DaoFactory getInstance () {
		if (instance == null) instance = new JDBCDaoFactory ();
		return instance;
	}
	
	public abstract ProductsDao createProductsDao ();
	public abstract UsersDao createUsersDao ();
	public abstract OrdersDao createOrdersDao ();
}
