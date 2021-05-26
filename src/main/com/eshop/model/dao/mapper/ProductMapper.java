package com.eshop.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eshop.model.entity.Product;
import com.eshop.model.entity.ProductState;

public class ProductMapper implements ObjectMapper <Product> {
	@Override
	public Product extractFromResultSet (ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("id"));
		p.setName(rs.getString("name"));
		p.setDescription(rs.getString("description"));
		p.setPrice(rs.getDouble("price"));
		p.setAmount(rs.getInt("amount"));
		p.setDateCreated(rs.getDate("date_created"));
		p.setDateModified(rs.getDate("date_modified"));
		p.setCategory(rs.getString("category"));
		String state = rs.getString("state");
		if ("ON_SALE".equals(state)) p.setState(ProductState.ON_SALE);
		else if ("HIDDEN".equals(state)) p.setState(ProductState.HIDDEN);
		return p;
	}
}

