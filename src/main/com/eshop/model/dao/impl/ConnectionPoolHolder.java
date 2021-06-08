package com.eshop.model.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ConnectionPoolHolder {

	private static Logger logger = Logger.getLogger(ConnectionPoolHolder.class.getName());

	private static DataSource ds = new DataSource();

	private static ConnectionPoolHolder instance;
	private ConnectionPoolHolder () {}

	public static ConnectionPoolHolder getInstance () {
		if (instance == null) {
			synchronized (ConnectionPoolHolder.class) {
				instance = new ConnectionPoolHolder ();
				PoolProperties p = new PoolProperties();
				
				//configuration should be here, singleton is hard to test
				//trainer sayed I should not bother now

				//p.setUrl("jdbc:h2:~/eshop-test-db;MVCC=TRUE;LOCK_TIMEOUT=30000;user=login;password=password");
				//p.setDriverClassName("org.h2.Driver");
				p.setUrl("jdbc:mysql://localhost:3306/eshop?user=login&password=password");
				p.setDriverClassName("com.mysql.cj.jdbc.Driver");

				ds.setPoolProperties(p);
			}
		}
		return instance;
	}	

	public Connection getConnection () throws SQLException{
		return ds.getConnection();
	}

}
