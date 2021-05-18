package com.eshop.model.db;

public class MySQLQueries {
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
	public static final String GET_ALL_CATEGORIES = "SELECT * FROM category;";
	public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
	public static final String GET_CATEGORY_BY_ID = "SELECT * FROM category  WHERE id = ?;";
	public static final String GET_CATEGORY_PRODUCTS = "SELECT * FROM product WHERE category_id = ?;";
	public static final String GET_PRODUCT_ATTRIBUTES = "SELECT attribute_key.id AS key_id, attribute_key.name AS `key`, attribute_value.id AS value_id, attribute_value.name AS value " +
		"FROM attribute " +
	       	"INNER JOIN attribute_key " + 
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

}
