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
			<title>${all_products_label}</title>
		</head>
		<body>
			<div align="right"><a align="right" href="${uri}?lang=en"><fmt:message key="en"/></a></div><br>
			<div align="right"><a align="right" href="${uri}?lang=ru"><fmt:message key="ru"/></a></div><br>
			<a href="${context_path}/"><fmt:message key="main"/></a><br>
			<c:if test="${empty user}">
				<a href="${context_path}/login"><fmt:message key="log_in"/></a><br>
				<a href="${context_path}/reg"><fmt:message key="registration"/></a><br>
				<a href="${context_path}/controller/cart"><fmt:message key="cart"/></a><br>
			</c:if>
			<c:if test="${not empty user}">
				<a href="${context_path}/controller/logout"><fmt:message key="logout"/></a><br>
			</c:if>
			<c:if test="${user.role == 'CUSTOMER'}">
				<a href="${context_path}/controller/cart"><fmt:message key="cart"/></a><br>
				<a href="${context_path}/controller/account"><fmt:message key="account"/></a><br>
			</c:if>
			<c:if test="${user.role == 'ADMIN'}">
				<a href="${context_path}/controller/users"><fmt:message key="all-users"/></a><br>
				<a href="${context_path}/controller/orders"><fmt:message key="all-orders"/></a><br>
			</c:if>
			<h1><fmt:message key="all-products"/></h1>
				<c:if test="${sessionScope.user.role == 'ADMIN'}">
				<form action="${context_path}/create-product">
						<button><fmt:message key="create"/></button>
					</form>
				</c:if>
			<form>
				<label for="name"><fmt:message key="name"/>:</label>
				<input type="text" id="name" name="name">
				<label for="category"><fmt:message key="category"/>:</label>
				<input type="text" id="category" name="category">
				<label for="min_price"><fmt:message key="min"/> <fmt:message key="price"/>:</label>
				<input type="text" id="min_price" name="price_min">
				<label for="max_price"><fmt:message key="max"/> <fmt:message key="price"/>:</label>
				<input type="text" id="max_price" name="price_max">
				<label for="sort_by"><fmt:message key="sort-by"/>:</label>
				<input type="text" id="sory_by" name="sort_by">
				<label for="desc"><fmt:message key="desc"/>:</label>
				<input type="radio" id="desc" name="desc"><br>

				<input type="submit" value="<fmt:message key="search"/>"><br><br>
			</form>
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
			<hr>
			<c:forEach items="${applicationScope.products}" var="product">
				<c:if test="${sessionScope.user.role == 'ADMIN'}">
					<strong>
						<c:out value="${product.name}"/>
						(<fmt:message key="${product.state}"/>)
					</strong><br>
					<fmt:message key="category"/><c:out value=": ${product.category}"/><br>
					<fmt:message key="description"/><c:out value=": ${product.description}"/><br>
					<fmt:message key="price"/><c:out value=": ${product.price}"/><br>
					<fmt:message key="amount"/><c:out value=": ${product.amount}"/><br>
					<fmt:message key="date-modified"/><c:out value=": ${product.dateModified}"/><br>
						<form action="${context_path}/edit-product">
							<button name="product_id" value="${product.id}"><fmt:message key="edit"/></button>
						</form>
						<form action="${context_path}/controller/delete-product">
							<button name="product_id" value="${product.id}"><fmt:message key="delete"/></button>
						</form>
					<hr>
				</c:if>
				<c:if test="${sessionScope.user.role != 'ADMIN' && product.state != 'HIDDEN'}">
					<strong>
						<c:out value="${product.name}"/>
					</strong><br>
					<c:out value="category: ${product.category}"/><br>
					<c:out value="description: ${product.description}"/><br>
					<c:out value="price: ${product.price}"/><br>
					<c:out value="amount: ${product.amount}"/><br>
					<c:out value="last modifying: ${product.dateModified}"/><br>
					<c:if test="${empty user || user.role == 'CUSTOMER'}">
						<form action="${context_path}/controller/add-item" method="POST">
							<button name="product_id" value="${product.id}"><fmt:message key="add"/></button>
						</form>
					</c:if>
					<c:if test="${sessionScope.user.role == 'ADMIN'}">
						<form action="${context_path}/edit-product">
							<button name="product_id" value="${product.id}"><fmt:message key="edit"/></button>
						</form>
						<form action="${context_path}/controller/delete-product">
							<button name="product_id" value="${product.id}"><fmt:message key="delete"/></button>
						</form>
					</c:if>
					<hr>
				</c:if>
			</c:forEach>
		</body>
	</html>
</fmt:bundle>
