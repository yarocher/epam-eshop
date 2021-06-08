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
	</html>
		<head>
			<title><fmt:message key="sorry"/></title>
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
					<c:if test="${empty user}">
						<a href="${context_path}/login"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="log_in"/></button></a><br>
						<a href="${context_path}/reg"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="registration"/></button></a><br>
						<a href="${context_path}/controller/cart"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="cart"/></button></a><br>
					</c:if>
					<c:if test="${not empty user}">
						<a href="${context_path}/controller/logout"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="logout"/></button></a><br>
					</c:if>
					<c:if test="${user.role == 'CUSTOMER'}">
						<a href="${context_path}/controller/cart"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="cart"/></button></a><br>
						<a href="${context_path}/controller/account"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="account"/></button></a><br>
					</c:if>
					<c:if test="${user.role == 'ADMIN'}">
						<a href="${context_path}/controller/users"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-users"/></button></a><br>
						<a href="${context_path}/controller/orders"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-orders"/></button></a><br>
					</c:if>
				</div>
			</header>
			<h1><fmt:message key="sorry"/></h1>
			<p><fmt:message key="smth-went-wrong"/></p>		
		</body>
	</html>
</fmt:bundle>
