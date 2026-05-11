
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
%>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js">
	
</script>
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
				alt="" loading="lazy" /> <span> Student Course Management </span>
			</a>
			<!-- Search form -->


			<!-- Right links -->

		</div>
		<!-- Container wrapper -->
	</nav>
	<br>
	<br>
	<br>
	<c:if test="${param.otpMisMatch}">
		<div class="alert alert-danger col-8">OTP invalid</div>
	</c:if>
	<div class="container shadow p-5  justify-content-center d-flex">


		<c:if test="${param.loginerror}">
			<div class="alert alert-danger col-8">Login Failed. Please
				enter correct credentials</div>
		</c:if>

		<c:if test="${param.loggedOut}">
			<div class="alert alert-success col-8">User logged out
				Successfully</div>
		</c:if>
		<form:form method="post"
			action="${pageContext.request.contextPath}/verifyOTP"
			class=" justify-content-center ">

			<div class="row">
				<label class="col-3 form-label">Enter the OTP sent to email</label>
				<div class="col-5">
					<input name="emailOTP" id="emailOTP" placeholder="Email OTP"
						class="form-control shadow" type="tel" maxlength="5" /> <span
						class="col-2 text-danger" id="EmailOTPEmpty" hidden="true">Email
						OTP is required</span>
					<c:if test="${param.emailOTPinvalid }">

						<span class="col-2 text-danger">Email OTP is invalid</span>
					</c:if>
				</div>

			</div>
			<br>

			<div class="row">
				<label class="col-3 form-label">Enter the OTP sent to phone
					number</label>
				<div class="col-5 ">

					<input name="phoneOTP" placeholder="Phone number OTP"
						class="form-control shadow" type="tel" id="phoneOTP" maxlength="5" />
					<span class="col-2 text-danger" id="PhoneOTPEmpty" hidden="true">Phone
						number OTP is required</span>
					<c:if test="${param.phoneOTPinvalid }">

						<span class="col-2 text-danger">Phone number OTP is invalid</span>
					</c:if>
				</div>

			</div>
			<br>
			<div class="row  justify-content-center d-flex">
				<button type="submit" class="btn btn-primary shadow col-3">Submit</button>

			</div>
			<br>
		</form:form>
	</div>
</body>
<jsp:include page="footer.jsp" />
<script src="<c:url value="/resources/js/verifyOTP.js" />"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

</html>