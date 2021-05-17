package com.eshop.model.db;

public class MySQLQueries {
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM product;";
	public static final String GET_ALL_CATEGORIES = "SELECT * FROM category;";
	public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
	public static final String GET_CATEGORY_BY_ID = "SELECT * FROM category  WHERE id = ?;";
	public static final String GET_CATEGORY_PRODUCTS = "SELECT * FROM product WHERE category_id = ?;";
	public static final String GET_PRODUCT_ATTRIBUTES = "SELECT attribute_key.name AS `key`, attribute_value.name AS value FROM attribute " +
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

}
