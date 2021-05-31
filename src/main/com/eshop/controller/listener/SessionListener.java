package com.eshop.controller.listener;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

import com.eshop.model.entity.Product;
import com.eshop.model.entity.User;

public class SessionListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("SESSION WAS CREATED");
		HttpSession session = httpSessionEvent.getSession();
		Map <Product, Integer> items = new HashMap <> ();
		session.setAttribute("cart", items); 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("SESSION WAS DESTROYED");
		HttpSession session = httpSessionEvent.getSession();
		User user = (User) session.getAttribute("user");
		List <User> loggedUsers = (List <User>) session.getServletContext().getAttribute("logged-users");

		if (loggedUsers != null) {
			loggedUsers.remove(user);
			session.getServletContext().setAttribute("logged-users", loggedUsers);
		}

	}
}
