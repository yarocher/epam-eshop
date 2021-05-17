package com.eshop.model.db;

public class DAOFactoryMySQL extends DAOFactory {
	public ProductsDAO newProductsDAO () {
		return new ProductsDAOMySQL ();	
	}
	public OrdersDAO newOrdersDAO () {
		return new OrdersDAOMySQL ();	
	}
	public UsersDAO newUsersDAO () {
		return new UsersDAOMySQL ();	
	}
}
