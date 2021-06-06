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
	}
	public User getUser (long id) throws DBException {
		try (UsersDao usersDao = daoFactory.createUsersDao(); 
			OrdersDao ordersDao = daoFactory.createOrdersDao()) {
			User u = usersDao.findById(id);
			u.getOrders().addAll(ordersDao.findUserOrders(u));
			return u;
		}
	}
	public User getUser (String login) throws DBException {
		try (UsersDao usersDao = daoFactory.createUsersDao(); 
			OrdersDao ordersDao = daoFactory.createOrdersDao()) {
			User u = usersDao.findByLogin(login);
			u.getOrders().addAll(ordersDao.findUserOrders(u));
			return u;
		}
	}
	public List <User> getUsers () throws DBException {
		try (UsersDao usersDao = daoFactory.createUsersDao();
			OrdersDao ordersDao = daoFactory.createOrdersDao()) {
			List <User> users = usersDao.findAll();
			for (User u: users) u.getOrders().addAll(ordersDao.findUserOrders(u));
			return users;
		}
	}
	public void updateUser (User u) throws DBException {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			dao.update(u);
		}
	}
	public void deleteUser (User u) throws DBException {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			dao.delete(u);
		}
	}
}
