package com.eshop.model.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Key {
	private long id;
	private String name;
	private List <Value> values;

	public long getId () {return id;}
	public String getName () {return name;}
	public List <Value> values () {
		if (values == null) values = new ArrayList <> ();
		return values;
	}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}

	public Key () {}
	public Key (String name) {
		setName(name);
	}

	@Override
	public String toString () {
		return name + id + values();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Key)) return false;
		Key o = (Key) obj;
		return name.equals(o.name);
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
