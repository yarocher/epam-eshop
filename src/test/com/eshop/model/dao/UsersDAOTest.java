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
import com.eshop.model.dao.UsersDao;

public class UsersDAOTest {
	private static String testURL;
	private static DaoFactory daoFactory;
	private User [] testUsers;

	@BeforeClass
	public static void initFactory () {
		testURL = "jdbc:h2:~/mydb;user=login;password=password;";
		daoFactory = DaoFactory.getInstance();
	}

	@Before
	public void setUp () throws FileNotFoundException, SQLException {
		testUsers = TestData.users();

		RunScript.execute(DriverManager.getConnection(testURL), new FileReader ("sql/db-reset.sql"));
		RunScript.execute(DriverManager.getConnection(testURL), new FileReader ("sql/db-fill-init.sql"));
	}
	@Test
	public void shouldCreateUser () throws Exception {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			dao.create(testUsers[0]);
			User u = dao.findById(4);
			assertEquals(testUsers[0], u);
		}
	}
	@Test
	public void shouldFindUserById () throws Exception {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			User u = dao.findById(1);
			assertEquals(testUsers[0], u);
		}
	}
	@Test
	public void shouldFindAllUsers () throws Exception {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			List <User> users = dao.findAll();
			assertEquals(3, users.size());
			assertEquals(testUsers[0], users.get(0));
			assertEquals(testUsers[1], users.get(1));
			assertEquals(testUsers[2], users.get(2));
		}
	}
	@Test
	public void shouldUpdateUser () throws Exception {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			User u = dao.findById(2);
			u.setState(UserState.ACTIVE);
			u.setRole(Role.ADMIN);
			dao.update(u);

			User updated = dao.findById(2);

			assertEquals(u, updated);
		}
	}
	@Test
	public void shouldDeleteUser () throws Exception {
		try (UsersDao dao = daoFactory.createUsersDao()) {
			User u = dao.findById(1);
			dao.delete(u);

			List <User> users = dao.findAll();

			assertFalse(users.contains(u));
			assertEquals(2, users.size());
		}

	}
}
