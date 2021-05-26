package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.User;
import com.eshop.model.entity.UserState;
import com.eshop.model.entity.Role;

public class UserMapper implements ObjectMapper <User> {
	@Override
	public User extractFromResultSet (ResultSet rs) throws SQLException {
		User u = new User ();
		u.setId(rs.getLong("id"));
		u.setLogin(rs.getString("login"));
		u.setPassword(rs.getString("password"));
		String state = rs.getString("state");
		if ("ACTIVE".equals(state)) u.setState(UserState.ACTIVE);
		else if ("BLOCKED".equals(state)) u.setState(UserState.BLOCKED);
		String role = rs.getString("role");
		if ("CUSTOMER".equals(role)) u.setRole(Role.CUSTOMER);
		else if ("ADMIN".equals(role)) u.setRole(Role.ADMIN);
		return u;
	}
}
