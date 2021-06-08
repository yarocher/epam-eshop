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

	private static DaoFactory daoFactory;
	private Order [] testOrders;

	@BeforeClass
	public static void initFactory () {
		daoFactory = DaoFactory.getInstance();
	}

	@Before
	public void setUp () throws FileNotFoundException, SQLException {
		testOrders = TestData.orders();

		for (int i = 0; i < testOrders.length; i++) {
			User u = new User ();
			u.setId(i + 1);
			testOrders[i].setUser(u);
		}
		
		RunScript.execute(DriverManager.getConnection(TestData.TEST_DB_URL), new FileReader ("sql/db-reset.sql"));
		RunScript.execute(DriverManager.getConnection(TestData.TEST_DB_URL), new FileReader ("sql/db-fill-init-test.sql"));
	}

	@Test
	public void shouldCreateOrder () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			testOrders[0].setState(OrderState.NEW);
			dao.create(testOrders[0]);
			Order o = dao.findById(4);

			assertEquals(testOrders[0], o);
		}

	}

	@Test
	public void shouldFindOrderById () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = dao.findById(1);
			
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
	public void shouldFindUserOrders () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			List <Order> orders = dao.findUserOrders(TestData.users()[0]);

			assertEquals(2, orders.size());
			assertTrue(orders.contains(testOrders[0]));
			assertTrue(orders.contains(testOrders[1]));
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

	@Test
	public void shouldRollbackIfFail () throws Exception {
		try (OrdersDao dao = daoFactory.createOrdersDao()) {
			Order o = testOrders[2];
			o.getItems().clear();
			o.getItems().put(TestData.products()[0], -1); //amount con't be less then zero

			boolean passed = false;
			try {
				dao.create(o); //should fail after order was inserted and rollback
			}
			catch (DBException dbe) {
				passed = true;
			}

			assertTrue(passed);
			assertEquals(3, dao.findAll().size());
		}
	}

}
