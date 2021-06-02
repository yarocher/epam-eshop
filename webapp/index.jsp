<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="uri" scope="session" value="${pageContext.request.requestURI}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="context_path" scope="page" value="${pageContext.request.contextPath}"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="content">
	<!DOCTYPE html>
	<html>
		<head>
			<title>Welcome!</title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<c:if test="${empty user}">
				<a href="${context_path}/login"><fmt:message key="log_in"/></a><br>
				<a href="${context_path}/reg"><fmt:message key="registration"/></a><br>
				<a href="${context_path}/controller/cart"><fmt:message key="cart"/></a><br>
			</c:if>
			<c:if test="${not empty user}">
				<a href="${context_path}/controller/logout"><fmt:message key="logout"/></a><br>
			</c:if>
			<c:if test="${user.role == 'CUSTOMER'}">
				<a href="${context_path}/controller/cart"><fmt:message key="cart"/></a><br>
				<a href="${context_path}/controller/account"><fmt:message key="account"/></a><br>
			</c:if>
			<c:if test="${user.role == 'ADMIN'}">
				<a href="${context_path}/controller/users"><fmt:message key="all-users"/></a><br>
				<a href="${context_path}/controller/orders"><fmt:message key="all-orders"/></a><br>
			</c:if>
			<a href="${context_path}/controller/products"><fmt:message key="all-products"/></a><br>
		</body>
	</html>
</fmt:bundle>
