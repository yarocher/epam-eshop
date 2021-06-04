<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="uri" scope="session" value="${pageContext.request.requestURI}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="context_path" scope="page" value="${pageContext.request.contextPath}"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="content">
	<!DOCTYPE html>
	</html>
		<head>
			<meta charset="UTF-8">
			<title><fmt:message key="create"/> <fmt:message key="product"/></title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<h1><fmt:message key="create"/> <fmt:message key="product"/></h1>
			<form action="http://localhost:8080/eshop/controller/create-product" method="POST" accept-charset="UTF-8">
				<input type="hidden" name="product_id" value="${param.product_id}">
				<label for="name"><fmt:message key="name"/>:</label><br>
				<input type="text" id="name" name="name"><br>
				<label for="category"><fmt:message key="category"/>:</label><br>
				<input type="text" id="category" name="category"><br>
				<label for="description"><fmt:message key="description"/>:</label><br>
				<input type="text" id="description" name="description"><br>
				<label for="amount"><fmt:message key="amount"/>:</label><br>
				<input type="text" id="amount" name="amount"><br>
				<label for="price"><fmt:message key="price"/>:</label><br>
				<input type="text" id="price" name="price"><br>
				<input type="submit" value="<fmt:message key="create"/>">
			</form>
		</body>
	</html>
</fmt:bundle>
