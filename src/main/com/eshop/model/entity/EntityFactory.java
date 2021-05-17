package com.eshop.model.entity;

public class EntityFactory {
	private static EntityFactory instance;
	private EntityFactory () {}

	public static EntityFactory getInstance () {
		if (instance == null) instance = new EntityFactory ();
		return instance;
	}

	public Key newKey (String name) {
		return new Key (name);
	}

	public Value newValue (String name) {
		return new Value (name);
	}

	public Category newCategory (String name) {
		return new Category (name);
	}

	public Product newProduct (String name, double price, int amount) {
		return new Product (name, price, amount);
	}

	public Cart newCart () {
		return new Cart ();
	}

	public Order newOrder (Cart cart) {
		return new Order (cart);
	}

	public User newUser (String login, String password) {
		return new User (login, password);
	}
}
