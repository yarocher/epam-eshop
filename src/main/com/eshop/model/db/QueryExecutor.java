package com.eshop.model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecutor <R> {
	private Query <R> query;
	private String msg;

	public String getErrorMessage () {return msg;}
	public void setErrorMessage (String msg) {
		this.msg = msg;
	}

	public void setQuery (Query<R> query) {
		this.query = query;
	}

	public R execute (Connection conn, String sql, boolean genKeys) throws DBException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = (genKeys) ? conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) : conn.prepareStatement(sql);

			return query.apply(stmt, rs);
		}
		catch (SQLException sqle) {
			throw new DBException (msg, sqle);
		}
		finally {
			close(rs);
			close(stmt);
		}
	}

	public void close (AutoCloseable res) {
		try {
			if (res != null) res.close();
		}
		catch (Exception ioe) {
			System.out.println("Can't close resource " + res);
		}
	}


}
