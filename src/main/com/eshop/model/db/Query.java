package com.eshop.model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface Query <R> {
	R apply (PreparedStatement stmt, ResultSet rs) throws DBException, SQLException;	
}
