<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Users</title>
	</head>
	<body>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"http://localhost:8080/eshop/reg\">registation</a><br>"%>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"http://localhost:8080/eshop/login\">login</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"http://localhost:8080/eshop/controller/logout\">logout</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"http://localhost:8080/eshop/account\">account</a><br>"%>
		<a href="http://localhost:8080/eshop/cart">cart</a><br>
		<h1>Users</h1>
		<c:forEach items="${users}" var="user">
			<c:out value="${user}"/><p>
		</c:forEach>
	</body>
</html>
