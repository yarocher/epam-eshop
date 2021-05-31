<!DOCTYPE html>
<html>
	<head>
		<title>Welcome!</title>
	</head>
	<body>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"" + application.getContextPath() + "/reg\">registation</a><br>"%>
		<%= session.getAttribute("user") != null ? ""  : "<a href=\"" + application.getContextPath() + "/login\">login</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"" + application.getContextPath() + "/controller/logout\">logout</a><br>"%>
		<%= session.getAttribute("user") == null ? ""  : "<a href=\"" + application.getContextPath() + "/account\">account</a><br>"%>
		<a href="http://localhost:8080/eshop/cart">cart</a><br>
		<a href="http://localhost:8080/eshop/controller/products">get list of all products</a><br>
		<a href="http://localhost:8080/eshop/controller/users">get list of all users</a><br>
		<a href="http://localhost:8080/eshop/controller/orders">get list of all orders</a><br>
	</body>
</html>
