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
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
	public Order getOrder (long id) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = dao.findById(id);
			return o;
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public List <Order> getOrders () throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			return dao.findAll();
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public void updateOrder (Order o) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			dao.update(o);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public void deleteOrder (Order o) throws DBException {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			dao.delete(o);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
}
