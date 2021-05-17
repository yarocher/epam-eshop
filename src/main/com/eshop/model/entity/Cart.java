package com.eshop.model.entity;

import java.util.Map;
import java.util.HashMap;

public class Cart {
	private long id;
	private Map <Product, Integer> items;

	public long getId () {return id;}
	public Map items () {
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
}
