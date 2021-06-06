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
			<title><fmt:message key="log_in"/></title>
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
					<a href="${context_path}/reg"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="registration"/></button></a><br>
					<a href="${context_path}/controller/cart"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="cart"/></button></a><br>
					<a href="${context_path}/controller/products"><button style="background: #fff40e; border-radius: 15px;"><fmt:message key="all-products"/></button></a>
				</div>
			</header>
			<div align="center">
				<h1><fmt:message key="log_in"/></h1>
				<form action="${context_path}/controller/login" method="POST">
					<label for="login"><fmt:message key="login"/>:</label><br>
					<input type="text" id="login" name="login" style="background: #fffcb8; border-radius: 10px;"><br>
					<label for="password"><fmt:message key="password"/>:</label><br>
					<input type="password" id="password" name="password" style="background: #fffcb8; border-radius: 10px;"><br>
					<br>
					<input type="submit" value="<fmt:message key="submit"/>" style="background: blue; font-size: 20px; border-radius: 15px; color: white;">
				</form>
			</div>
		</body>
	</html>
</fmt:bundle>
