<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<c:if
			test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
			<div class="col-xl-4 ">
				<!-- Profile picture card-->
				<div class="card mb-4 mb-xl-0 shadow">
					<div class="card-header">Profile Picture</div>
					<div class="card-body text-center">
						<!-- Profile picture image-->
						<c:if
							test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
							<img class="img-account-profile rounded-circle mb-1"
								src="/studentMVC/image/${student.st_id }" alt="" id="image-view">

						</c:if>
						<c:if
							test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
							<img class="img-account-profile rounded-circle mb-1"
								src="<c:url value="/resources/vecteezy_user-profile-icon-profile-avatar-user-icon-male-icon_20911737.png" />"
								alt="" id="image-view">
						</c:if>
						<!-- Profile picture help block-->
						<div class="small font-italic text-muted mb-4">JPG or PNG no
							larger than 5 MB</div>
						
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
		</c:if>
		<input type="file" id="file" name="file"
							accept="image/jpeg,image/jpg,image/png" style="display: none">
		<c:choose>
			<c:when
				test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
				<div class="col-xl-8">
			</c:when>
			<c:otherwise>
				<div class="col-xl-8 container-fluid">
			</c:otherwise>
		</c:choose>
		<!-- Account details card-->
		<div class="card mb-4 shadow">
			<div class="card-header">Personal Details</div>
			<div class="card-body">
				<div class="row">

					<div class="col-6 form-group">
						<label for="first_name" class="form-label">First Name : </label>
						<form:input path="first_name" placeholder="First Name"
							class="form-control" required="true" />
						<span class="invalid-feedback" hidden="true" id="FnameErr">Numbers,
							special characters are not allowed</span> <span
							class="text-danger invalid-feedback" hidden="true"
							id="FnameEmptyErr">First name should have more than 1
							character</span>
					</div>

					<div class="col-6 form-group">
						<label for="last_name" class="form-label">Last Name : </label>
						<form:input path="last_name" placeholder="Last Name"
							class="form-control" required="true" />
						<span class="text-danger invalid-feedback" hidden="true"
							id="LnameErr">Numbers, special characters are not allowed</span>
						<span class="text-danger invalid-feedback" hidden="true"
							id="LnameEmptyErr">Last name should have more than 1
							character</span>

					</div>
				</div>
				<br>

				<div class="row">
					<div class="col-6 form-group">
						<label for="phone_no" class="form-label">Phone Number : </label>

						<form:input path="phone_no" placeholder="Phone Number"
							class=" form-control" required="true" autocomplete="off"
							maxlength="10" type="tel" />

						<c:if test="${param.phoneNoAlreadyExists}">
							<span class="text-danger">This phone number already exists</span>
						</c:if>
						<span class="text-danger invalid-feedback" hidden="true"
							id="phoneCharErr">Letters, special characters and spaces
							are not allowed.</span><span class="text-danger invalid-feedback"
							hidden="true" id="phoneReqErr">Phone number is required</span><span
							class="text-danger invalid-feedback" hidden="true"
							id="phoneLength">Phone number should have 10 digits.</span> <span
							class="text-danger invalid-feedback" id="dupPhoneNo"
							hidden="true">This phone number already exists</span>
					</div>
					<div class="col-6 form-group" id="OTPDiv">

						<label for="phone_no" class="form-label">Email : </label>

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
							user@email.com</span> <span class="text-danger invalid-feedback"
							hidden="true" id="emailReqErr">Email is required</span>
					</div>

				</div>
				<br> <br>

				<c:if
					test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
					<div class="row">

						<div class="col-12 form-group">
							<label for="phone_no" class="form-label">Password : </label>

							<form:input path="password" placeholder="Password Here"
								class="form-control" required="true" autocomplete="off"
								type="password" />
							<div hidden="hidden" id="passRules">
								<ul>
									<li class="text-danger" id="lenErr">Password should have
										at least 8 characters</li>
									<li class="text-danger" id="ucaseErr">Password should have
										at least one uppercase letter</li>
									<li class="text-danger" id="lcaseErr">Password should have
										at least one lowercase letter</li>
									<li class="text-danger" id="numberErr">Password should
										have at least one number</li>
									<li class="text-danger" id="scharErr">Password should have
										at least one special character</li>
								</ul>
							</div>
						</div>
					</div>
				</c:if>



			</div>
		</div>
	</div>
	<br>
	<br>
	<c:choose>
		<c:when
			test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
			<c:choose>
				<c:when
					test="${user.user_creation_status == 'NR'}">
					<form:button type="button" class="btn btn-primary"
						id="pDetailsButton"> Next &gt;&gt;
									</form:button>
				</c:when>
				<c:otherwise>
				<div class="row justify-content-center d-flex">
					<form:button type="submit" class="btn btn-primary col-5"
						id="pDetailsSubmitButton"> Submit
									</form:button>&nbsp;&nbsp;&nbsp;&nbsp;
					<form:button type="button" class="btn btn-primary col-5"
						id="pDetailsButton"> Next &gt;&gt;
									</form:button></div>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<form:button type="submit" class="btn btn-primary" id="submitButton"> Create User
									</form:button>
		</c:otherwise>
	</c:choose>

	</div>
</body>
</html>