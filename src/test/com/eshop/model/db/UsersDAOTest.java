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

public class UsersDAOTest {
	private static UsersDAO dao;
	private static EntityFactory ef;
	private static final String testURL = "jdbc:h2:~/mydb;user=login;password=password;";
	private static User userTest;

	@BeforeClass
	public static void setUp () {
		DAOFactory df = new DAOFactoryMySQL ();
		dao = df.newUsersDAO();
		dao.setURL(testURL);

		ef = EntityFactory.getInstance();

		userTest = ef.newUser("kekl", "kekp");
		userTest.setId(1);
		userTest.setState("ACTIVE");
		userTest.setRole("CUSTOMER");
		userTest.data().setFirstName("Kek");
		userTest.data().setLastName("Kekovich");
		userTest.data().setEmail("kek@kek.kek");
	}

	@Before
	public void setDB () throws FileNotFoundException, DBException, SQLException {
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-reset.sql"));
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-fill-init.sql"));
	}
	@Test
	public void shouldGetAllUsers () throws DBException {
		List <User> users = dao.getAllUsers();
		assertEquals(2, users.size());
		assertEquals(userTest, users.get(0));
	}
	@Test
	public void shouldGetUserById () throws DBException {
		User user = dao.getUserById(1);
		assertEquals(userTest, user);
	}
	@Test
	public void shouldInsertUser () throws DBException {
		User u = ef.newUser("l", "p");
		u.setId(1);
		u.setRole("ADMIN");
		u.setState("ACTIVE");
		u.data().setFirstName("fn");
		u.data().setLastName("ln");
		u.data().setEmail("em@kek.kek");

		dao.insertUser(u);

		User inserted = dao.getUserById(u.getId());

		assertEquals(u, inserted);
	}
	@Test
	public void shouldUpdateUser () throws DBException {
		User u = dao.getUserById(2);
		u.setState("BLOCKED");
		u.setRole("ADMIN");
		u.data().setFirstName("fn");
		u.data().setLastName("ln");
		u.data().setEmail("em@kek.kek");


		dao.updateUser(u);

		User updated = dao.getUserById(2);

		assertEquals(u, updated);

	}
	@Test
	public void shouldDeleteUser () throws DBException {
		List<User> users = dao.getAllUsers();
		for (User user: users) dao.deleteUser(user);

		users = dao.getAllUsers();

		assertTrue(users.isEmpty());
	}
}
