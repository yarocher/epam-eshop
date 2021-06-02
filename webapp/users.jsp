<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Users</title>
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
		<h1>Users</h1>
		<c:forEach items="${users}" var="user">
			<p>
				<c:out value="${user}"/>
				<form action="http://localhost:8080/eshop/controller/update-user" method="POST">
					<label for="state">Set state:</label>
					<input type="text" id="state" name="state">
					<input type="hidden" name="user_id" value="${user.id}">
					<input type="submit" value="Set">
				</form>
				<form action="http://localhost:8080/eshop/controller/delete-user" method="DELETE">
					<button name="user_id" value="${user.id}">Delete</button>
				</form>
			</p>
		</c:forEach>
	</body>
</html>
