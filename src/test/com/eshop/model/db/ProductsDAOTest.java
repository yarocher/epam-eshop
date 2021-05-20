package com.eshop.model.db;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.h2.tools.RunScript;

import java.io.FileReader;
import java.io.FileNotFoundException;

import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import com.eshop.model.entity.*;
import com.eshop.model.db.*;
import com.eshop.model.*;


public class ProductsDAOTest {
	private static ProductsDAO dao;
	private static EntityFactory ef;
	private static final String testURL = "jdbc:h2:~/mydb;user=login;password=password;";
	static private Product productTest;
	static private Category categoryTest;
	static private Key taste;
	static private Key color;
	static private Value red;
	static private Value green;
	static private Value sweet;
	static private Value salty;

	@BeforeClass
	public static void setUp () {
		DAOFactory df = new DAOFactoryMySQL ();
		dao = df.newProductsDAO();
		dao.setURL(testURL);

		ef = EntityFactory.getInstance();
		productTest = ef.newProduct("green apple", 4.4, 1);

		taste = ef.newKey("taste");
		taste.setId(1);
		color = ef.newKey("color");
		color.setId(3);
		sweet = ef.newValue("sweet");
		sweet.setId(1);
		salty = ef.newValue("salty");
		salty.setId(2);
		green = ef.newValue("green");
		green.setId(6);
		red = ef.newValue("red");
		red.setId(5);
		taste.values().add(sweet);
		taste.values().add(salty);
		color.values().add(red);
		color.values().add(green);
		productTest.attributes().put(taste, sweet);	
		productTest.attributes().put(color, green);	
		productTest.setId(1);
		productTest.setDescription("d1");	
		productTest.setState("ON_SALE");


		categoryTest = ef.newCategory("food");
		categoryTest.setId(1);
		categoryTest.keys().add(taste);
		categoryTest.keys().add(color);

	}

	@Before
	public void setDB () throws FileNotFoundException, DBException, SQLException {
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-reset.sql"));
		RunScript.execute(dao.getConnection(), new FileReader ("sql/h2-fill-init.sql"));
	}

	@Test
	public void shouldGetSearchProductsByPattern () throws DBException {

		ProductSearcher pattern = new ProductSearcher ();
		pattern
			.addName("green apple")
			.addPriceMin(4.0)
			.addPriceMax(5.0)
			.sortBy(MySQLQueries.DATE_SORTER + MySQLQueries.DESCENDING);
		List <Product> products = dao.getProductsByPattern(pattern);
		assertEquals(1, products.size());
		assertEquals(productTest, products.get(0));
	}
	@Test
	public void shouldGetAllProducts () throws DBException {

		List <Product> products = dao.getAllProducts();
		assertEquals(4, products.size());

		assertEquals(productTest, products.get(0));
	}
	@Test
	public void shouldGetAllCategories () throws DBException {
		List <Category> categories = dao.getAllCategories();
		assertEquals(2, categories.size());
		assertEquals(categoryTest, categories.get(0));
	}
	@Test
	public void shouldGetProductById () throws DBException {
		Product product = dao.getProductById(1);
		assertEquals(productTest, product);
	}
	@Test
	public void shouldGetCategoryById () throws DBException {
		assertEquals(categoryTest, dao.getCategoryById(1));
	}
	@Test
	public void shouldGetCategoryProducts () throws DBException {
		List <Product> products = dao.getCategoryProducts(categoryTest);
		assertEquals(2, products.size());
		assertEquals(productTest, products.get(0));
	}
	@Test
	public void shouldInsertCategory () throws DBException {
		Category category = ef.newCategory("c1");
		Key k1 = ef.newKey("k1");
		Key k2 = ef.newKey("k2");
		Value v1 = ef.newValue("v1");
		Value v2 = ef.newValue("v2");
		Value v3 = ef.newValue("v3");
		Value v4 = ef.newValue("v4");
		k1.values().add(v1);
		k1.values().add(v2);
		k2.values().add(v3);
		k2.values().add(v4);
		category.keys().add(k1);
		category.keys().add(k2);

		dao.insertCategory(category);
		Category inserted = dao.getCategoryById(category.getId());

		assertEquals(category, inserted);
	}
	@Test
	public void shouldInsertProduct () throws DBException {
		Product p = ef.newProduct("p1", 111.111, 111);
		p.setDescription("ddd");
		p.setState("ON_SALE");
		p.attributes().put(taste, sweet);
		p.attributes().put(color, red);
		dao.insertProduct(p, categoryTest);

		Product inserted = dao.getProductById(p.getId());

		assertEquals(p, inserted);
		assertTrue(dao.getCategoryProducts(categoryTest).contains(p));
	}
	@Test
	public void shouldUpdateCategory () throws DBException {
		Category c = ef.newCategory("c");
		dao.insertCategory(c);
		c.setName("new c");
		dao.updateCategory(c);

		assertEquals(c, dao.getCategoryById(c.getId()));
	}
	@Test
	public void shouldUpdateProduct () throws DBException {
		Product p = ef.newProduct("p1", 111.11, 111);
		p.setDescription("ddd");
		p.setState("ON_SALE");
		p.attributes().put(taste, sweet);
		p.attributes().put(color, red);

		dao.insertProduct(p, categoryTest);
		p.setDescription("d2");
		p.setState("HIDDEN");
		p.setPrice(234.44444);
		p.setAmount(2341);
		p.setName("aeourg");

		dao.updateProduct(p);

		Product updated = dao.getProductById(p.getId());

		assertEquals(p, updated);
	}
	@Test
	public void shouldDeleteCategory () throws DBException {
		Category c = ef.newCategory("c");
		dao.insertCategory(c);
		dao.deleteCategory(c);
		assertEquals(2, dao.getAllCategories().size());
	}
	@Test
	public void shouldDeleteProduct () throws DBException {
		Product p = ef.newProduct("p", 1245.52, 2345);
		dao.insertProduct(p, categoryTest);
		dao.deleteProduct(p);
		assertEquals(4, dao.getAllProducts().size());

	}
}
