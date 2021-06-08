package com.eshop.model.dao;

import java.util.List;

public interface GenericDao <T> extends AutoCloseable {

	void create (T entity) throws DBException;
	T findById (long id) throws DBException;
	List <T> findAll () throws DBException;
	void update (T entity) throws DBException;
	void delete (T entity) throws DBException;
	void close ();

}
