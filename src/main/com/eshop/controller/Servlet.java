package com.eshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.controller.command.Command;
import com.eshop.controller.command.CommandOutput;
import com.eshop.controller.command.CommandContainer;

public class Servlet extends HttpServlet {
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	public void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String commandName = req.getRequestURI().replaceAll("/eshop/controller/", "");
		Command command = CommandContainer.get(commandName);

		CommandOutput output = command.execute(req);
		String page = output.getPage();

		if (output.forRedirect()) res.sendRedirect(page); 
		else getServletContext().getRequestDispatcher(page).forward(req, res);
	}
}
