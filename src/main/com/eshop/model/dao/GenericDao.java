package com.eshop.model.dao;

import java.util.List;

public interface GenericDao <T> extends AutoCloseable {
	void create (T entity);
	T findById (long id);
	List <T> findAll ();
	void update (T entity);
	void delete (T entity);
}
