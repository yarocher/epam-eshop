package com.eshop.model.entity;

import java.util.List;
import java.util.ArrayList;

public class Key {
	private long id;
	private String name;
	private List <String> values;

	public long getId () {return id;}
	public String getName () {return name;}
	public List <String> values () {
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
		return name + values;
	}
}
