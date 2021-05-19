package com.eshop.model.db;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.h2.tools.RunScript;

import java.io.FileReader;
import java.io.FileNotFoundException;

import java.sql.SQLException;

import java.util.List;

import com.eshop.model.entity.*;
import com.eshop.model.db.*;

public class OrdersDAOTest {
	private static OrdersDAO dao;
	private static UsersDAO usersDAO;
	private static ProductsDAO productsDAO;
	private static EntityFactory ef;
	private static final String testURL = "jdbc:h2:~/mydb;user=login;password=password;";
	private static Cart cartTest;
	private static Order orderTest;
	private static User user;

	@BeforeClass
	public static void setUp () throws DBException {
		DAOFactory df = new DAOFactoryMySQL ();
		dao = df.newOrdersDAO();
		usersDAO = df.newUsersDAO();
		productsDAO = df.newProductsDAO();
		dao.setURL(testURL);
		usersDAO.setURL(testURL);
		productsDAO.setURL(testURL);

		ef = EntityFactory.getInstance();

		user = usersDAO.getUserById(1);

		cartTest = ef.newCart();
		cartTest.setId(1);
		cartTest.items().put(productsDAO.getProductById(1), 1);
		cartTest.items().put(productsDAO.getProductById(4), 1);
		orderTest = ef.newOrder(cartTest);
		orderTest.setId(1);
		orderTest.setState("NEW");
	}

	@Before
	public void setDB () throws FileNotFoundException, DBException, SQLException {
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-reset.sql"));
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-fill-init.sql"));
	}
	@Test
	public void shouldGetAllOrders () throws DBException {
		List <Order> orders = dao.getAllOrders();
		assertEquals(3, orders.size());
		assertEquals(orderTest, orders.get(0));
	}
	@Test
	public void shouldGetOrderById () throws DBException {
		assertEquals(orderTest, dao.getOrderById(1));
	}
	@Test
	public void shouldGetUserOrders () throws DBException {
		List <Order> orders = dao.getUserOrders(user);
		assertEquals(2, orders.size());
		assertEquals(orderTest, orders.get(0));
	}
	@Test
	public void shouldGetCurrentUserCart () throws DBException {
		Cart c = ef.newCart();
		c.setId(3);
		c.items().put(productsDAO.getProductById(1), 1);
		c.items().put(productsDAO.getProductById(3), 1);

		assertEquals(c, dao.getCurrentUserCart(user));
	}
	@Test
	public void shouldInsertCart () throws DBException {
		Cart c = ef.newCart();
		c.items().put(productsDAO.getProductById(1), 2);
		c.items().put(productsDAO.getProductById(4), 6);
		c.items().put(productsDAO.getProductById(3), 9);

		dao.deleteCart(dao.getCurrentUserCart(user));
		dao.insertCart(c, user);

		Cart inserted = dao.getCurrentUserCart(user);

		assertEquals(c, inserted);
	}
	@Test
	public void shouldDeleteCart () throws DBException {
		Cart c = dao.getCurrentUserCart(user);
		dao.deleteCart(c);

		boolean passed = false;
		try {
			dao.getCurrentUserCart(user);
		}
		catch (DBException dbe) {
			passed = true;
		}
		assertTrue(passed);
	}
	@Test
	public void shouldInsertOrder () throws DBException {
		Cart c = dao.getCurrentUserCart(user);
		Order o = ef.newOrder(c);

		dao.insertOrder(o, user);

		assertEquals(4, dao.getAllOrders().size());
		boolean passed = false;
		try {
			dao.getCurrentUserCart(user);
		}
		catch (DBException dbe) {
			passed = true;
		}
		assertTrue(passed);

	}
	@Test
	public void shouldUpdateOrder () throws DBException {
		Order o = dao.getOrderById(2);
		o.setState("PAYED");

		dao.updateOrder(o);

		Order updated = dao.getOrderById(o.getId());
		assertEquals(o, updated);
	}
	@Test
	public void shouldDeleteOrder () throws DBException {
		Order o = dao.getOrderById(1);
		dao.deleteOrder(o);

		assertEquals(2, dao.getAllOrders().size());
	}
}
