<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="uri" scope="session" value="${pageContext.request.requestURI}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="context_path" scope="page" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${sessionScope.lang}"/>
<fmt:setLocale value="${lang}"/>
<fmt:bundle basename="content">
	<!DOCTYPE html>
	<html>
		<head>
			<title><fmt:message key="all-users"/></title>
		</head>
		<body style="background: #fcf767;">
			<header style="border:1px solid black; width: 100%; background: #ffbe2a;">
				<h1 align="center">Eshop</h1>
				<form align="right">
					<c:if test="${lang == 'ru'}">
						<input type="hidden" name="lang" value="en">
						<input type="submit" value="<fmt:message key="en"/>" style="background: #0ff9c9c; color: #0027ff; font-size: 15px; border-radius: 15px;">
					</c:if>
					<c:if test="${lang == 'en' || empty lang}">
						<input type="hidden" name="lang" value="ru">
						<input type="submit" value="<fmt:message key="ru"/>" style="background: #0ff9c9c; color: #0027ff; font-size: 15px; border-radius: 15px;">
					</c:if>
				</form>
				<div align="left" style="border:1px solid black; width:fit-content; background: #69ecc6;">
					<strong><fmt:message key="navigator"/></strong><br>
					<a href="${context_path}/"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="main"/></button></a><br>
					<a href="${context_path}/controller/logout"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="logout"/></button></a><br>
					<c:if test="${user.role == 'ADMIN'}">
						<a href="${context_path}/controller/orders"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-orders"/></button></a><br>
					</c:if>
					<a href="${context_path}/controller/products"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-products"/></button></a>
				</div>
			</header>
			<h1 align="center"><fmt:message key="all-users"/></h1>
				<c:set var="page" value="${param.page}"/>
				<c:if test="${empty page}">
					<c:set var="page" value="1"/>
				</c:if>
				<br>
				<fmt:message key="page"/><c:out value=" : ${page}"/>
				<div>
					<form>
						<c:if test="${page != 1}">
							<button name="page" value="${page - 1}" style="background: #92d6fb; border-radius: 15px;"><fmt:message key="prev"/></button>	
						</c:if>
					</form>
					<form>
						<button name="page" value="${page + 1}" style="background: #92d6fb; border-radius: 15px;"><fmt:message key="next"/></button>	
					</form>
				</div>
			<hr>
			<div align="center">
				<c:forEach items="${applicationScope.users}" var="user">
					<c:if test="${user.role != 'ADMIN'}">
						<p>
						<c:out value="${user.login} ("/><fmt:message key="${user.state}"/>)<br>
							<form action="${context_path}/controller/update-user" method="POST">
								<input type="hidden" name="user_id" value="${user.id}">
								<c:if test="${user.state != 'BLOCKED'}">
									<input type="hidden" name="state" value="BLOCKED">
									<input type="submit" value="<fmt:message key="block"/>" style="background: #ff6565; color: #b30000; font-size: 15px; border-radius: 15px;">
								</c:if>
								<c:if test="${user.state == 'BLOCKED'}">
									<input type="hidden" name="state" value="ACTIVE">
									<input type="submit" value="<fmt:message key="unblock"/>" style="background: #96ff7a; color: #166800; font-size: 15px; border-radius: 15px;">
								</c:if>
							</form>
							<form action="${context_path}/controller/delete-user" method="DELETE">
								<button name="user_id" value="${user.id}" style="background: #7a98ff; color: #af0101; font-size: 15px; border-radius: 15px;"><fmt:message key="delete"/></button>
							</form>
						</p>
						<hr>
					</c:if>
				</c:forEach>
			</div>
		</body>
	</html>
</fmt:bundle>
