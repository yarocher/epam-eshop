package com.eshop.model.service;

import java.util.List;

import com.eshop.model.dao.DBException;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.UsersDao;
import com.eshop.model.entity.User;
import com.eshop.model.dao.OrdersDao;
import com.eshop.model.entity.Order;

public class UsersService {
	DaoFactory daoFactory = DaoFactory.getInstance();

	public void createUser (User u)throws DBException  {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			dao.create(u);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
	public User getUser (long id) throws DBException {
		try (UsersDao usersDao = daoFactory.createUsersDao(); 
			OrdersDao ordersDao = daoFactory.createOrdersDao()) {
			User u = usersDao.findById(id);
			u.orders().addAll(ordersDao.findUserOrders(u));
			return u;
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public User getUser (String login) throws DBException {
		try (UsersDao usersDao = daoFactory.createUsersDao(); 
			OrdersDao ordersDao = daoFactory.createOrdersDao()) {
			User u = usersDao.findByLogin(login);
			u.orders().addAll(ordersDao.findUserOrders(u));
			return u;
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public List <User> getUsers () throws DBException {
		try (UsersDao usersDao = daoFactory.createUsersDao();
			OrdersDao ordersDao = daoFactory.createOrdersDao()) {
			List <User> users = usersDao.findAll();
			for (User u: users) u.orders().addAll(ordersDao.findUserOrders(u));
			return users;
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	

	}
	public void updateUser (User u) throws DBException {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			dao.update(u);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
	public void deleteUser (User u) throws DBException {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			dao.delete(u);
		}
		catch (DBException dbe) {
			throw dbe;
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}	
	}
}
