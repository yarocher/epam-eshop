package com.eshop.model.dao;

import java.util.List; 

import com.eshop.model.entity.User;

public interface UsersDao extends GenericDao <User> {

	User findByLogin (String login) throws DBException;

}
