package com.eshop.model;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.eshop.model.entity.*;
import com.eshop.model.dao.DBException;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.UsersDao;
import com.eshop.model.dao.OrdersDao;
import com.eshop.model.dao.ProductsDao;

public class Mock {
	public static void main (String [] args) {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			ProductsDao productsDao = daoFactory.createProductsDao();
			Product p1 = new Product ();
			p1.setId(1);
			p1.setAmount(5);
			p1.setPrice(2.2);
			p1.setName("eorgn");
			p1.setCategory("c1");
			p1.setDescription("eeeeeeeeeeeeee");
			p1.setDateCreated(new Date ());
			try {Thread.sleep(2000);} catch (InterruptedException ie) {ie.printStackTrace();}
			p1.setDateModified(new Date ());
			p1.setState(ProductState.HIDDEN);
			System.out.println(productsDao.findAll());
			productsDao.create(p1);
			System.out.println(productsDao.findAll());
		}
		catch (DBException dbe) {
			dbe.printStackTrace();
		}
		/*

		Product p2 = new Product ();
		p2.setId(2);
		p2.setAmount(2);
		p2.setPrice(3.1);
		p2.setName("eoqige[ohgn");
		p2.setCategoryName("c2");
		p2.setDescription("hhhhhhhhhhh");
		p2.setDateCreated(new Date ());
		try {Thread.sleep(2000);} catch (InterruptedException ie) {ie.printStackTrace();}
		p2.setDateModified(new Date ());
		p2.setState(ProductState.HIDDEN);

		Product p3 = new Product ();
		p3.setId(2);
		p3.setAmount(2);
		p3.setPrice(3.1);
		p3.setName("eoqige[ohgn");
		p3.setCategoryName("c2");
		p3.setDescription("hhhhhhhhhhh");
		p3.setDateCreated(new Date ());
		try {Thread.sleep(2000);} catch (InterruptedException ie) {ie.printStackTrace();}
		p3.setDateModified(new Date ());
		p3.setState(ProductState.HIDDEN);

		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p2.equals(p3));

		Order o1 = new Order ();
		o1.setId(1);
		o1.setDateCreated(new Date ());
		try {Thread.sleep(2000);} catch (InterruptedException ie) {ie.printStackTrace();}
		o1.setDateModified(new Date ());
		o1.setState(OrderState.PAYED);
		o1.items().put(p1, 2);
		o1.items().put(p2, 2);

		Order o2 = new Order ();
		o2.setId(2);
		o2.setDateCreated(new Date ());
		try {Thread.sleep(2000);} catch (InterruptedException ie) {ie.printStackTrace();}
		o2.setDateModified(new Date ());
		o2.setState(OrderState.NEW);
		o2.items().put(p3, 5);
		o2.items().put(p2, 2);

		Order o3 = new Order ();
		o3.setId(2);
		o3.setDateCreated(new Date ());
		try {Thread.sleep(2000);} catch (InterruptedException ie) {ie.printStackTrace();}
		o3.setDateModified(new Date ());
		o3.setState(OrderState.NEW);
		o3.items().put(p3, 5);
		o3.items().put(p2, 2);

		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);
		System.out.println(o2.equals(o3));

		User u1 = new User ();
		u1.setLogin("eoug");
		u1.setPassword("iwourghurgh");
		u1.setState(UserState.ACTIVE);
		u1.setRole(Role.ADMIN);
		u1.setId(1);
		u1.orders().add(o3);

		User u2 = new User ();
		u2.setLogin("qwreq");
		u2.setPassword("iiiiiiiiii");
		u2.setState(UserState.BLOCKED);
		u2.setRole(Role.CUSTOMER);
		u2.setId(2);
		u2.orders().add(o1);
		u2.orders().add(o2);

		User u3 = new User ();
		u3.setLogin("qwreq");
		u3.setPassword("iiiiiiiiii");
		u3.setState(UserState.BLOCKED);
		u3.setRole(Role.CUSTOMER);
		u3.setId(2);
		u3.orders().add(o1);
		u3.orders().add(o2);

		System.out.println(u1);
		System.out.println(u2);
		System.out.println(u3);
		System.out.println(u2.equals(u3));
		*/

	}
}
