package com.eshop.model.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.eshop.model.dao.SQL;
import com.eshop.model.dao.DBException;
import com.eshop.model.dao.UsersDao;
import com.eshop.model.dao.mapper.UserMapper;
import com.eshop.model.dao.mapper.OrderMapper;
import com.eshop.model.entity.User;
import com.eshop.model.entity.Order;

public class JDBCUsersDao implements UsersDao {
	Connection connection;

	public JDBCUsersDao (Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create (User u) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			stmt.setString(k++, u.getLogin());
			stmt.setString(k++, u.getPassword());
			stmt.setString(k++, u.getRole().toString());
			stmt.execute();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					u.setId(rs.getLong(1));
				}
			}
		}
		catch (SQLException sqle) {
			if (sqle.getErrorCode() == 1062) throw new DBException (DBException.NOT_UNIQUE_LOGIN, sqle);
			throw new DBException (DBException.CREATE_USER, sqle);
		}

	}

	@Override
	public User findById (long id) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USER_BY_ID)) {
			stmt.setString(1, Long.toString(id));
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User u = new UserMapper().extractFromResultSet(rs); 
					return u;
				}
				else throw new DBException (DBException.USER_NOT_FOUND);
			}
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_USER, sqle);
		}
	}

	@Override
	public User findByLogin (String login) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.SELECT_USER_BY_LOGIN)) {
			stmt.setString(1, login);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User u = new UserMapper().extractFromResultSet(rs); 
					return u;
				}
				else throw new DBException (DBException.USER_NOT_FOUND);
			}
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_USER, sqle);
		}
	}

	@Override
	public List <User> findAll () throws DBException  {
		try (Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(SQL.SELECT_ALL_USERS)) {
			List <User> users = new ArrayList <> ();
			UserMapper mapper = new UserMapper ();
			while (rs.next()) {
				User u = mapper.extractFromResultSet(rs);
				users.add(u); 
			}
			return users;
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.FIND_USERS, sqle);
		}
	}

	@Override
	public void update (User u) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.UPDATE_USER)) {
			int k = 1;
			stmt.setString(k++, u.getLogin());
			stmt.setString(k++, u.getPassword());
			stmt.setString(k++, u.getState().toString());
			stmt.setString(k++, u.getRole().toString());
			stmt.setString(k++, Long.toString(u.getId()));
			stmt.execute();
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.UPDATE_USER, sqle);
		}
	}

	@Override
	public void delete (User u) throws DBException  {
		try (PreparedStatement stmt = connection.prepareStatement(SQL.DELETE_USER)) {
			stmt.setString(1, Long.toString(u.getId()));
			stmt.execute();
		}
		catch (SQLException sqle) {
			throw new DBException (DBException.DELETE_USER, sqle);
		}
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
