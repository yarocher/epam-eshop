package com.eshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshop.controller.command.Command;
import com.eshop.controller.command.CommandOutput;
import com.eshop.controller.command.CommandContainer;

import java.util.logging.Logger;

public class Servlet extends HttpServlet {
	Logger logger = Logger.getLogger(Servlet.class.getName());

	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	public void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String contextPath = req.getContextPath();
		String commandName = req.getRequestURI().replaceAll(contextPath + Path.CONTROLLER + "/", "");
		Command command = CommandContainer.get(commandName);

		logger.info(req.getRequestURI());
		logger.info("current command: " + commandName + " (" + command + ")");

		CommandOutput output = command.execute(req);
		String page = output.getPage();

		if (output.forRedirect()) res.sendRedirect(req.getContextPath() + page); 
		else getServletContext().getRequestDispatcher(page).forward(req, res);
	}
}
