package com.eshop.model.db;

import java.sql.Connection;

public interface DAO {
	void setURL (String URL);
	Connection getConnection () throws DBException;
}
