<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Cart</title>
	</head>
	<body>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<h1>Cart</h1>
		<ul>
			<c:forEach items="${cart}" var="item">
				<li>${item.key.name} (${item.value})<li>
			</c:forEach>
		</ul>
		<form action="http://localhost:8080/eshop/controller/make-order" method="POST">
			<button>make order</button>
		</form>
	</body>
</html>
