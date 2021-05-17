package com.eshop.model.entity;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class Product {
	private long id;
	private String name;
	private Map <String, String> attributes;
	private String description;
	private int amount;
	private double price;
	private Date dateCreation;
	private Date dateModified;
	private ProductState state;

	public long getId () {return id;};
	public String getName () {return name;};
	public Map <String, String> getAttributes () {
		if (attributes == null) attributes = new HashMap <> ();
		return attributes;
	};
	public String getDescription () {return description;};
	public int getAmount () {return amount;};
	public double getPrice () {return price;};
	public Date getDateCreation () {return dateCreation;};
	public Date getDateModified () {return dateModified;};
	public ProductState getState () {return state;}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}
	public void setDescription (String description) {this.description = description;}
	public void setAmount (int amount) {this.amount = amount;}
	public void setPrice (double price) {this.price = price;}
	public void setDateCreation (Date date) {this.dateCreation = date;}
	public void setDateModified (Date date) {this.dateModified = date;}
	public void setState (ProductState state) {this.state = state;}

	public Product () {}
	public Product (String name, double price, int amount) {
		setName(name);
		setPrice(price);
		setAmount(amount);
	}

	@Override
	public String toString () {
		return "Product" + id + " {name=" + name + ", price=" + String.format("%.2f", price) + ", amount=" + amount + ", state=" + state +  "}";
	}

}
