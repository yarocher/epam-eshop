<!DOCTYPE html>
<html>
	<head>
		<title>Products</title>
	</head>
	<body>
		<a href="http://localhost:8080/eshop/reg">registation</a><br>
		<a href="http://localhost:8080/eshop/login">login</a><br>
		<a href="http://localhost:8080/eshop/logout">logout</a><br>
		<h1>Products</h1>
		<p><%=application.getAttribute("products")%></p>
	</body>
</html>
