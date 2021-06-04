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
			<title><fmt:message key="cart"/></title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<h1><fmt:message key="cart"/></h1>
			<c:if test="${empty cart}">
				<c:out value="your cart is empty"/>
			</c:if>
			<c:if test="${not empty cart}">
				<ul>
					<c:forEach items="${cart}" var="item">
						<c:out value="${item.key.name} (${item.value}): ${item.key.price * item.value}"/><br>
						<c:set var="total" value="${total + item.key.price * item.value}"/>
					</c:forEach>
				</ul>
				<c:out value="total: ${total}"/>
				<form action="${context_path}/controller/make-order" method="POST">
					<button><fmt:message key="make-order"/></button>
				</form>
			</c:if>
		</body>
	</html>
</fmt:bundle>
