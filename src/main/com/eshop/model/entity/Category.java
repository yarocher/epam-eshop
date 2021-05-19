package com.eshop.model.entity;

import java.util.List;
import java.util.ArrayList;

public class Category {
	private long id;
	private String name;
	private List <Key> keys;

	public long getId () {return id;}
	public String getName () {return name;}
	public List <Key> keys () {
		if (keys == null) keys = new ArrayList <> ();
		return keys;
	}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}

	public Category () {}
	public Category (String name) {
		setName(name);
	}

	@Override
	public String toString () {
		return "Category" + id + " {name=" + name + ", keys=" + keys + "}";
	}


	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Category)) return false;
		Category o = (Category) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (name == null ^ o.name == null) eq = false;
		if (name != null && !name.equals(o.name)) eq = false;
		if (!keys().equals(o.keys())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
