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
			<title><fmt:message key="account"/></title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<h1><fmt:message key="account"/></h1>
			<c:out value="login: ${user.login}"/>
			<c:if test="${user.state == 'BLOCKED'}">
				<c:out value="(${user.state})"/>
			</c:if>
			<hr>
			<c:forEach items="${user.orders}" var="order">
				<p>
				<strong><c:out value="Order #${order.id} (${order.state})"/></strong><br>
				<c:out value="created: ${order.dateCreated}"/><br>
				<c:out value="modified: ${order.dateModified}"/><br>
				<p>
				<c:out value="items:"/><br>
				<c:set var="total" value="${0}"/>
				<c:forEach items="${order.items}" var="item">
					<c:out value="${item.key.name} (${item.value}): ${item.key.price * item.value} $"/><br>
					<c:set var="total" value="${total + item.key.price * item.value}"/>
				</c:forEach>
				<p><c:out value="total: ${total} $"/></p>
				</p>
				<hr>
			</c:forEach>
		</body>
	</html>
</fmt:bundle>
