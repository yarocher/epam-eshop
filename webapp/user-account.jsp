<!DOCTYPE html>
<html>
	<head>
		<title>User account</title>
	</head>
	<body>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=en">EN</a></div><br>
		<div align="right"><a align="right" href="<%=request.getRequestURI()%>?lang=ru">RU</a></div><br>
		<a href="http://localhost:8080/eshop/">main</a><br>
		<h1>User account</h1>
		<p><%= session.getAttribute("user") %></p>
		<% java.util.List <com.eshop.model.entity.Order> orders = ((com.eshop.model.entity.User) session.getAttribute("user")).getOrders();%>
		<% for (com.eshop.model.entity.Order o: orders) out.write(o.toString() + "<br>"); %>
	</body>
</html>
