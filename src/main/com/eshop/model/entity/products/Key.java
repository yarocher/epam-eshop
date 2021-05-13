package com.eshop.model.entity.products;

import java.util.List;
import java.util.ArrayList;

public class Key {
	private String name;
	private List <String> values = new ArrayList <> ();

	public String name () {return name;}
	public List <String> values () {return values;}

	public Key (String name) {this.name = name;}

	@Override
	public String toString () {
		return name + values;
	}
}
