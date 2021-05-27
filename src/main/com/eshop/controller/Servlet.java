package com.eshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.controller.command.Command;
import com.eshop.controller.command.CommandContainer;

public class Servlet extends HttpServlet {
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String commandName = req.getParameter("command");
		Command command = CommandContainer.get(commandName);
		String out = command.execute(req);

		res.getWriter().write(out);
	}
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);	
	}
}
