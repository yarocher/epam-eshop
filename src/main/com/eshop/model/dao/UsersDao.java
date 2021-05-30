package com.eshop.model.dao;

import java.util.List; 

import com.eshop.model.entity.User;
import com.eshop.model.entity.Order;

public interface UsersDao extends GenericDao <User> {
	User findByLogin (String login) throws DBException;
}
