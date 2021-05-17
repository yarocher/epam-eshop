package com.eshop.model.db;

public abstract class DAOFactory {
	public abstract ProductsDAO newProductsDAO ();
	public abstract OrdersDAO newOrdersDAO ();
	public abstract UsersDAO newUsersDAO ();
}
