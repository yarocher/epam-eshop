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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Value)) return false;
		Value o = (Value) obj;
		return name.equals(o.name);
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
