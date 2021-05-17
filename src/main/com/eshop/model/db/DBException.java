package com.eshop.model.db;

public class DBException extends Exception {
	public DBException (String msg) {
		super(msg);
	}
	public DBException (String msg, Throwable cause) {
		super(msg, cause);
	}
}
