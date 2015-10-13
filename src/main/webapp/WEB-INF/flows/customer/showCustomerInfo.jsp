<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Customer Information</title>
</head>

<body>

	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Customer</h1>
				<p>Customer Confirmation</p>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<form:form modelAttribute="order" class="form-horizontal">
				<input type="hidden" name="_flowExecutionKey"
					value="${flowExecutionKey}" />

				<legend>Customer Details</legend>

				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="customerId" />
					<spring:message code="addCustomer.form.customerId.label" />
					</label>

					<div class="col-lg-10">${customer.customerId}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="firstName"><spring:message
							code="addCustomer.form.customerFirstName.label" /></label>
					<div class="col-lg-10">${customer.firstName}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="lastName"><spring:message
							code="addCustomer.form.customerLastName.label" /></label>
					<div class="col-lg-10">${customer.lastName}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="doorNo"><spring:message
							code="addCustomer.form.customerDoorNo.label" /></label>
					<div class="col-lg-10">${customer.billingAddress.doorNo}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="streetName"><spring:message
							code="addCustomer.form.customerStreetName.label" /></label>
					<div class="col-lg-10">${customer.billingAddress.streetName}
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="city"><spring:message
							code="addCustomer.form.customerCityName.label" /></label>
					<div class="col-lg-10">${customer.billingAddress.city}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="state"><spring:message
							code="addCustomer.form.customerStateName.label" /></label>
					<div class="col-lg-10">${customer.billingAddress.state}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="country"><spring:message
							code="addCustomer.form.customerCountryName.label" /></label>
					<div class="col-lg-10">${customer.billingAddress.country}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="zipCode"><spring:message
							code="addCustomer.form.customerZipCode.label" /></label>
					<div class="col-lg-10">${customer.billingAddress.zipCode}</div>
				</div>

				<div class="form-group">
					<label class="control-label col-lg-2" for="phoneNumber"><spring:message
							code="addCustomer.form.customerPhoneNumber.label" /></label>
					<div class="col-lg-10">${customer.phoneNumber}</div>
				</div>

			</form:form>
		</div>
	</div>
</body>
</html>
