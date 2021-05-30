<!DOCTYPE html>
<html>
	<head>
		<title>Orders</title>
	</head>
	<body>
		<a href="http://localhost:8080/eshop/reg">registation</a><br>
		<a href="http://localhost:8080/eshop/login">login</a><br>
		<a href="http://localhost:8080/eshop/logout">logout</a><br>
		<h1>Orders</h1>
		<p><%=application.getAttribute("orders")%></p>
	</body>
</html>
