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

import com.eshop.model.ProductSearcher;
import com.eshop.model.entity.*;
import com.eshop.model.dao.DaoFactory;
import com.eshop.model.dao.ProductsDao;

import com.eshop.model.dao.SQL;

import java.math.BigDecimal;

public class ProductsDAOTest {

	private static DaoFactory daoFactory;
	private Product [] testProducts;

	@BeforeClass
	public static void initFactory () {
		daoFactory = DaoFactory.getInstance();
	}

	@Before
	public void setUp () throws FileNotFoundException, SQLException {
		testProducts = TestData.products();

		RunScript.execute(DriverManager.getConnection(TestData.TEST_DB_URL), new FileReader ("sql/db-reset.sql"));
		RunScript.execute(DriverManager.getConnection(TestData.TEST_DB_URL), new FileReader ("sql/db-fill-init-test.sql"));
	}

	@Test
	public void shouldCreateProduct () throws Exception {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			Product p = testProducts[0];
			dao.create(p);
			Product created = dao.findById(p.getId());
			assertEquals(p, created);
		}
	}

	@Test
	public void shouldFindProductById () throws Exception {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			Product p = dao.findById(1);
			assertEquals(testProducts[0], p);
		}
	}

	@Test
	public void shouldFindAllProducts () throws Exception {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			List <Product> products = dao.findAll();
			assertEquals(3, products.size());
			assertEquals(testProducts[0], products.get(0));
			assertEquals(testProducts[1], products.get(1));
			assertEquals(testProducts[2], products.get(2));
		}
	}
	
	@Test
	public void shouldFindByPattern () throws Exception {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			ProductSearcher pattern = new ProductSearcher();
			pattern
				.addCategory("cups")
				.sortBy(SQL.NAME, true);
			assertEquals(testProducts[0], dao.findByPattern(pattern).get(1));
		}
	}

	@Test
	public void shouldUpdateProduct () throws Exception {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			Product p = dao.findById(2);
			p.setName("name1");
			p.setPrice(new BigDecimal (11.11).setScale(2, BigDecimal.ROUND_HALF_UP));
			p.setAmount(999);
			p.setState(ProductState.ON_SALE);
			dao.update(p);

			Product updated = dao.findById(2);

			assertEquals(p, updated);
		}

	}

	@Test
	public void shouldDeleteProduct () throws Exception {
		try (ProductsDao dao = daoFactory.createProductsDao()) {
			Product p = dao.findById(1);
			dao.delete(p);

			List <Product> products = dao.findAll();

			assertFalse(products.contains(p));
			assertEquals(2, products.size());
		}

	}

}
