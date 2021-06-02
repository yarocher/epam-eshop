package com.eshop.controller.command;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.User;

public class Validator {
	private static Pattern loginPattern = Pattern.compile("\\w*@[a-z]*\\.[a-z]*");
	private static Pattern passwordPattern = Pattern.compile("\\w{8,}");

	public static void validateUser(HttpServletRequest req, User user) throws AuthorizationException {
		if (!user.getLogin().equals(req.getParameter("login")))  throw new AuthorizationException ("Wrong login!");
		if (!user.getPassword().equals(req.getParameter("password"))) throw new AuthorizationException ("Wrong password!");
	}	
	public static void validateLogin(String login) throws AuthorizationException {
		if (!loginPattern.matcher(login).matches()) throw new AuthorizationException ("Not valid login!");
	}	
	public static void validatePassword(String password) throws AuthorizationException {
		if (!passwordPattern.matcher(password).matches()) throw new AuthorizationException ("Not valid password!");
	}	
}
