package com.eshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
	@Override
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		res.getWriter().write("111iuwwqwetqreoihjqeito[hn2uohnpwosgnog;hnsetjpro23p48htguw11");	
	}
	@Override
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);	
	}
}
