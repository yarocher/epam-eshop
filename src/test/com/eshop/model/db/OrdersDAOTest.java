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
	private static EntityFactory ef;
	private static final String testURL = "jdbc:h2:~/p8db;user=login;password=password;";

	@BeforeClass
	public static void setUp () {
		DAOFactory df = new DAOFactoryMySQL ();
		dao = df.newOrdersDAO();
		usersDAO = df.newUsersDAO();
		dao.setURL(testURL);
		usersDAO.setURL(testURL);

		ef = EntityFactory.getInstance();

	}

	@Before
	public void setDB () throws FileNotFoundException, DBException, SQLException {
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-reset.sql"));
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-fill-init.sql"));
	}
	@Test
	public void shouldGetAllOrders () throws DBException {
	}
	@Test
	public void shouldGetOrderById () throws DBException {

	}
	@Test
	public void shouldGetUserOrders () throws DBException {
		Order o = dao.getOrderById(dao.getConnection(), 1, true);
		System.out.println("SOURCE:");
		System.out.println(o);

		o.setState("PAYED");

		dao.updateOrder(o);

		Order updated = dao.getOrderById(dao.getConnection(), o.getId(), true);

		System.out.println("UPDATED:");
		System.out.println(updated);

	}
	@Test
	public void shouldGetCurrentUserCart () throws DBException {

	}
	@Test
	public void shouldInsertCart () throws DBException {

	}
	@Test
	public void shouldUpdateCart () throws DBException {

	}
	@Test
	public void shouldDeleteCart () throws DBException {

	}
	@Test
	public void shouldInsertOrder () throws DBException {

	}
	@Test
	public void shouldUpdateOrder () throws DBException {

	}
	@Test
	public void shouldDeleteOrder () throws DBException {

	}
}
