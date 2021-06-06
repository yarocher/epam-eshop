<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctm" uri="WEB-INF/tags.tld" %>
<c:set var="uri" scope="session" value="${pageContext.request.requestURI}"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="context_path" scope="page" value="${pageContext.request.contextPath}"/>
<c:set var="lang" value="${sessionScope.lang}"/>
<fmt:setLocale value="${lang}"/>
<fmt:bundle basename="content">
	<!DOCTYPE html>
	<html>
		<head>
			<title><fmt:message key="all-orders"/></title>
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
					<c:if test="${not empty user}">
						<a href="${context_path}/controller/logout"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="logout"/></button></a><br>
					</c:if>
					<c:if test="${user.role == 'ADMIN'}">
						<a href="${context_path}/controller/users"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-users"/></button></a><br>
					</c:if>
					<a href="${context_path}/controller/products"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-products"/></button></a>
				</div>
			</header>
			<h1 align="center"><fmt:message key="all-orders"/></h1>
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
			<div align="center">
			<a id="elements"/>
				<c:forEach items="${applicationScope.orders}" var="order">
					<p>
						<strong><fmt:message key="order"/><c:out value=" #${order.id}"/> (<fmt:message key="${order.state}"/>)</strong><br>
						<fmt:message key="date-created"/>: <ctm:date date="${order.dateCreated}"/><br>
						<fmt:message key="date-modified"/>: <ctm:date date="${order.dateModified}"/><br>

					</p>
						<form action="${context_path}/controller/update-order" method="POST">
							<input type="hidden" name="order_id" value="${order.id}">
							<input type="hidden" name="page" value="${param.page}">
							<label for="state"><fmt:message key="set-state"/>:</label>
							<select name="state" style="background: #fce3ff;">
								<option value="NEW"><fmt:message key="NEW"/></option>
								<option value="PAYED"><fmt:message key="PAYED"/></option>
								<option value="CANCELLED"><fmt:message key="CANCELLED"/></option>
							</select>
							<input type="submit" value="<fmt:message key="set"/>" style="background: #f092fb; border-radius: 15px;">
						</form>
					<hr>
				</c:forEach>
			</div>
		</body>
	</html>
</fmt:bundle>
