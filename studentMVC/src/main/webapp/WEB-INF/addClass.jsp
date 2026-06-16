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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js">
	
</script>
</head>
<jsp:include page="adminHeader.jsp" />

<body>
	<br>
	<br>
	<br>
	<br>
	<main id="main-content">

		<div class="row">
			<!-- Profile picture card-->
			<form:form method="post"
				action="${pageContext.request.contextPath }/saveClass"
				modelAttribute="cl">
				<div class="card mb-4 mb-xl-0 shadow">
					<div class="card-header text-center">
						<h2>Add Class</h2>
					</div>
					<div class="card-body container-fluid">
						<!-- Profile picture image-->
						<div class="col-12 form-group">
							<label for="dayUuid" class="col-6 form-label">Select a
								Course : </label>
							<form:select path="courseIDLong"
								class="form-select col-6 ui fluid dropdown "
								style=" max-height: 300px;   overflow-y: auto; ">
								<option value="0">Select a Course</option>
								<form:options items="${courses }" itemValue="id"
									itemLabel="courseName" />
							</form:select>
							<span class="text-danger invalid-feedback" hidden="true"
								id="addrperm1err">This is required</span>
						</div>
						<div class="col-12 form-group">
							<label for="dayUuid" class="col-6 form-label">Select a
								Professor : </label>
							<form:select path="courseIDLong"
								class="form-select col-6 ui fluid dropdown "
								style=" max-height: 300px;   overflow-y: auto; ">
								<option value="0">Select a Professor</option>
								<form:options items="${professors }" itemValue="id"
									itemLabel="fullName" />
							</form:select>
							<span class="text-danger invalid-feedback" hidden="true"
								id="addrperm1err">This is required</span>
						</div>
						<div class="col-12 form-group">
							<label for="dayUuid" class="col-6 form-label ">Select a
								Day : </label>
							<form:select path="dayUuid"
								class="form-select col-6 ui fluid dropdown ">
								<option value="0">Select a Day</option>
								<form:options items="${days}" itemValue="iUuid"
									itemLabel="nameString" />
							</form:select>
							<span class="text-danger invalid-feedback" hidden="true"
								id="addrperm1err">This is required</span>
						</div>
						<div class="col-12 form-group">

							<label for="timeUuid" class="col-6 form-label">Select a
								Time Slot: </label>
							<form:select path="timeUuid"
								class="form-select col-6 ui fluid dropdown " disabled="true">
								<option>Select a Day First</option>
							</form:select>
							<span class="text-danger invalid-feedback" hidden="true"
								id="addrperm1err">This is required</span>
						</div>
					</div>
				</div>
				<br>
				<br>
				<br>
				<div class="row justify-content-center d-flex">
					<form:button type="submit" class="btn btn-primary "
						id="AddressSubmitButton"> Submit
									</form:button>
					&nbsp;&nbsp;


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
<script type="module" src="<c:url value="/resources/js/addClass.js" />">
						</script>
<script type="text/javascript">
	$('.ui.dropdown').dropdown();
</script>
</html>

