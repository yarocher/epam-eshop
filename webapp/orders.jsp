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
			<title><fmt:message key="all-orders"/></title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<h1><fmt:message key="all-orders"/></h1>
			<hr>
			<c:set var="page" value="${param.page}"/>
			<c:if test="${empty page}">
				<c:set var="page" value="1"/>
			</c:if>
			<form>
				<c:if test="${page != 1}">
					<button name="page" value="${page - 1}">prev</button>	
				</c:if>
			</form>
			<form>
				<button name="page" value="${page + 1}">next</button>	
			</form>
			<c:forEach items="${applicationScope.orders}" var="order">
				<p>
				<c:out value="Order #${order.id} (${order.state})"/><br>
				<c:out value="date of creation: ${order.dateCreated}"/><br>
				<c:out value="date of last modifying: ${order.dateModified}"/><br>
					<form action="${context_path}/update-order" method="POST">
						<label for="state"><fmt:message key="set-state"/>:</label>
						<input type="text" id="state" name="state">
						<input type="hidden" name="order_id" value="${order.id}">
						<input type="submit" value="<fmt:message key="set"/>">
					</form>
				</p>
				<hr>
			</c:forEach>
		</body>
	</html>
</fmt:bundle>
