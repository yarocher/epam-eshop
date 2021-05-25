package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.User;

public class UserMapper implements ObjectMapper <User> {
	@Override
	public User extractFromResultSet (ResultSet rs) throws SQLException {
		return null;
	}
}
