package com.eshop.controller.listener;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

import com.eshop.model.entity.Product;
import com.eshop.model.entity.User;

import com.eshop.controller.Attributes;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		Map <Product, Integer> items = new HashMap <> ();
		session.setAttribute(Attributes.CART, items); 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		User user = (User) session.getAttribute(Attributes.USER);

		@SuppressWarnings("unchecked")
		List <User> loggedUsers = (List <User>) session.getServletContext().getAttribute(Attributes.LOGGED_USERS);

		if (loggedUsers != null) {
			loggedUsers.remove(user);
			session.getServletContext().setAttribute(Attributes.LOGGED_USERS, loggedUsers);
		}
	}
}
