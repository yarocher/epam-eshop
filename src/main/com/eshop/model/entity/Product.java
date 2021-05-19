package com.eshop.model.entity;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class Product {
	private long id;
	private String name;
	private Map <Key, Value> attributes;
	private String description;
	private int amount;
	private double price = -1;
	private Date dateCreation;
	private Date dateModified;
	private String state;

	public long getId () {return id;};
	public String getName () {return name;};
	public Map <Key, Value> attributes () {
		if (attributes == null) attributes = new HashMap <> ();
		return attributes;
	};
	public String getDescription () {return description;};
	public int getAmount () {return amount;};
	public double getPrice () {return price;};
	public Date getDateCreation () {return dateCreation;};
	public Date getDateModified () {return dateModified;};
	public String getState () {return state;}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}
	public void setDescription (String description) {this.description = description;}
	public void setAmount (int amount) {this.amount = amount;}
	public void setPrice (double price) {this.price = price;}
	public void setDateCreation (Date date) {this.dateCreation = date;}
	public void setDateModified (Date date) {this.dateModified = date;}
	public void setState (String state) {this.state = state;}

	public Product () {}
	public Product (String name, double price, int amount) {
		setName(name);
		setPrice(price);
		setAmount(amount);
	}

	@Override
	public String toString () {
		//return fullToString();
		return "Product" + id + " {name=" + name + ", price=" + String.format("%.2f", price) + ", amount=" + amount + ", state=" + state +  "}";
	}

	public String fullToString () {
		StringBuilder sb = new StringBuilder (name + id +  ":\n");
		sb.append("\tattributes:\n");
		for (Map.Entry <Key, Value> entry: attributes().entrySet()) {
			sb.append("\t\t" + entry.getKey().getName() + ": " + entry.getValue().getName() + "\n");
		}
		sb.append("\t" + description + "\n");
		sb.append("\tprice: " + price + "\n");
		sb.append("\tamount: " + amount + "\n");
		sb.append("\tdate of creation: " + dateCreation + "\n");
		sb.append("\tdate of modifiyng: " + dateModified + "\n");
		sb.append("\tstate: " + state + "\n");
		return sb.toString(); 
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
		if (!attributes().equals(o.attributes())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}

}
