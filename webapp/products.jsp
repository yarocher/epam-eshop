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
			<title><fmt:message key="all-products"/></title>
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
			<h1 align="center"><fmt:message key="all-products"/></h1>
				<c:if test="${sessionScope.user.role == 'ADMIN'}">
				<form align="right" action="${context_path}/create-product">
						<button style="background: #02bd3b; border-radius: 15px;"><fmt:message key="create"/></button>
					</form>
				</c:if>
			<form style="border:1px solid black; width:fit-content; background: #cdff00;">
				<strong><fmt:message key="search"/></strong><br>
				<label for="search_by"><fmt:message key="search_by"/>:</label>
				<select name="search_by" style="background: #fce3ff;">
					<option value="name"><fmt:message key="name"/></option>
					<option value="category"><fmt:message key="category"/></option>
				</select>
				<input type="text" id="filter" name="filter" style="background: #fce3ff;"><br>

				<label for="min_price"><fmt:message key="min"/> <fmt:message key="price"/>:</label>
				<input type="text" id="min_price" name="price_min" style="background: #fce3ff;"><br>
				<label for="max_price"><fmt:message key="max"/> <fmt:message key="price"/>:</label>
				<input type="text" id="max_price" name="price_max" style="background: #fce3ff;"><br>

				<label for="sort_by"><fmt:message key="sort-by"/>:</label>
				<select name="sort_by" style="background: #fce3ff;">
					<option value="name"><fmt:message key="name"/></option>
					<option value="price"><fmt:message key="price"/></option>
					<option value="date_modified"><fmt:message key="date"/></option>
				</select>

				<label for="desc"><fmt:message key="desc"/>:</label>
				<input type="checkbox" id="desc" name="desc" style="background: #fce3ff;"><br>

				<input type="submit" value="<fmt:message key="search"/>" style="background: #f092fb; border-radius: 15px;"><br>
			</form>
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
				<a id="elements"/>
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

						<c:set var="date" value="${product.dateModified}"/>
						<fmt:message key="date-modified"/><c:out value=": ${date.month} ${date.dayOfMonth} ${date.year}"/><br>

							<form align="center" action="${context_path}/edit-product">
								<button name="product_id" value="${product.id}" style="background: #fffb00; font-size: 15px; border-radius: 15px;"><fmt:message key="edit"/></button>
							</form>
							<form align="center" action="${context_path}/controller/delete-product">
								<button name="product_id" value="${product.id}" style="background: #7a98ff; color: #af0101; font-size: 15px; border-radius: 15px;"><fmt:message key="delete"/></button>
							</form>
						<hr>
					</c:if>
					<c:if test="${sessionScope.user.role != 'ADMIN' && product.state != 'HIDDEN'}">
						<strong>
							<c:out value="${product.name}"/>
						</strong><br>
						<fmt:message key="category"/><c:out value=": ${product.category}"/><br>
						<fmt:message key="description"/><c:out value=": ${product.description}"/><br>
						<fmt:message key="price"/><c:out value=": ${product.price}"/><br>
						<fmt:message key="amount"/><c:out value=": ${product.amount}"/><br>

						<c:set var="date" value="${product.dateModified}"/>
						<fmt:message key="date-modified"/><c:out value=": ${date.month} ${date.dayOfMonth} ${date.year}"/><br>

						<c:if test="${empty user || user.role == 'CUSTOMER'}">
							<form action="${context_path}/controller/add-item" method="POST">
								<input type="hidden" name="product_id" value="${product.id}">
								<input type="hidden" name="search_by" value="${param.search_by}">
								<input type="hidden" name="filter" value="${param.filter}">
								<input type="hidden" name="price_min" value="${param.price_min}">
								<input type="hidden" name="price_max" value="${param.price_max}">
								<input type="hidden" name="sort_by" value="${param.sort_by}">
								<input type="hidden" name="page" value="${param.page}">
								<input type="submit" value="<fmt:message key="add"/>" style="background: #c9ecff; font-size: 15px; border-radius: 15px;">
							</form>
						</c:if>
						<c:if test="${sessionScope.user.role == 'ADMIN'}">
							<form align="right" action="${context_path}/edit-product">
								<button name="product_id" value="${product.id}"><fmt:message key="edit"/></button>
							</form>
							<form align="right" action="${context_path}/controller/delete-product">
								<button name="product_id" value="${product.id}"><fmt:message key="delete"/></button>
							</form>
						</c:if>
						<hr>
					</c:if>
				</c:forEach>
			</div>
		</body>
	</html>
</fmt:bundle>
