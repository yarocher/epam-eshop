package com.eshop.model.db;

import java.sql.Connection;

import java.util.List;

import com.eshop.model.entity.*;
import com.eshop.model.db.*;

public interface OrdersDAO extends DAO {
	List <Order> getAllOrders () throws DBException;
	Order getOrderById (long id) throws DBException;
	Order getOrderById (Connection conn, long id, boolean terminal) throws DBException;
	List <Order> getUserOrders (User user) throws DBException;
	Cart getCurrentUserCart (User user) throws DBException;
	boolean insertCart (Cart cart, User user) throws DBException;	
	boolean deleteCart (Cart cart) throws DBException;
	boolean insertOrder (Order order, User user) throws DBException;	
	boolean updateOrder (Order order) throws DBException;
	boolean deleteOrder (Order order) throws DBException;
}
