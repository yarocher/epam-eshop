package com.eshop.model.entity;

import java.util.Map;
import java.util.HashMap;

public class Cart {
	private long id;
	private Map <Product, Integer> items;

	public long getId () {return id;}
	public Map <Product, Integer> items () {
		if (items == null) items = new HashMap <> ();
		return items;
	}

	public void setId (long id) {this.id = id;}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ("Cart" + id + " {");
		for (Map.Entry<Product, Integer> entry : items.entrySet()) {
			sb.append(entry.getKey().getName() + " (" + entry.getValue() + "); ");
		}	
		sb.append("}");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cart)) return false;
		Cart o = (Cart) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (!items().equals(o.items())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
