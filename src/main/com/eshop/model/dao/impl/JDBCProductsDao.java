package com.eshop.model.dao.impl;

import java.sql.Connection;

import java.util.List;

import com.eshop.model.dao.ProductsDao;
import com.eshop.model.entity.Product;

public class JDBCProductsDao implements ProductsDao {
	Connection connection;

	public JDBCProductsDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (Product product) {

	}

	@Override
	public Product findById (long id) {
		return null;
	}

	@Override
	public List <Product> findAll () {
		return null;
	}

	@Override
	public void update (Product product) {

	}

	@Override
	public void delete (Product product) {

	}

	@Override
	public void close () {
		try {
			connection.close();
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}
	}
}


