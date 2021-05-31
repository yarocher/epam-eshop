package com.eshop.model.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class User  implements java.io.Serializable{
	private long id;
	private String login;
	private String password;
	private UserState state;
	private Role role;
	private List <Order> orders;

	public long getId () {return id;}
	public String getLogin () {return login;}
	public String getPassword () {return password;}
	public UserState getState () {return state;}
	public Role getRole () {return role; }
	public List <Order> orders () {
		if (orders == null) orders = new ArrayList <> ();
		return orders;
	}

	public void setId (long id) {this.id = id;}
	public void setLogin (String login) {this.login = login;}
	public void setPassword (String password) {this.password = password;}
	public void setState (UserState state) {this.state = state;}
	public void setRole (Role role) {this.role = role;}

	public User () {}
	public User (String login, String password) {
		setLogin(login);
		setPassword(password);
		setRole(Role.CUSTOMER);
	}

	@Override
	public String toString () {
		return "User" + id + "(" + state + ") {login=" + login + ", password=" + password + ", role=" + role + ", orders=" + orders().stream().map(o -> o.getId()).collect(Collectors.toList()) +  "}";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) return false;
		User o = (User) obj;
		boolean eq = true;
		if (id != o.id) eq = false;
		if (login == null ^ o.login == null) eq = false;
		if (login != null && !login.equals(o.login)) eq = false;
		if (password == null ^ o.password == null) eq = false;
		if (password != null && !password.equals(o.password)) eq = false;
		if (role == null ^ o.role == null) eq = false;
		if (role != null && !role.equals(o.role)) eq = false;
		if (state == null ^ o.state == null) eq = false;
		if (state != null && !state.equals(o.state)) eq = false;
		//if (!orders().equals(o.orders())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
