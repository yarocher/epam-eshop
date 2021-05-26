package com.eshop.model.dao;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.h2.tools.RunScript;

import java.io.FileReader;
import java.io.FileNotFoundException;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

import com.eshop.model.entity.*;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.OrdersDao;

public class OrdersDAOTest {
	private static String testURL;
	private static DaoFactory daoFactory;
	private Order [] testOrders;

	@BeforeClass
	public static void initFactory () {
		testURL = "jdbc:h2:~/eshop-test-db;user=login;password=password;";
		daoFactory = DaoFactory.getInstance();
	}

	@Before
	public void setUp () throws FileNotFoundException, SQLException {
		testOrders = TestData.orders();

		for (int i = 0; i < testOrders.length; i++) {
			User u = new User ();
			u.setId(i + 1);
			testOrders[i].setUserId(u.getId());
		}
		
		RunScript.execute(DriverManager.getConnection(testURL), new FileReader ("sql/db-reset.sql"));
		RunScript.execute(DriverManager.getConnection(testURL), new FileReader ("sql/db-fill-init.sql"));
	}
	@Test
	public void shouldCreateOrder () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			testOrders[0].setState(OrderState.NEW);
			dao.create(testOrders[0]);
			Order o = dao.findById(4);
			System.out.println(o);
			assertEquals(testOrders[0], o);
		}

	}
	@Test
	public void shouldFindOrderById () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = dao.findById(1);
			System.out.println(o);
			assertEquals(testOrders[0], o);
		}

	}
	@Test
	public void shouldFindAllOrders () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			List <Order> orders = dao.findAll();
			assertEquals(3, orders.size());
			assertEquals(testOrders[0], orders.get(0));
			assertEquals(testOrders[1], orders.get(1));
			assertEquals(testOrders[2], orders.get(2));
		}

	}
	@Test
	public void shouldUpdateOrder () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = dao.findById(2);
			o.setState(OrderState.PAYED);
			dao.update(o);

			Order updated = dao.findById(2);

			assertEquals(o, updated);
		}

	}
	@Test
	public void shouldDeleteOrder () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = dao.findById(1);
			dao.delete(o);

			List <Order> orders = dao.findAll();

			assertFalse(orders.contains(o));
			assertEquals(2, orders.size());
		}

	}
}
