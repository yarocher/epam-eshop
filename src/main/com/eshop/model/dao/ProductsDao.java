package com.eshop.model.dao;

import com.eshop.model.dao.DBException;

import java.util.List;

import com.eshop.model.entity.Product;
import com.eshop.model.ProductSearcher;

public interface ProductsDao extends GenericDao <Product> {

	List <Product> findByPattern (ProductSearcher pattern) throws DBException;	

}
