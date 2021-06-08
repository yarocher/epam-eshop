package com.eshop.controller;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.User;

public class Validator {

	private static Pattern loginPattern = Pattern.compile("[\\w_\\-,\\.]*@[a-z]*\\.[a-z]*");
	private static Pattern passwordPattern = Pattern.compile("\\w{4,}");
	
	private static final String WRONG_PASSWORD = "wrong-password";
	private static final String WRONG_LOGIN = "wrong-login";

	public static void validateUser(HttpServletRequest req, User user) throws AuthorizationException {
		if (!user.getLogin().equals(req.getParameter(Attributes.LOGIN)))  throw new AuthorizationException (WRONG_LOGIN);
		if (!user.getPassword().equals(req.getParameter(Attributes.PASSWORD))) throw new AuthorizationException (WRONG_PASSWORD);
	}	

	public static void validateLogin(String login) throws AuthorizationException {
		if (!loginPattern.matcher(login).matches()) throw new AuthorizationException (WRONG_LOGIN);
	}	

	public static void validatePassword(String password) throws AuthorizationException {
		if (!passwordPattern.matcher(password).matches()) throw new AuthorizationException (WRONG_PASSWORD);
	}	

}
