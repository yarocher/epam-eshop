package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.Order;
import com.eshop.model.entity.OrderState;

public class OrderMapper implements ObjectMapper <Order> {
	@Override
	public Order extractFromResultSet (ResultSet rs) throws SQLException {
		Order o = new Order ();
		o.setId(rs.getLong("id"));
		o.setDateCreated(rs.getDate("date_created"));
		o.setDateModified(rs.getDate("date_modified"));
		String state = rs.getString("state");
		if ("NEW".equals(state)) o.setState(OrderState.NEW);
		else if ("PAYED".equals(state)) o.setState(OrderState.PAYED);
		else if ("CANCELLED".equals(state)) o.setState(OrderState.CANCELLED);
		return o;
	}
}
