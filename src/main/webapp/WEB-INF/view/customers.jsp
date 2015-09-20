<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Products</title>
</head>
<body>

	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Customers</h1>
			<p>All the customers of our store</p>
		</div>
	</div>
	</section>

	<section class="container">
	<div class="row">
		<c:forEach items="${customers}" var="customer">
			<div class="col-am-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Customer ID: ${customer.customerId }</h3>
						<p>First Name: ${customer.firstName }</p>
						<p>Last Name: ${customer.lastName }</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	</section>

</body>
</html>