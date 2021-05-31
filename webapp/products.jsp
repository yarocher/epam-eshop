<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Products</title>
	</head>
	<body>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"http://localhost:8080/eshop/reg\">registation</a><br>"%>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"http://localhost:8080/eshop/login\">login</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"http://localhost:8080/eshop/controller/logout\">logout</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"http://localhost:8080/eshop/account\">account</a><br>"%>
		<a href="http://localhost:8080/eshop/cart">cart</a><br>
		<h1>Products</h1>
		<c:forEach items="${products}" var="product">
			<c:out value="${product}"/><p>
			<form action="http://localhost:8080/eshop/controller/add-item" method="POST">
				<button name="product_id" value="${product.id}">add</button>
			</form>
		</c:forEach>
	</body>
</html>
