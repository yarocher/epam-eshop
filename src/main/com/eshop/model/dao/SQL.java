package com.eshop.model.dao;

public class SQL {
	public static final String INSERT_PRODUCT = "INSERT INTO product (name, description, amount, price, category) VALUES (?, ?, ?, ?, ?)";
	public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
	public static final String SELECT_PRODUCT_BY_ID = SELECT_ALL_PRODUCTS + " WHERE product.id = ?";
	public static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, description = ?, amount = ?, price = ?, state = ?, category = ?  WHERE id = ?";
	public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";

	public static final String INSERT_ORDER = "INSERT INTO `order` (user_id) VALUES (?)";
	public static final String SELECT_ALL_ORDERS = "SELECT * FROM `order`";
	public static final String SELECT_USER_ORDERS = "SELECT * FROM `order` WHERE user_id = ?";
	public static final String SELECT_ORDER_BY_ID = SELECT_ALL_ORDERS + " WHERE id = ?";
	public static final String UPDATE_ORDER = "UPDATE `order` SET state = ? WHERE id = ?";
	public static final String DELETE_ORDER = "DELETE FROM `order` WHERE id = ?";

	public static final String INSERT_ORDER_ITEM = "INSERT INTO order_item (order_id, product_id, amount) VALUES (?, ?, ?)";
	public static final String SELECT_ORDER_ITEMS = "SELECT product.*, order_item.amount as item_amount FROM order_item INNER JOIN product ON order_item.product_id = product.id WHERE order_id = ?";

	public static final String INSERT_USER = "INSERT INTO user (login, password, role) VALUES (?, ?, ?)";
	public static final String SELECT_ALL_USERS = "SELECT * FROM user";
	public static final String SELECT_USER_BY_ID = SELECT_ALL_USERS + " WHERE id = ?";
	public static final String SELECT_USER_BY_LOGIN = SELECT_ALL_USERS + " WHERE login = ?";
	public static final String UPDATE_USER = "UPDATE user SET login = ?, password = ?, state = ?, role = ? WHERE id = ?";
	public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

	public static final String NAME_FILTER = " product.name LIKE ?";
	public static final String CATEGORY_FILTER = " category LIKE ?";
	public static final String PRICE_MAX_FILTER = " price <= ? ";
	public static final String PRICE_MIN_FILTER = " price >= ? ";

	public static final String FILTER = " WHERE ";
	public static final String AND = " AND ";

	public static final String SORT_BY = " ORDER BY ";
	public static final String DESC = " DESC";

	public static final String ID = "id";
	public static final String DATE_CREATED = "date_created";
	public static final String DATE_MODIFIED = "date_modified";
	public static final String NAME = "name";
	public static final String CATEGORY = "category";
	public static final String DESCRIPTION = "description";
	public static final String PRICE = "price";
	public static final String AMOUNT = "amount";
	public static final String STATE = "state";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String ROLE = "role";


	public static final String USER_ID = "user_id";
	public static final String ITEM_AMOUNT = "item_amount";
}
