package com.eshop.model.db;

public class MySQLQueries {
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
	public static final String GET_ALL_CATEGORIES = "SELECT * FROM category;";
	public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
	public static final String GET_CATEGORY_BY_ID = "SELECT * FROM category  WHERE id = ?;";
	public static final String GET_CATEGORY_PRODUCTS = "SELECT * FROM product WHERE category_id = ?;";
	public static final String GET_PRODUCT_ATTRIBUTES = "SELECT attribute_key.id AS key_id, attribute_key.name AS `key`, attribute_value.id AS value_id, attribute_value.name AS value " +
		"FROM attribute " + "INNER JOIN attribute_key " + 
		"ON attribute.key_id = attribute_key.id " +
	       	"INNER JOIN attribute_value " + 
		"ON attribute.value_id = attribute_value.id " + 
		"WHERE product_id = ?;";

	public static final String GET_CATEGORY_KEYS = "SELECT * FROM attribute_key " +
		"INNER JOIN category_key " +
		"ON category_key.key_id = attribute_key.id " + 
		"WHERE category_key.category_id = ?;";

	public static final String GET_KEY_VALUES = "SELECT * FROM attribute_value " +
		"INNER JOIN key_value " +
		"ON attribute_value.id = key_value.value_id " + 
		"WHERE key_value.key_id = ?;";

	public static final String GET_KEY_BY_NAME = "SELECT * FROM attribute_key WHERE name = ?;";
	public static final String GET_VALUE_BY_NAME = "SELECT * FROM attribute_value WHERE name = ?;";

	public static final String INSERT_VALUE = "INSERT INTO attribute_value (name) VALUES (?);";
	public static final String INSERT_KEY = "INSERT INTO attribute_key (name) VALUES (?);";
	public static final String INSERT_CATEGORY = "INSERT INTO category (name) VALUES (?);";
	public static final String ADD_VALUE_TO_KEY = "INSERT INTO key_value (key_id, value_id) VALUES (?, ?);";
	public static final String ADD_KEY_TO_CATEGORY = "INSERT INTO category_key (category_id, key_id) VALUES (?, ?);";

	public static final String INSERT_PRODUCT = "INSERT INTO product (name, description, price, amount, category_id) VALUES (?, ?, ?, ?, ?);";
	public static final String INSERT_ATTRIBUTE = "INSERT INTO attribute (product_id, category_id, key_id, value_id) VALUES (?, ?, ?, ?);";

	public static final String UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE id = ?;";
	public static final String UPDATE_PRODUCT = "UPDATE product SET name = ?, description = ?, price = ?, amount = ?, state = ? WHERE id = ?;";

	public static final String DELETE_CATEGORY = "DELETE FROM category WHERE id = ?;";
	public static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?;";

	public static final String NAME_FILTER = " product.name = ?";
	public static final String PRICE_MAX_FILTER = " price <= ? ";
	public static final String PRICE_MIN_FILTER = " price >= ? ";
	public static final String ATTRIBUTE_FILTER = " attribute_key.name = ? AND attribute_value.name = ?";

	public static final String PRODUCT_PATTERN = "SELECT DISTINCT product.id FROM product " +
		"INNER JOIN attribute " +
		"ON attribute.product_id = product.id " +
		"INNER JOIN attribute_key " +
		"ON attribute.key_id = attribute_key.id " +
		"INNER JOIN attribute_value " +
		"ON attribute.value_id = attribute_value.id ";

	public static final String FILTER = " WHERE ";
	public static final String AND = " AND ";

	public static final String GET_ALL_USERS = "SELECT * FROM user";
	public static final String GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	public static final String GET_USER_DATA = "SELECT * FROM user_data WHERE user_id = ?";
	public static final String INSERT_USER = "INSERT INTO user (login, password) VALUES (?, ?)";
	public static final String INSERT_USER_DATA = "INSERT INTO user_data (first_name, last_name, email, user_id) VALUES (?, ?, ?, ?)";
	public static final String UPDATE_USER = "UPDATE user SET login = ?, password = ? WHERE id = ?";
	public static final String UPDATE_USER_DATA  = "UPDATE user_data SET first_name = ?, last_name = ?, email = ? WHERE user_id = ?";
	public static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

	public static final String GET_CART_BY_ID = "SELECT * FROM cart WHERE id = ?";
	public static final String GET_CART_PRODUCTS = "SELECT cart_product.product_id, cart_product.amount FROM cart_product WHERE cart_id = ?";
	public static final String GET_CURRENT_USER_CART = "SELECT cart.id FROM cart LEFT JOIN `order` ON cart.id = `order`.cart_id WHERE cart.user_id = ? AND `order`.id IS NULL";
	public static final String GET_ORDER_BY_ID = "SELECT * FROM `order` WHERE id = ?";
	public static final String GET_ALL_ORDERS = "SELECT * FROM `order`";
	public static final String GET_USER_ORDERS = "SELECT * FROM `order` WHERE user_id = ?";

	public static final String INSERT_CART = "INSERT INTO cart (user_id) VALUES (?)";
	public static final String DELETE_CART = "DELETE FROM cart WHERE id = ?";
	public static final String INSERT_ORDER = "INSERT INTO `order` (cart_id, user_id) VALUES (?, ?)";
	public static final String UPDATE_ORDER = "UPDATE `order` SET state = ? WHERE id = ?";
	public static final String DELETE_ORDER = "DELETE FROM `order` WHERE id = ?";


}
