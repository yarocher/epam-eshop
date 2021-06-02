<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome!</title>
	</head>
	<body>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=en">EN</a></div><br>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=ru">RU</a></div><br>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"" + application.getContextPath() + "/reg\">" + ((java.util.ResourceBundle)session.getAttribute("content")).getString("registration")%></a><br>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"" + application.getContextPath() + "/login\">" + ((java.util.ResourceBundle)session.getAttribute("content")).getString("login")%></a><br>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"" + application.getContextPath() + "/controller/logout\">" + ((java.util.ResourceBundle)session.getAttribute("content")).getString("logout") + "</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"" + application.getContextPath() + "/controller/account\">" + ((java.util.ResourceBundle)session.getAttribute("content")).getString("account") + "</a><br>"%>
		<a href="http://localhost:8080/eshop/controller/cart"><%=((java.util.ResourceBundle)session.getAttribute("content")).getString("cart")%></a><br>
		<a href="http://localhost:8080/eshop/controller/products"><%=((java.util.ResourceBundle)session.getAttribute("content")).getString("all-products")%></a><br>
		<a href="http://localhost:8080/eshop/controller/users"><%=((java.util.ResourceBundle)session.getAttribute("content")).getString("all-users")%></a><br>
		<a href="http://localhost:8080/eshop/controller/orders"><%=((java.util.ResourceBundle)session.getAttribute("content")).getString("all-orders")%></a><br>
	</body>
</html>
