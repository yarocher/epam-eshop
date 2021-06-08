package com.eshop.model.dao;

import com.eshop.model.entity.*;

import java.math.BigDecimal;

public class TestData {

	public static final String TEST_DB_URL = "jdbc:h2:~/eshop-test-db;MVCC=TRUE;LOCK_TIMEOUT=30000;user=login;password=password";
	public static final String TEST_DB_DRIVER = "org.h2.Driver";

	public static Product [] products () {
		Product p1 = new Product ();
		p1.setId(1);
		p1.setName("cup1");
		p1.setDescription("desc of cup1");
		p1.setAmount(324);
		p1.setState(ProductState.ON_SALE);
		p1.setPrice(new BigDecimal (234.120).setScale(2, BigDecimal.ROUND_HALF_UP));
		p1.setCategory("cups");

		Product p2 = new Product ();
		p2.setId(2);
		p2.setName("cup2");
		p2.setDescription("desc of cup2");
		p2.setAmount(235);
		p2.setState(ProductState.ON_SALE);
		p2.setPrice(new BigDecimal (224.320).setScale(2, BigDecimal.ROUND_HALF_UP));
		p2.setCategory("cups");

		Product p3 = new Product ();
		p3.setId(3);
		p3.setName("book1");
		p3.setDescription("desc of book1");
		p3.setAmount(311);
		p3.setState(ProductState.ON_SALE);
		p3.setPrice(new BigDecimal (24.320).setScale(2, BigDecimal.ROUND_HALF_UP));
		p3.setCategory("books");

		return new Product [] {p1, p2, p3};

	}

	public static Order [] orders () {
		Product [] products = products();
		Product p1 = products[0];
		Product p2 = products[1];
		Product p3 = products[2];

		Order o1 = new Order ();
		o1.setId(1);
		o1.setState(OrderState.PAYED);
		o1.getItems().put(p1, 3);
		o1.getItems().put(p3, 2);

		Order o2 = new Order ();
		o2.setId(2);
		o2.setState(OrderState.NEW);
		o2.getItems().put(p2, 5);
		o2.getItems().put(p3, 1);

		Order o3 = new Order ();
		o3.setId(3);
		o3.setState(OrderState.CANCELLED);
		o3.getItems().put(p1, 2);
		o3.getItems().put(p2, 9);
		o3.getItems().put(p3, 2);

		return new Order [] {o1, o2, o3};
	}

	public static User [] users () {
		Order [] orders = orders();
		Order o1 = orders[0];
		Order o2 = orders[1];
		Order o3 = orders[2];

		User u1 = new User ();
		u1.setId(1);
		u1.setLogin("ivanov123@gmail.com");
		u1.setPassword("ivanov");
		u1.setState(UserState.ACTIVE);
		u1.setRole(Role.CUSTOMER);
		u1.getOrders().add(o1);
		u1.getOrders().add(o2);
		o1.setUser(u1);
		o2.setUser(u1);

		User u2 = new User ();
		u2.setId(2);
		u2.setLogin("petrov44@mail.ru");
		u2.setPassword("petrov44");
		u2.setState(UserState.BLOCKED);
		u2.setRole(Role.CUSTOMER);
		u2.getOrders().add(o3);
		o3.setUser(u2);

		User u3 = new User ();
		u3.setId(3);
		u3.setLogin("admin");
		u3.setPassword("admin");
		u3.setState(UserState.ACTIVE);
		u3.setRole(Role.ADMIN);

		return new User [] {u1, u2, u3};
	}

}
