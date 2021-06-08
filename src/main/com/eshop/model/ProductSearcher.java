package com.eshop.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

import com.eshop.model.dao.SQL;

public class ProductSearcher {

	private String name;
	private BigDecimal priceMax;
	private BigDecimal priceMin;
	private String sorter;
	private String category;

	public String getName () {
		return name;
	}

	public String getCategory () {
		return category;
	}

	public BigDecimal getPriceMax () {
		return priceMax;
	}

	public BigDecimal getPriceMin () {
		return priceMin;
	}

	public ProductSearcher addName (String name) {
		this.name = name;	
		return this;
	}

	public ProductSearcher addCategory (String category) {
		this.category = category;	
		return this;
	}

	public ProductSearcher addPriceMax (BigDecimal priceMax) {
		this.priceMax = priceMax;	
		return this;
	}

	public ProductSearcher addPriceMin (BigDecimal priceMin) {
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

	//1) name
	//2) category
	//3) priceMax
	//4) priceMin
	public String get () {
		StringBuilder sb = new StringBuilder (SQL.SELECT_ALL_PRODUCTS);
		boolean more = false;
		if (name != null || category != null || priceMax != null || priceMin != null ) sb.append(SQL.FILTER); 
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
		if (priceMax != null) {
			sb.append(more ? SQL.AND : "");
			sb.append(SQL.PRICE_MAX_FILTER);
			more = true;
		}
		if (priceMin != null) {
			sb.append(more ? SQL.AND : "");
			sb.append(SQL.PRICE_MIN_FILTER);
			more = true;
		}
		if (sorter != null) sb.append(sorter);
		return sb.toString();
	}

}
