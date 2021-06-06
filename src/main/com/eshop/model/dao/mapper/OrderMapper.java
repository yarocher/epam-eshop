package com.eshop.model.dao.mapper;

import java.time.LocalDateTime;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.Order;
import com.eshop.model.entity.OrderState;

import com.eshop.model.dao.SQL;

public class OrderMapper implements ObjectMapper <Order> {
	@Override
	public Order extractFromResultSet (ResultSet rs) throws SQLException {
		LocalDateTime dateCreated = new java.sql.Timestamp(rs.getDate(SQL.DATE_CREATED).getTime()).toLocalDateTime();
		LocalDateTime dateModified = new java.sql.Timestamp(rs.getDate(SQL.DATE_MODIFIED).getTime()).toLocalDateTime();
		Order o = new Order ();
		o.setId(rs.getLong(SQL.ID));
		o.setDateCreated(dateCreated);
		o.setDateModified(dateModified);
		String state = rs.getString(SQL.STATE);
		if (OrderState.NEW.toString().equals(state)) o.setState(OrderState.NEW);
		else if (OrderState.PAYED.toString().equals(state)) o.setState(OrderState.PAYED);
		else if (OrderState.CANCELLED.toString().equals(state)) o.setState(OrderState.CANCELLED);
		return o;
	}
}
