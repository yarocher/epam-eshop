package com.eshop.model.entity;

public class UserData {
	private long userId;
	private String firstName;
	private String lastName;
	private String email;

	public long getUserId () {return userId;}
	public String getFirstName () {return firstName;}
	public String getLastName () {return lastName;}
	public String getEmail () {return email;}

	public void setUserId (long userId) {this.userId = userId;}
	public void setFirstName (String firstName) {this.firstName = firstName;}
	public void setLastName (String lastName) {this.lastName = lastName;}
	public void setEmail (String email) {this.email = email;}

	@Override
	public String toString () {
		return "first name: " + firstName + ", last name: " + lastName + ", email: " + email;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserData)) return false;
		UserData o = (UserData) obj;
		boolean eq = true;
		if (userId != o.userId) eq = false;
		if (firstName == null ^ o.firstName == null) eq = false;
		if (firstName != null && !firstName.equals(o.firstName)) eq = false;
		if (lastName == null ^ o.lastName == null) eq = false;
		if (lastName != null && !lastName.equals(o.lastName)) eq = false;
		if (email == null ^ o.email == null) eq = false;
		if (email != null && !email.equals(o.email)) eq = false;

		return eq;
	}
}
