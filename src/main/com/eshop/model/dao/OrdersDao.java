package com.eshop.model.dao;

import java.util.List;

import com.eshop.model.entity.Order;
import com.eshop.model.entity.User;

public interface OrdersDao extends GenericDao <Order> {

	List <Order> findUserOrders (User u) throws DBException;

}
