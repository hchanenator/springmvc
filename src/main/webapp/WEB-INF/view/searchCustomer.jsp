<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script type="text/javascript"
	src="/webstore/resource/js/controllers.js"></script>
<title>Search Customer</title>
</head>
<body>
	<section>
	<div class="jumbotron">
		<div class="container">
			<h1>Customer</h1>
			<p>Find Customer</p>
		</div>
	</div>
	</section>
	<section class="container"> <form:form
		modelAttribute="customer" class="form-horizontal">
		<fieldset>
			<legend>Customer Details</legend>

			<div class="form-group">
				<label class="control-label col-lg-2 col-lg-2" for="customerId" />
				<spring:message code="addCustomer.form.customerId.label" />
				</label>

				<div class="col-lg-10">
					<form:input id="customerId" path="customerId" type="text"
						class="form:input-large" />
				</div>
			</div>

			<input type="hidden" name="_flowExecutionKey"
				value="${flowExecutionKey}" />

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<a href="<spring:url value="/customer?customerId=${customerId}" />" class="btn btn-primary"> <spring:message code="findCustomer.find.label" /> </a>						
					<button id="btnCancel" class="btn btn-default"
						name="_eventId_cancel">Cancel</button>
				</div>
			</div>

		</fieldset>
	</form:form> </section>
</body>
</html>