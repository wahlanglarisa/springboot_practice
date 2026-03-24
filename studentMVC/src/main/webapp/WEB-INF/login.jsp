<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/student.css" />"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
<nav id="main-navbar"
	class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
	<!-- Container wrapper -->
	<div class="container-fluid">
		<!-- Toggle button -->
		

		<!-- Brand -->
		<a class="navbar-brand" th:href="@{/student/studentHomepage}"> <img
			th:src="@{/ChatGPT Image Sep 3, 2025, 01_04_50 PM.png}" height="25"
			alt="" loading="lazy" /> <span> Student Course Management
		</span>
		</a>
		<!-- Search form -->


		<!-- Right links -->

	</div>
	<!-- Container wrapper -->
</nav>
	<br>
	<br>
	<br>

	<div class="container shadow p-5">
		<c:if test="${param.loginerror}">
			<div class="alert alert-danger col-8">Login Failed. Please
				enter correct credentials</div>
		</c:if>
		
		<c:if test="${param.loggedOut}">
			<div class="alert alert-success col-8">User logged out
				Successfully</div>
		</c:if>
		<form:form method="post" 
			action="${pageContext.request.contextPath}/login" class=" justify-content-center ">
			
			<div class="row">
				<label class="col-3 form-label">Enter the email</label>
				<div class="col-5">
					<input name="username" id="email" placeholder="Enter your email ID"
						class="form-control shadow" />
				</div>
				<span class="col-2 text-danger" id="EmailCharErr" hidden="true">Email
					ID is invalid. Should be in the format user_name@domain.com</span>
			</div>
			<br>

			<div class="row">
				<label class="col-3 form-label">Enter the password</label>
				<div class="col-5 ">

					<input name="password" placeholder="Enter your password"
						class="form-control shadow" type="password" id="password" />
				</div>
			</div>
			<br>
			<div class="row">
				<button type="submit" class="btn btn-primary shadow col-3">Login</button>
				<a class="link col-3" href="/studentMVC/addstudent">New User?
					Register Here</a>
			</div>
			<br>
		</form:form>
	</div>
</body>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/login.js" />"></script>
</html>