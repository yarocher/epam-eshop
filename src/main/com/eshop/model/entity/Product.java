package com.eshop.model.entity;

import java.util.Date;

public class Product {
	private long id;
	private String name;
	private String description;
	private int amount;
	private double price = -1;
	private Date dateCreated;
	private Date dateModified;
	private ProductState state;

	public long getId () {return id;};
	public String getName () {return name;};
	public String getDescription () {return description;};
	public int getAmount () {return amount;};
	public double getPrice () {return price;};
	public Date getDateCreated () {return dateCreated;};
	public Date getDateModified () {return dateModified;};
	public ProductState getState () {return state;}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}
	public void setDescription (String description) {this.description = description;}
	public void setAmount (int amount) {this.amount = amount;}
	public void setPrice (double price) {this.price = price;}
	public void setDateCreated (Date date) {this.dateCreated = date;}
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
		return name + id + 
			"\n\tprice: " + price +
			"\n\tamount: " + amount +
			"\n\tdate created: " + dateCreated +
			"\n\tdate modified: " + dateModified +
			"\n\tstate: " + state +
			"\n\tdescription: " + description;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product)) return false;
		Product o = (Product) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (amount != o.amount) eq = false;
		if (price - o.price > Math.abs(0.01))  eq = false;
		if (description == null ^ o.description == null) eq = false;
		if (description != null && !description.equals(o.description)) eq = false;
		if (name == null ^ o.name == null) eq = false;
		if (name != null && !name.equals(o.name)) eq = false;
		if (state == null ^ o.state == null) eq = false;
		if (state != null && !state.equals(o.state)) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}

}
