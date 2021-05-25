package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.Order;

public class OrderMapper implements ObjectMapper <Order> {
	@Override
	public Order extractFromResultSet (ResultSet rs) throws SQLException {
		return null;
	}
}
