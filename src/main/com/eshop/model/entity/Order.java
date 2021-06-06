package com.eshop.model.entity;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

public class Order implements java.io.Serializable {
	private long id;
	private Map <Product, Integer> items;
	private OrderState state;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	private User user;

	public long getId () {return id;}
	public Map <Product, Integer> getItems() {
		if (items == null) items = new HashMap <> ();
		return items;
	}
	public OrderState getState () {return state;}
	public LocalDateTime getDateCreated () {return dateCreated;};
	public LocalDateTime getDateModified () {return dateModified;};
	public User getUser () {return user;};

	public void setId (long id) {this.id = id;}
	public void setState (OrderState state) {this.state = state;}
	public void setDateCreated (LocalDateTime date) {this.dateCreated = date;}
	public void setDateModified (LocalDateTime date) {this.dateModified = date;}
	public void setUser (User user) {this.user = user;}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ("Order" + id + "(" + state + ")" + "\n\tuser: " + user + "\n\tdate created: " + dateCreated + "\n\tdate modified: " + dateModified +	"\n\titems : ");
		for (Map.Entry<Product, Integer> entry: getItems().entrySet()) sb.append("\n\t\t" + entry.getKey().getName() + " (" + entry.getValue() + ");");
		return sb.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Order)) return false;
		Order o = (Order) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (state == null ^ o.state == null) eq = false;
		if (state != null && !state.equals(o.state)) eq = false;
		if (!getItems().equals(o.getItems())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
