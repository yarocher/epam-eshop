package com.eshop.model.entity.products;

import java.util.List;
import java.util.ArrayList;

public class Category {
	private long id;
	private String name;
	private List <Key> keys;

	private Category () {}
	public static Category newCategory (String name) {
		Category category = new Category ();
		category.setName(name);
		return category;	
	}

	public long getId () {return id;}
	public String getName () {return name;}
	public List <Key> keys () {
		if (keys == null) keys = new ArrayList <> ();
		return keys;
	}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}

	@Override
	public String toString () {
		return "Category" + id + " {name=" + name + ", keys=" + keys + "}";
	}
}
