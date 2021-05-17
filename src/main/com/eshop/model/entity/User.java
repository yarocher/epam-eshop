package com.eshop.model.entity;

public class User {
	private long id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String state;

	public long getId () {return id;}
	public String getLogin () {return login;}
	public String getPassword () {return password;}
	public String getFirstName () {return firstName;}
	public String getLastName () {return lastName;}
	public String getEmail () {return email;}
	public String getState () {return state;}

	public void setId (long id) {this.id = id;}
	public void setLogin (String login) {this.login = login;}
	public void setPassword (String password) {this.password = password;}
	public void setFirstName (String firstName) {this.firstName = firstName;}
	public void setLastName (String lastName) {this.lastName = lastName;}
	public void setEmail (String email) {this.email = email;}
	public void setState (String state) {this.state = state;}

	public User () {}
	public User (String login, String password) {
		setLogin(login);
		setPassword(password);
	}

	@Override
	public String toString () {
		return "User" + id + " {login=" + login + ", password=" + password + "}";
	}
}
