package com.eshop.model.db;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.SQLException;
import com.eshop.model.db.DBException;

import org.h2.tools.RunScript;

import java.io.FileReader;
import java.io.FileNotFoundException;

public class ProductsDAOTest {

	private static ProductsDAO dao;
	private static String testURL = "jdbc:h2:~/eshop-test-db;user=login;password=password;";
	private static String dbRestartScript = "db-scripts/db-restart.sql";

	@BeforeClass
	public static void setUp () {
		dao = new ProductsDAO (testURL);
	}

	@Before
	public void dbRestart () throws DBException, SQLException, FileNotFoundException {
		RunScript.execute(dao.getConnection(testURL), new FileReader (dbRestartScript));
	}

	@Test
	public void shouldInsertProduct () {
		System.out.println("ok");
		
	}
	@Test
	public void shouldInsertCategory () {

	}
	@Test
	public void shouldUpdateProduct () {

	}
	@Test
	public void shouldUpdateCategory () {

	}
	@Test
	public void shouldDeleteProduct () {

	}
	@Test
	public void shouldDeleteCategory () {

	}
	@Test
	public void shouldGetAllProducts () {

	}
	@Test
	public void shouldGetAllCategories() {

	}
	@Test
	public void shouldGetCategoryProducts () {

	}
	@Test
	public void shouldGetCategoryById () {

	}
	@Test
	public void shouldGetCategoryByName () {

	}
	@Test
	public void shouldGetProductById () {

	}
	public void shouldGetProductByPattern () {

	}
}
