<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link href="<c:url value="/resources/css/student.css" />"
	rel="stylesheet">
<title>Insert title here</title>

</head>
<c:choose>
	<c:when test="${user.email=='admin@gmail.com'}">
		<jsp:include page="adminHeader.jsp"></jsp:include></c:when>
	<c:otherwise><jsp:include page="studentHeader.jsp" /></c:otherwise>
</c:choose>

<br>
<br>
<br>
<body>
	<main id="main-content">
		<div class="container">
			<form:form modelAttribute="userPassword" method="post"
				action="/studentMVC/updatePassword" autocomplete="off">
				<c:if test="${param.passwordUpdated}">
					<div class="alert alert-success">Password Update Successful</div>
				</c:if>
				<form:input type="hidden" path="email" />

				<div class="row">
					<div class="col-12">

						<form:input placeholder="Enter Old Password" path="oldPassword"
							class=" form-control" autocomplete="off" type="password" />
						<span class="text-danger invalid-feedback" hidden="true"
							id="password_err">Incorrect password.</span>
					</div>

				</div>
				<br>
				<div class="row">
					<div class="col-12">

						<form:input path="newPassword" placeholder="Enter New Password"
							class=" form-control" required="true" autocomplete="off"
							type="password" />
						<div>
							<ul>
								<li class="text-danger" id="lenErr">Password should have at
									least 8 characters</li>
								<li class="text-danger" id="ucaseErr">Password should have
									at least one uppercase letter</li>
								<li class="text-danger" id="lcaseErr">Password should have
									at least one lowercase letter</li>
								<li class="text-danger" id="numberErr">Password should have
									at least one number</li>
								<li class="text-danger" id="scharErr">Password should have
									at least one special character</li>
							</ul>
						</div>
						<span class="text-danger invalid-feedback" hidden="true"
							id="newPassErr">Letters, special characters and spaces are
							not allowed.</span>
					</div>

				</div>
				<br>

				<div class="row">
					<div class="col-12">

						<input placeholder="Confirm New Password" class=" form-control"
							autocomplete="off" type="password" id="confirmPass" /> <span
							class="text-danger invalid-feedback" hidden="true"
							id="confirmNewPassErr">Does not match new password</span>
					</div>

				</div>
				<br>
				<form:button type="submit" value="Submit"
					class="btn btn-primary form-control" id="submit">Submit</form:button>
			</form:form>

		</div>
	</main>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="<c:url value="/resources/js/passwordUpdate.js" />"
	type="text/javascript"></script>
</html>