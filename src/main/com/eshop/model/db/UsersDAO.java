package com.eshop.model.db;

import java.util.List;

import com.eshop.model.entity.*;
import com.eshop.model.db.*;


public interface UsersDAO extends DAO {
	List <User> getAllUsers () throws DBException;
	User getUserById (long id) throws DBException;
	boolean insertUser (User user) throws DBException;	
	boolean updateUser (User user) throws DBException;
	boolean deleteUser (User user) throws DBException;
}
