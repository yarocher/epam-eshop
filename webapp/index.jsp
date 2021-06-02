<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle" %>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="context_path" scope="page" value="${pageContext.request.contextPath}"/>
<c:set var="content" scope="session" value="${sessionScope.content}"/>
<c:set var="login_label" scope="session" value="${content.getString('login')}"/>
<c:set var="registration_label" scope="session" value="${content.getString('registration')}"/>
<c:set var="logout_label" scope="session" value="${content.getString('logout')}"/>
<c:set var="account_label" scope="session" value="${content.getString('account')}"/>
<c:set var="cart_label" scope="session" value="${content.getString('cart')}"/>
<c:set var="all_products_label" scope="session" value="${content.getString('all-products')}"/>
<c:set var="all_orders_label" scope="session" value="${content.getString('all-orders')}"/>
<c:set var="all_users_label" scope="session" value="${content.getString('all-users')}"/>
<c:set var="login_label" scope="session" value="${content.getString('login')}"/>
<c:set var="login_label" scope="session" value="${content.getString('login')}"/>
<c:set var="login_label" scope="session" value="${content.getString('login')}"/>
<c:set var="login_label" scope="session" value="${content.getString('login')}"/>
<!DOCTYPE html>
<html>
	<head>
		<title>Welcome!</title>
	</head>
	<body>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=en">EN</a></div><br>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=ru">RU</a></div><br>
		<c:if test="${empty user}">
			<a href="${context_path}/reg">${registration_label}</a><br>
			<a href="${context_path}/login">${login_label}</a><br>
			<a href="${context_path}/controller/cart">${cart_label}</a><br>
		</c:if>
		<c:if test="${not empty user}">
			<a href="${context_path}/controller/logout">${logout_label}</a><br>
		</c:if>
		<c:if test="${user.role == 'CUSTOMER'}">
			<a href="${context_path}/controller/cart">${cart_label}</a><br>
			<a href="${context_path}/controller/account">${account_label}</a><br>
		</c:if>
		<c:if test="${user.role == 'ADMIN'}">
			<a href="${context_path}/controller/users">${all_users_label}</a><br>
			<a href="${context_path}/controller/orders">${all_orders_label}</a><br>
		</c:if>
		<a href="${context_path}/controller/products">${all_products_label}</a><br>
	</body>
</html>
