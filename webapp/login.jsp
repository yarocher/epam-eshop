<!DOCTYPE html>
</html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<h1>Login</h1>
		<form action="http://localhost:8080/eshop/controller/login" method="POST">
			<label for="login">Login:</label><br>
			<input type="text" id="login" name="login"><br>
			<label for="password">Password:</label><br>
			<input type="text" id="password" name="password"><br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
