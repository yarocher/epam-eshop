package com.eshop.model.dao;

public class DBException extends Exception {
	public static final String GET_CONNECTION = "Can't get connection...";

	public static final String CREATE_PRODUCT = "Can't create product...";
	public static final String FIND_PRODUCT = "Can't find product...";
	public static final String FIND_PRODUCTS = "Can't find products...";
	public static final String PRODUCT_NOT_FOUND = "Product not found";
	public static final String UPDATE_PRODUCT = "Can't update product...";
	public static final String DELETE_PRODUCT = "Can't delete product...";

	public static final String CREATE_ORDER = "Can't create order...";
	public static final String FIND_ORDER = "Can't find order...";
	public static final String FIND_ORDERS = "Can't find orders...";
	public static final String ORDER_NOT_FOUND = "Order not found";
	public static final String UPDATE_ORDER = "Can't update order...";
	public static final String DELETE_ORDER = "Can't delete order...";
	public static final String FIND_ORDER_ITEMS = "Can't find order items...";
	public static final String INSERT_ORDER_ITEM = "Can't insert order item...";

	public static final String CREATE_USER = "Can't create user...";
	public static final String FIND_USER = "Can't find user...";
	public static final String FIND_USERS = "Can't find users...";
	public static final String USER_NOT_FOUND = "Order not found";
	public static final String UPDATE_USER = "Can't update user...";
	public static final String DELETE_USER = "Can't delete user...";
	public static final String FIND_USER_ORDERS = "Can't find user orders...";

	public DBException (String msg) {
		super (msg);
	}
	public DBException (String msg, Throwable cause) {
		super (msg, cause);
	}
}
