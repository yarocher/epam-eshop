package com.eshop.model.entity;

import java.util.Date;

public class Order {
	private long id;
	private Cart cart;
	private String state;
	private Date dateCreation;
	private Date dateModified;

	public long getId () {return id;}
	public Cart getCart () {return cart;}
	public String getState () {return state;}
	public Date getDateCreation () {return dateCreation;};
	public Date getDateModified () {return dateModified;};

	public void setId (long id) {this.id = id;}
	public void setCart (Cart cart) {this.cart = cart;}
	public void setState (String state) {this.state = state;}
	public void setDateCreation (Date date) {this.dateCreation = date;}
	public void setDateModified (Date date) {this.dateModified = date;}

	public Order () {}
	public Order (Cart cart) {
		setCart(cart);
	}

	@Override
	public String toString () {
		return "Order" + id + " {" + cart + ", " + state + "}";
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Order)) return false;
		Order o = (Order) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (state == null ^ o.state == null) eq = false;
		if (state != null && !state.equals(o.state)) eq = false;
		if (cart == null ^ o.cart == null) eq = false;
		if (cart != null && !cart.equals(o.cart)) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
