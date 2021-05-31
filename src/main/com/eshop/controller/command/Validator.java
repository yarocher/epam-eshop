package com.eshop.controller.command;

import javax.servlet.http.HttpServletRequest;

import com.eshop.model.entity.User;

public class Validator {
	public static void validateUser(HttpServletRequest req, User user) throws AuthorizationException {
		if (!user.getLogin().equals(req.getParameter("login")) || !user.getPassword().equals(req.getParameter("password"))) throw new AuthorizationException ("Wrong credentionals!");
	}	
}
