<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
</html>
	<head>
		<title>Edit product</title>
	</head>
	<body>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=en">EN</a></div><br>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=ru">RU</a></div><br>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<h1>Edit product</h1>
		<form action="http://localhost:8080/eshop/controller/update-product" method="POST">
			<input type="hidden" name="product_id" value="${param.product_id}">
			<label for="name">Name:</label><br>
			<input type="text" id="name" name="name"><br>
			<label for="category">Category:</label><br>
			<input type="text" id="category" name="category"><br>
			<label for="description">Description:</label><br>
			<input type="text" id="description" name="description"><br>
			<label for="amount">Amount:</label><br>
			<input type="text" id="amount" name="amount"><br>
			<label for="price">Price:</label><br>
			<input type="text" id="price" name="price"><br>
			<label for="state">State:</label><br>
			<input type="text" id="state" name="state"><br>
			<input type="submit" value="Edit">
		</form>
	</body>
</html>
