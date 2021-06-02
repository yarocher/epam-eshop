<!DOCTYPE html>
</html>
	<head>
		<title>Registration</title>
	</head>
	<body>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=en">EN</a></div><br>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=ru">RU</a></div><br>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<h1>Registration</h1>
		<form action="http://localhost:8080/eshop/controller/reg" method="POST">
			<label for="login">Login:</label><br>
			<input type="text" id="login" name="login"><br>
			<label for="password">Password:</label><br>
			<input type="text" id="password" name="password"><br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
