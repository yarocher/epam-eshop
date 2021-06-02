<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Products</title>
	</head>
	<body>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=en">EN</a></div><br>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=ru">RU</a></div><br>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"http://localhost:8080/eshop/reg\">registation</a><br>"%>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"http://localhost:8080/eshop/login\">login</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"http://localhost:8080/eshop/controller/logout\">logout</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"http://localhost:8080/eshop/controller/account\">account</a><br>"%>
		<a href="http://localhost:8080/eshop/controller/cart">cart</a><br>
		<h1>Products</h1>
			<form action="http://localhost:8080/eshop/create-product">
				<button>create</button>
			</form>
		<form>
			<label for="name">Name:</label>
			<input type="text" id="name" name="name">
			<label for="category">Category:</label>
			<input type="text" id="category" name="category">
			<label for="min_price">min price:</label>
			<input type="text" id="min_price" name="price_min">
			<label for="max_price">max price:</label>
			<input type="text" id="max_price" name="price_max">
			<label for="sort_by">sort by:</label>
			<input type="text" id="sory_by" name="sort_by">
			<label for="desc">desc:</label>
			<input type="radio" id="desc" name="desc"><br>

			<input type="submit" value="Search"><br><br>
		</form>
		<c:forEach items="${products}" var="product">
			<c:out value="${product}"/><p>
			<form action="http://localhost:8080/eshop/controller/add-item" method="POST">
				<button name="product_id" value="${product.id}">add</button>
			</form>
			<form action = "http://localhost:8080/eshop/edit-product">
				<button name="product_id" value="${product.id}">edit</button>
			</form>
			<form action = "http://localhost:8080/eshop/controller/delete-product">
				<button name="product_id" value="${product.id}">delete</button>
			</form>
		</c:forEach>
	</body>
</html>
