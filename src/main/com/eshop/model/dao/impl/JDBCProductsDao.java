package com.eshop.model.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.eshop.model.ProductSearcher;
import com.eshop.model.dao.ProductsDao;
import com.eshop.model.dao.DBException;
import com.eshop.model.dao.SQL;
import com.eshop.model.dao.mapper.ProductMapper;
import com.eshop.model.entity.Product;

import java.util.logging.Logger;
import java.util.logging.Level;

public class JDBCProductsDao implements ProductsDao {
	Logger logger = Logger.getLogger(JDBCProductsDao.class.getName());
	Connection connection;

	public JDBCProductsDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (Product p) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			stmt.setString(k++, p.getName());
			stmt.setString(k++, p.getDescription());
			stmt.setString(k++, Integer.toString(p.getAmount()));
			stmt.setString(k++, p.getPrice().toString());
			stmt.setString(k++, p.getCategory());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					p.setId(rs.getLong(1));
				}
			}
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.CREATE_PRODUCT, sqle);
			throw new DBException (DBException.CREATE_PRODUCT, sqle);
		}
	}

	@Override
	public Product findById (long id) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_PRODUCT_BY_ID)) {
			stmt.setString(1, Long.toString(id));
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) return new ProductMapper().extractFromResultSet(rs); 
				else throw new DBException (DBException.PRODUCT_NOT_FOUND);
			}
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.FIND_PRODUCT, sqle);
			throw new DBException (DBException.FIND_PRODUCT, sqle);
		}
	}

	@Override
	public List <Product> findAll () throws DBException {
		try (Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL.SELECT_ALL_PRODUCTS)) {
			List <Product> products = new ArrayList <> ();
			ProductMapper mapper = new ProductMapper ();
			while (rs.next()) {
				Product p = mapper.extractFromResultSet(rs);
				products.add(p); 
			}
			return products;
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.FIND_PRODUCTS, sqle);
			throw new DBException (DBException.FIND_PRODUCTS, sqle);
		}
	}

	@Override
	public List <Product> findByPattern (ProductSearcher pattern) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(pattern.get())) {
			int k = 1;
			if (pattern.getName() != null) stmt.setString(k++, "%" + pattern.getName() + "%");
			if (pattern.getCategory() != null) stmt.setString(k++, "%" + pattern.getCategory() + "%");
			if (pattern.getPriceMax() != null) stmt.setString(k++, pattern.getPriceMax().toString());
			if (pattern.getPriceMin() != null) stmt.setString(k++, pattern.getPriceMin().toString());
			logger.info("stmt: " + stmt);
			try (ResultSet rs = stmt.executeQuery();) {
				List <Product> products = new ArrayList <> ();
				ProductMapper mapper = new ProductMapper ();
				while (rs.next()) {
					Product p = mapper.extractFromResultSet(rs);
					products.add(p); 
				}
				return products;
			}
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.FIND_PRODUCTS, sqle);
			throw new DBException (DBException.FIND_PRODUCTS, sqle);
		}
	}

	@Override
	public void update (Product p) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.UPDATE_PRODUCT)) {
			int k = 1;
			stmt.setString(k++, p.getName());
			stmt.setString(k++, p.getDescription());
			stmt.setString(k++, Integer.toString(p.getAmount()));
			stmt.setString(k++, p.getPrice().toString());
			stmt.setString(k++, p.getState().toString());
			stmt.setString(k++, p.getCategory());
			stmt.setString(k++, Long.toString(p.getId()));
			stmt.execute();
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.UPDATE_PRODUCT, sqle);
			throw new DBException (DBException.UPDATE_PRODUCT, sqle);
		}
	}

	@Override
	public void delete (Product p) throws DBException {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.DELETE_PRODUCT)) {
			stmt.setString(1, Long.toString(p.getId()));
			stmt.execute();
		}
		catch (SQLException sqle) {
			logger.log(Level.WARNING, DBException.DELETE_PRODUCT, sqle);
			throw new DBException (DBException.DELETE_PRODUCT, sqle);
		}

	}

	@Override
	public void close () {
		try {
			connection.close();
		}
		catch (Exception e) {
			logger.log(Level.WARNING, DBException.CLOSE_CONNECTION, e);
		}
	}
}


