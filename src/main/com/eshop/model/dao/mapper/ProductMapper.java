package com.eshop.model.dao.mapper;

import java.time.LocalDateTime;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.Product;
import com.eshop.model.entity.ProductState;

import com.eshop.model.dao.SQL;

public class ProductMapper implements ObjectMapper <Product> {
	@Override
	public Product extractFromResultSet (ResultSet rs) throws SQLException {
		LocalDateTime dateCreated = new java.sql.Timestamp(rs.getDate(SQL.DATE_CREATED).getTime()).toLocalDateTime();
		LocalDateTime dateModified = new java.sql.Timestamp(rs.getDate(SQL.DATE_MODIFIED).getTime()).toLocalDateTime();
		Product p = new Product();
		p.setId(rs.getLong(SQL.ID));
		p.setName(rs.getString(SQL.NAME));
		p.setDescription(rs.getString(SQL.DESCRIPTION));
		p.setPrice(rs.getBigDecimal(SQL.PRICE));
		p.setAmount(rs.getInt(SQL.AMOUNT));
		p.setDateCreated(dateCreated);
		p.setDateModified(dateModified);
		p.setCategory(rs.getString(SQL.CATEGORY));
		String state = rs.getString(SQL.STATE);
		if (ProductState.ON_SALE.toString().equals(state)) p.setState(ProductState.ON_SALE);
		else if (ProductState.HIDDEN.toString().equals(state)) p.setState(ProductState.HIDDEN);
		return p;
	}
}

