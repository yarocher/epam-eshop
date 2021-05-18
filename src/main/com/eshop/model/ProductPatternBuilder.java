package com.eshop.model;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.eshop.model.db.MySQLQueries;

public class ProductPatternBuilder {
	private String name;
	private double priceMax = -1;
	private double priceMin = -1;
	private Map <String, String> attributes;

	public String name () {return name;}
	public double priceMax () {return priceMax;}
	public double priceMin () {return priceMin;}
	public Map <String, String> attributes () {
		if (attributes == null) attributes = new HashMap <> ();
		return attributes;
	}

	public ProductPatternBuilder addName (String name) {
		this.name = name;	
		return this;
	}

	public ProductPatternBuilder addPriceMax (double priceMax) {
		this.priceMax = priceMax;	
		return this;
	}

	public ProductPatternBuilder addPriceMin (double priceMin) {
		this.priceMin = priceMin;	
		return this;
	}

	public ProductPatternBuilder addAttribute (String key, String value) {
		attributes().put(key, value);
		return this;
	}

	//name
	//priceMax
	//priceMin
	//attributes...
	public String get () {
		StringBuilder sb = new StringBuilder (MySQLQueries.PRODUCT_PATTERN);
		boolean more = false;
		if (name != null || priceMax != -1 || priceMin != -1  || !attributes.isEmpty()) sb.append(MySQLQueries.FILTER); 
		if (name != null) {
			sb.append(more ? MySQLQueries.AND : "");
			sb.append(MySQLQueries.NAME_FILTER);
			more = true;
		}
		if (priceMax != -1) {
			sb.append(more ? MySQLQueries.AND : "");
			sb.append(MySQLQueries.PRICE_MAX_FILTER);
			more = true;
		}
		if (priceMin != -1) {
			sb.append(more ? MySQLQueries.AND : "");
			sb.append(MySQLQueries.PRICE_MIN_FILTER);
			more = true;
		}
		for (int i = 0; i < attributes().size(); i++) {
			sb.append(more ? MySQLQueries.AND : "");
			sb.append(MySQLQueries.ATTRIBUTE_FILTER);
			more = true;
		}
		return sb.toString();

	}
}
