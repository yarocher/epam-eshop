package com.eshop.model.entity;

public class Value {
	private long id;
	private String name;

	public long getId () {return id;}
	public String getName () {return name;}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}

	public Value () {}
	public Value (String name) {
		setName(name);
	}

	@Override
	public String toString () {
		return name + id;
	}
}
