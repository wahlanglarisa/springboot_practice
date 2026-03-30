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
<br>
<br>
<br>
<br>

<body class="">
	<c:if
		test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
		<main id="main-content">
	</c:if>
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
				<div class="col-xl-4 ">
					<!-- Profile picture card-->
					<div class="card mb-4 mb-xl-0 shadow">
						<div class="card-header">Profile Picture</div>
						<div class="card-body text-center">
							<!-- Profile picture image-->
							<c:if
								test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
								<img class="img-account-profile rounded-circle mb-1"
									src="/studentMVC/image/${student.st_id }" alt=""
									id="image-view">

							</c:if>
							<c:if
								test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
								<img class="img-account-profile rounded-circle mb-1"
									src="<c:url value="/resources/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png"/>"
									alt="" id="image-view">
							</c:if>
							<!-- Profile picture help block-->
							<div class="small font-italic text-muted mb-4">JPG or PNG
								no larger than 5 MB</div>
							<input type="file" id="file" name="file"
								accept="image/jpeg,image/jpg,image/png" style="display: none">
							<span class="invalid-feedback" hidden="true" id="invalidFile">Please
								upload only image files of JPG, PNG and JPEG</span>
							<!-- Profile picture upload button-->
							<button class="btn btn-primary" type="button"
								id="newProfileButton"
								onclick="document.getElementById('file').click();">Upload
								new image</button>

						</div>
					</div>
				</div>
				<div class="col-xl-8">
					<!-- Account details card-->
					<div class="card mb-4 shadow">
						<div class="card-header">Personal Details</div>
						<div class="card-body">
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
										id="LnameErr">Numbers, special characters are not
										allowed</span>

								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-6">

									<form:input path="phone_no" placeholder="Phone Number"
										class=" form-control" required="true" autocomplete="off"
										maxlength="10" type="tel" />

									<c:if test="${param.phoneNoAlreadyExists}">
										<span class="text-danger">This phone number already
											exists</span>
									</c:if>
									<span class="text-danger invalid-feedback" hidden="true"
										id="phoneCharErr">Letters, special characters and
										spaces are not allowed.</span><span
										class="text-danger invalid-feedback" hidden="true"
										id="phoneLength">Phone number should have 10 digits.</span> <span
										class="text-danger invalid-feedback" id="dupPhoneNo"
										hidden="true">This phone number already exists</span>
								</div>
								<div class="col-6" id="OTPDiv">

									<input placeholder="Enter OTP sent to phone number"
										class="form-control" id="OTP-phone" autocomplete="off"
										name="OTP-phone" disabled="true" /> <span
										class="text-danger invalid-feedback" id="otpPhoneInvalid"
										hidden="true">OTP is invalid</span>
								</div>

							</div>
							<br>
							<div class="row">
								<div class="col-6">

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

								<div class="col-6" id="OTPDiv">

									<input placeholder="Enter OTP sent to email"
										class="form-control" id="OTP-email" autocomplete="off"
										name="OTP-email" disabled="true" /> <span
										class="text-danger invalid-feedback" id="otpInvalid"
										hidden="true">OTP is invalid</span>
								</div>
							</div>
							<br>

							<c:if
								test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
								<div class="row">

									<div class="col-12">

										<form:input path="password" placeholder="Password Here"
											class="form-control" required="true" autocomplete="off"
											type="password" />
										<div>
											<ul>
												<li class="text-danger" id="lenErr">Password should
													have at least 8 characters</li>
												<li class="text-danger" id="ucaseErr">Password should
													have at least one uppercase letter</li>
												<li class="text-danger" id="lcaseErr">Password should
													have at least one lowercase letter</li>
												<li class="text-danger" id="numberErr">Password should
													have at least one number</li>
												<li class="text-danger" id="scharErr">Password should
													have at least one special character</li>
											</ul>
										</div>
									</div>
								</div>
							</c:if>



						</div>
					</div>
				</div>
			</div>
			<c:if
				test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
				<jsp:include page="updateProfile.jsp"></jsp:include>
			</c:if>
			<div class="row p-2" id="buttonDiv">

				<form:button type="submit" class="btn btn-primary" id="submitButton"> Submit</form:button>
			</div>
		</form:form>

	</div>
	<c:if
		test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
		</main>
	</c:if>
</body>
		<jsp:include page="footer.jsp" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<script>
console.log(${student.st_id })
	var contextPath = "${pageContext.request.contextPath}";

	/*var requestURI = "${requestScope['jakarta.servlet.forward.request_uri']}";
	stateElement=document.getElementById("state_code")
	district_element=document.getElementById("district_code")
	if(requestURI=='/studentMVC/updatestudent/${student.st_id}'){
		console.log(document.getElementById("state_code")[0])
		state_codes=stateElement[0]
		state_codes.remove();
		district_code=district_element[0]
		district_code.remove();
		stateElement.removeAttribute("disabled")
		district_element.removeAttribute("disabled")
	}
		document.getElementById("file").onchange = function(event){
		    const file = event.target.files[0];

		    const reader = new FileReader();
		    reader.onload = function(e){
		        document.getElementById("image-view").src = e.target.result;
		    };

		    reader.readAsDataURL(file);
		
	}*/
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

