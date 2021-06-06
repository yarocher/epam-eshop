package com.eshop.model.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Product implements java.io.Serializable{
	private long id;
	private String name;
	private String category;
	private String description;
	private int amount;
	private BigDecimal price = new BigDecimal (-1);;
	private LocalDateTime dateCreated;
	private LocalDateTime dateModified;
	private ProductState state;

	public long getId () {return id;};
	public String getName () {return name;};
	public String getCategory() {return category;};
	public String getDescription () {return description;};
	public int getAmount () {return amount;};
	public BigDecimal getPrice () {return price;};
	public LocalDateTime getDateCreated () {return dateCreated;};
	public LocalDateTime getDateModified () {return dateModified;};
	public ProductState getState () {return state;}

	public void setId (long id) {this.id = id;}
	public void setName (String name) {this.name = name;}
	public void setCategory(String category) {this.category = category;}
	public void setDescription (String description) {this.description = description;}
	public void setAmount (int amount) {this.amount = amount;}
	public void setPrice (BigDecimal price) {this.price = price;}
	public void setDateCreated (LocalDateTime date) {this.dateCreated = date;}
	public void setDateModified (LocalDateTime date) {this.dateModified = date;}
	public void setState (ProductState state) {this.state = state;}

	public Product () {}
	public Product (String name, BigDecimal price, int amount) {
		setName(name);
		setPrice(price);
		setAmount(amount);
	}

	@Override
	public String toString () {
		return name + id + 
			"\n\tcategory: " + category+
			"\n\tprice: " + price +
			"\n\tamount: " + amount +
			"\n\tdate created: " + dateCreated +
			"\n\tdate modified: " + dateModified +
			"\n\tstate: " + state +
			"\n\tdescription: " + description;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Product)) return false;
		Product o = (Product) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (amount != o.amount) eq = false;
		if (!price.equals(o.price)) eq = false;
		if (description == null ^ o.description == null) eq = false;
		if (description != null && !description.equals(o.description)) eq = false;
		if (name == null ^ o.name == null) eq = false;
		if (name != null && !name.equals(o.name)) eq = false;
		if (category == null ^ o.category== null) eq = false;
		if (category!= null && !category.equals(o.category)) eq = false;
		if (state == null ^ o.state == null) eq = false;
		if (state != null && !state.equals(o.state)) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}

}
