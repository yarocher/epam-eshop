package com.eshop.model.service;

import java.util.List;

import com.eshop.model.dao.DBException;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.OrdersDao;
import com.eshop.model.entity.Order;

public class OrdersService {
	DaoFactory daoFactory = DaoFactory.getInstance();

	public void createOrder (Order o) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			dao.create(o);
		}
	}
	public Order getOrder (long id) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = dao.findById(id);
			return o;
		}
	}
	public List <Order> getOrders () throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			return dao.findAll();
		}
	}
	public void updateOrder (Order o) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			dao.update(o);
		}
	}
	public void deleteOrder (Order o) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			dao.delete(o);
		}
	}
}
