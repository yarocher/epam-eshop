package com.eshop.model.service;

public class ServiceException extends Exception {
	public ServiceException (String msg) {
		super (msg);
	}
	public ServiceException (Throwable cause) {
		super (cause);
	}
	public ServiceException (String msg, Throwable cause) {
		super (msg, cause);
	}
	
}
