package com.eshop.model.entity;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class Order {
	private long id;
	private Map <Product, Integer> items;
	private OrderState state;
	private Date dateCreated;
	private Date dateModified;
	private long userId;

	public long getId () {return id;}
	public Map <Product, Integer> items () {
		if (items == null) items = new HashMap <> ();
		return items;
	}
	public OrderState getState () {return state;}
	public Date getDateCreated () {return dateCreated;};
	public Date getDateModified () {return dateModified;};
	public long getUserId () {return userId;};

	public void setId (long id) {this.id = id;}
	public void setState (OrderState state) {this.state = state;}
	public void setDateCreated (Date date) {this.dateCreated = date;}
	public void setDateModified (Date date) {this.dateModified = date;}
	public void setUserId (long userId) {this.userId = userId;}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ("Order" + id + "(" + state + ")" + "\n\tuserId: " + userId + "\n\tdate created: " + dateCreated + "\n\tdate modified: " + dateModified +	"\n\titems : ");
		for (Map.Entry<Product, Integer> entry: items().entrySet()) sb.append("\n\t\t" + entry.getKey().getName() + " (" + entry.getValue() + ");");
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
		if (!items().equals(o.items())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
