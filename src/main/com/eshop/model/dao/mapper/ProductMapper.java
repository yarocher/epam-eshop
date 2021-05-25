package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.Product;

public class ProductMapper implements ObjectMapper <Product> {
	@Override
	public Product extractFromResultSet (ResultSet rs) throws SQLException {
		return null;
	}
}

