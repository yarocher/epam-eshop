package com.eshop.model.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.List;
import java.util.ArrayList;

import com.eshop.model.entity.*;
import com.eshop.model.db.*;


public class UsersDAOMySQL implements UsersDAO{
	private String url = "jdbc:mysql://localhost:3306/mydb?user=login&password=password";
	private EntityFactory ef = EntityFactory.getInstance();

	@Override
	public void setURL (String url) {
		this.url = url;
	}

	@Override
	public Connection getConnection () throws DBException {
		try {
			return DriverManager.getConnection(url); 
		}
		catch (SQLException sqle) {
			throw new DBException ("Something went wrong while trying to get connection...", sqle);
		}
	}	
	@Override
	public List <User> getAllUsers () throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <List<User>> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			List <User> users = new ArrayList <> ();
			rs = stmt.executeQuery();

			while (rs.next()) {
				User user = ef.newUser(rs.getString("login"), rs.getString("password"));
				user.setId(rs.getLong("id"));
				UserData data = getUserData (conn, user);
				user.data().setFirstName(data.getFirstName());
				user.data().setLastName(data.getLastName());
				user.data().setEmail(data.getEmail());
				users.add(user);
			}	
			return users;
		});
		qe.setErrorMessage("Something went wrong while trying to get all users...");
		List <User> users = qe.execute(conn, MySQLQueries.GET_ALL_USERS, false);
		qe.close(conn);
		return users;
	}
	@Override
	public User getUserById (long id) throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <User> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(id));
			rs = stmt.executeQuery();
			if (rs.next()) {
				User user = ef.newUser(rs.getString("login"), rs.getString("password"));
				user.setId(rs.getLong("id"));
				user.setRole(rs.getString("role"));
				UserData data = getUserData (conn, user);
				user.data().setFirstName(data.getFirstName());
				user.data().setLastName(data.getLastName());
				user.data().setEmail(data.getEmail());
				return user;
			}
			else throw new DBException ("User with id " + id + " is not exist!"); 
			
		});
		qe.setErrorMessage("Something went wrong while trying to get user by id...");
		User user = qe.execute(conn, MySQLQueries.GET_USER_BY_ID, false);
		qe.close(conn);
		return user;
	}
	@Override
	public boolean insertUser (User user) throws DBException {
		Connection conn = getConnection();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRole());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			boolean res = rs.next();
			if (res) { 
				user.setId(rs.getLong(1));
				insertUserData(conn, user.data());
			}	
			return res;
		});
		qe.setErrorMessage("Something went wrong while trying to insert user...");
		boolean res = qe.execute(conn, MySQLQueries.INSERT_USER, true).booleanValue();
		qe.close(conn);
		return res;
	}
	@Override
	public boolean updateUser (User user) throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getRole());
			stmt.setString(4, Long.toString(user.getId()));
			updateUserData(conn, user.data());
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to update user...");
		boolean res = qe.execute(conn, MySQLQueries.UPDATE_USER, false);
		qe.close(conn);
		return res;
	}
	@Override
	public boolean deleteUser (User user) throws DBException {
		Connection conn = getConnection ();
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(user.getId()));	
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to delete user...");
		boolean res = qe.execute(conn, MySQLQueries.DELETE_USER, false);
		qe.close(conn);
		return res;
	}
	
	private UserData getUserData (Connection conn, User user) throws DBException{
		QueryExecutor <UserData> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, Long.toString(user.getId()));
			rs = stmt.executeQuery();
			if (rs.next()) {
				UserData data = new UserData();
				data.setFirstName(rs.getString("first_name"));
				data.setLastName(rs.getString("last_name"));
				data.setEmail(rs.getString("email"));
				data.setUserId(rs.getLong("user_id"));
				return data;
			}
			else throw new DBException ("User data of user " + user.getId() + " is not exist!"); 
			
		});
		qe.setErrorMessage("Something went wrong while trying to get user data...");
		return qe.execute(conn, MySQLQueries.GET_USER_DATA, false);
	}

	private boolean insertUserData (Connection conn, UserData data) throws DBException {
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, data.getFirstName());
			stmt.setString(2, data.getLastName());
			stmt.setString(3, data.getEmail());
			stmt.setString(4, Long.toString(data.getUserId()));
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to insert user data...");
		return qe.execute(conn, MySQLQueries.INSERT_USER_DATA, false).booleanValue();
	}

	private boolean updateUserData (Connection conn, UserData data) throws DBException {
		QueryExecutor <Boolean> qe = new QueryExecutor <> ();
		qe.setQuery((stmt, rs) -> {
			stmt.setString(1, data.getFirstName());
			stmt.setString(2, data.getLastName());
			stmt.setString(3, data.getEmail());
			stmt.setString(4, Long.toString(data.getUserId()));
			return stmt.execute();
		});
		qe.setErrorMessage("Something went wrong while trying to update user data...");
		return qe.execute(conn, MySQLQueries.UPDATE_USER_DATA, false).booleanValue();
	}
}
