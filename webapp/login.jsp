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
			<title><fmt:message key="log_in"/></title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<h1><fmt:message key="log_in"/></h1>
			<form action="${context_path}/controller/login" method="POST">
				<label for="login"><fmt:message key="login"/>:</label><br>
				<input type="text" id="login" name="login"><br>
				<label for="password"><fmt:message key="password"/>:</label><br>
				<input type="text" id="password" name="password"><br>
				<input type="submit" value="<fmt:message key="submit"/>">
			</form>
		</body>
	</html>
</fmt:bundle>
