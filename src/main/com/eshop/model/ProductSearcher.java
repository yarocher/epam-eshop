package com.eshop.model;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.eshop.model.dao.SQL;

public class ProductSearcher {
	private String name;
	private double priceMax = -1;
	private double priceMin = -1;
	private String sorter;
	private String category;

	public String name () {return name;}
	public String category () {return category;}
	public double priceMax () {return priceMax;}
	public double priceMin () {return priceMin;}

	public ProductSearcher addName (String name) {
		this.name = name;	
		return this;
	}

	public ProductSearcher addCategory (String category) {
		this.category = category;	
		return this;
	}

	public ProductSearcher addPriceMax (double priceMax) {
		this.priceMax = priceMax;	
		return this;
	}

	public ProductSearcher addPriceMin (double priceMin) {
		this.priceMin = priceMin;	
		return this;
	}

	public ProductSearcher sortBy (String field, boolean desc) {
		this.sorter = SQL.SORT_BY + field + (desc ? SQL.DESC : "");
		return this;
	}

	public ProductSearcher sortBy (String field) {
		return sortBy(field, false);
	}

	//name
	//category
	//priceMax
	//priceMin
	public String get () {
		StringBuilder sb = new StringBuilder (SQL.SELECT_ALL_PRODUCTS);
		boolean more = false;
		if (name != null || category != null || priceMax != -1 || priceMin != -1 ) sb.append(SQL.FILTER); 
		if (name != null) {
			sb.append(more ? SQL.AND : "");
			sb.append(SQL.NAME_FILTER);
			more = true;
		}
		if (category != null) {
			sb.append(more ? SQL.AND : "");
			sb.append(SQL.CATEGORY_FILTER);
			more = true;
		}
		if (priceMax != -1) {
			sb.append(more ? SQL.AND : "");
			sb.append(SQL.PRICE_MAX_FILTER);
			more = true;
		}
		if (priceMin != -1) {
			sb.append(more ? SQL.AND : "");
			sb.append(SQL.PRICE_MIN_FILTER);
			more = true;
		}
		if (sorter != null) sb.append(sorter);
		return sb.toString();

	}
}
