<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/student.css" />"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<c:choose>
	<c:when
		test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
		<nav class="navbar bg-body-tertiary">
			<div class="container-fluid">
				<a class="navbar-brand text-large" href="/studentMVC/"><strong>
						Student Course Management</strong> </a>
			</div>
		</nav>
	</c:when>
	<c:otherwise>
		<jsp:include page="studentHeader.jsp" />
	</c:otherwise>
</c:choose>
			<br> <br>
			<br> <br>

<body class="">
	<main id="main-content">
		<div class="mx-auto justify-content-center d-flex">

			<form:form modelAttribute="student" method="post"
				action="/studentMVC/saveStudent" class="needs-validation"
				autocomplete="off" enctype="multipart/form-data">
				<div class="alert alert-danger" id="EmptyError" hidden="true">Please
					fill up all fields</div>

				<c:if test="${param.success}">
					<div class="alert alert-success">
						User Created Successfully. <a href="/studentMVC/" class="link">Click
							Here to Login</a>
					</div>
				</c:if>
				<c:if test="${param.updateSuccess}">
					<div class="alert alert-success">Update Successful</div>
				</c:if>
				<c:if test="${param.emptyFields}">
					<div class="alert alert-danger">Please fill up all fields</div>
				</c:if>
				<c:choose>
					<c:when
						test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
						<div class="text-center">
							<h4>Register Here</h4>
						</div>
					</c:when>
					<c:otherwise>
						<div class="text-center">
							<h1>Update User Details</h1>
						</div>
					</c:otherwise>
				</c:choose>
				<form:input type="hidden" path="st_id" class="col-5" />
				<form:input type="hidden" path="user_id" class="col-5" />

				<div class="row">

					<div class="col-6">

						<form:input path="first_name" placeholder="First Name"
							class="form-control" required="true" />
						<span class="invalid-feedback" hidden="true" id="FnameErr">Numbers,
							special characters are not allowed</span>
					</div>

					<div class="col-6">

						<form:input path="last_name" placeholder="Last Name"
							class="form-control" required="true" />
						<span class="text-danger invalid-feedback" hidden="true"
							id="LnameErr">Numbers, special characters are not allowed</span>

					</div>
				</div>
				<br>

				<div class="row">
					<div class="col-12">

						<form:input path="phone_no" placeholder="Phone Number"
							class=" form-control" required="true" autocomplete="off" />

						<c:if test="${param.phoneNoAlreadyExists}">
							<span class="text-danger">This phone number already exists</span>
						</c:if>
						<span class="text-danger invalid-feedback" hidden="true"
							id="phoneCharErr">Letters, special characters and spaces
							are not allowed.</span><span class="text-danger invalid-feedback"
							hidden="true" id="phoneLength">Phone number should have 10
							digits.</span> <span class="text-danger invalid-feedback"
							id="dupPhoneNo" hidden="true">This phone number already
							exists</span>
					</div>

				</div>
				<br>
				<div class="row">
					<div class="col-12">

						<form:input path="email" placeholder="Email Here"
							class="form-control" required="true" autocomplete="off" />
						<c:if test="${param.emailAlreadyExists }">
							<span class="text-danger invalid-feedback">This email
								already exists</span>
						</c:if>
						<span class="text-danger invalid-feedback" id="dupEmail"
							hidden="true">This email already exists</span> <span
							class="text-danger invalid-feedback" hidden="true"
							id="EmailCharErr">Invalid email id. Use format
							user@email.com</span>
					</div>

				</div>
				<br>
				<div class="row">
					<div class="col-6">

						<form:input path="address" placeholder="Address Here"
							class="col-8 form-control" required="true" />
					</div>

					<div class="col-6">

						<form:select path="country_code" class="form-select input-small "
							data-size="5">
							<option value="0">Select a Country</option>
							<form:options items="${countries}" itemLabel="countryName"
								itemValue="countryCode" />
						</form:select>
						<span class="text-danger invalid-feedback" id="countryEmpty"
							hidden="true">Please select a country</span>

					</div>
				</div>
				<br>
				<div class="row">

					<div class="col-6">

						<form:select path="state_code" class="form-select input-small "
							data-width="5" disabled="true">
							<option value="0">State : Please select a country first</option>
						</form:select>
						<span class="text-danger invalid-feedback" id="stateEmpty"
							hidden="true">Please select a state</span>
					</div>

					<div class="col-6">

						<form:select path="district_code"
							class="form-select input-small minimize-height" data-width="5"
							disabled="true">
							<option value="0">District : Please select a state first</option>
						</form:select>
						<span class="text-danger col-7" id="districtEmpty" hidden="true">Please
							select a district</span>
					</div>

				</div>
				<br>
			
				<div class="row">

					<div class="col-12">

						<input name="file" text="Upload profile picture"
							class=" form-control" type="file" />
					</div>
				</div>
				<div class="row p-2" id="buttonDiv">

					<form:button type="submit" class="btn btn-primary"
						id="submitButton"> Submit</form:button>
				</div>
			</form:form>
		</div>
	</main>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<c:choose>
	<c:when
		test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
		<script type="text/javascript"
			src="<c:url value="/resources/js/student.js" />"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript"
			src="<c:url value="/resources/js/updateStudentData.js" />"></script>
	</c:otherwise>
</c:choose>
</html>

