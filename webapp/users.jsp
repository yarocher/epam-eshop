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
			<title><fmt:message key="all-users"/></title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<h1><fmt:message key="all-users"/></h1>
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
			<c:forEach items="${applicationScope.users}" var="user">
				<p>
				<c:out value="${user.login} (${user.state})"/><br>
					<form action="${context_path}/controller/update-user" method="POST">
						<input type="hidden" name="user_id" value="${user.id}">
						<c:if test="${user.state != 'BLOCKED'}">
							<input type="hidden" name="state" value="BLOCKED">
							<input type="submit" value="block">
						</c:if>
						<c:if test="${user.state == 'BLOCKED'}">
							<input type="hidden" name="state" value="ACTIVE">
							<input type="submit" value="unblock">
						</c:if>
					</form>
					<form action="${context_path}/controller/delete-user" method="DELETE">
						<button name="user_id" value="${user.id}"><fmt:message key="delete"/></button>
					</form>
				</p>
				<hr>
			</c:forEach>
		</body>
	</html>
</fmt:bundle>
