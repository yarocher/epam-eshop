package com.eshop.model.entity;

public class User {
	private long id;
	private String login;
	private String password;
	private String state;
	private String role;
	private UserData data;

	public long getId () {return id;}
	public String getLogin () {return login;}
	public String getPassword () {return password;}
	public String getState () {return state;}
	public String getRole () {return role; }
	public UserData data () {
		if (data == null) data = new UserData ();
		data.setUserId(id);
		return data;
	}

	public void setId (long id) {this.id = id;}
	public void setLogin (String login) {this.login = login;}
	public void setPassword (String password) {this.password = password;}
	public void setState (String state) {this.state = state;}
	public void setRole (String role) {this.role = role;}

	public User () {}
	public User (String login, String password) {
		setLogin(login);
		setPassword(password);
	}

	@Override
	public String toString () {
		return "User" + id + " {login=" + login + ", password=" + password + ", role=" + role +  "\n" + data + "}";
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
		if (!data().equals(o.data())) eq = false;

		return eq;
	}

	@Override
	public int hashCode () {
		return (int) id;
	}
}
