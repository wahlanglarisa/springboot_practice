<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>
<head>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js">
	
</script>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<jsp:include page="studentHeader.jsp" />

<br>
<br>
<br>
<body>
	<div class="mx-auto p-5">

		<form:form modelAttribute="courseStudent" method="post"
			action="/studentMVC/saveStudentCourse">
			<c:if test="${param.duplicateCourse }">
				<div class="alert alert-danger">Duplicate course selected.
					Course already exists for student</div>
			</c:if>
			<c:if test="${param.success}">
				<div class="alert alert-success">Course Successfully added</div>
			</c:if>
			<div class="row">
				<form:input path="stId" type="hidden" value="${stId }" />
				<div class="col-sm-5 ui container">

					<form:select path="course" class="form-select ui fluid dropdown"
						multiple="multiple" placeholder="Select a Course">
						<option value="" selected="selected">Select Courses</option>
						<form:options items="${courses}" itemLabel="courseName"
							itemValue="id" class="col-5"></form:options>
					</form:select>

				</div>

			</div>
			<br>
			<form:button type="submit" class="form-control btn btn-primary "> Submit</form:button>

		</form:form>
	</div>



</body>
<script>
	$('.ui.dropdown').dropdown();
</script>
</html>
