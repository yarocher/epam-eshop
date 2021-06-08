package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.User;
import com.eshop.model.entity.UserState;
import com.eshop.model.entity.Role;

import com.eshop.model.dao.SQL;

public class UserMapper implements ObjectMapper <User> {

	@Override
	public User extractFromResultSet (ResultSet rs) throws SQLException {
		User u = new User ();
		u.setId(rs.getLong(SQL.ID));
		u.setLogin(rs.getString(SQL.LOGIN));
		u.setPassword(rs.getString(SQL.PASSWORD));
		String state = rs.getString(SQL.STATE);
		if (UserState.ACTIVE.toString().equals(state)) u.setState(UserState.ACTIVE);
		else if (UserState.BLOCKED.toString().equals(state)) u.setState(UserState.BLOCKED);
		String role = rs.getString(SQL.ROLE);
		if (Role.CUSTOMER.toString().equals(role)) u.setRole(Role.CUSTOMER);
		else if (Role.ADMIN.toString().equals(role)) u.setRole(Role.ADMIN);
		return u;
	}

}
