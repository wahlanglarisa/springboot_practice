<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet">

<meta charset="UTF-8">
<link href="<c:url value="/resources/css/student.css" />" rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js">
	
</script>

<title>Insert title here</title>
</head>
<jsp:include page="studentHeader.jsp" />

<br>
<br>
<br>
<body>
	<main id="main-content">
		<div class="container mx-auto p-5 ">
			<c:if test="${param.deleteSuccess }">
				<div class="alert alert-success">Course Deleted Successfully</div>
			</c:if>

			<form:form modelAttribute="courseStudent" method="post"
				action="/studentMVC/saveStudentCourse">
				<c:if test="${param.duplicateCourse }">
					<div class="alert alert-danger">Duplicate course selected.
						Course already exists for student</div>
				</c:if>
				<c:if test="${param.AddCoursesuccess}">
					<div class="alert alert-success">Course Successfully added</div>
				</c:if>
				<div class="row">
					<div class="col-sm-9">
						<form:input path="stId" type="hidden" value="${student.id}" />

						<form:select path="course" class="form-select ui fluid dropdown "
							multiple="multiple" placeholder="Select a Course">
							<c:choose>
								<c:when test="${coursesToAdd.size()>0 }">
									<option value="" selected="selected">Select Courses</option>
								</c:when>
								<c:otherwise>
									<option value="" selected="selected">No Courses to Add</option>
								</c:otherwise>
							</c:choose>
							<form:options items="${coursesToAdd }" itemLabel="courseName"
								itemValue="id"></form:options>
						</form:select>

					</div>
					<div class="col-2">
						<form:button type="submit" class="btn btn-primary col-sm-8"> Add Courses</form:button>
					</div>
				</div>
				<br>

			</form:form>
			<table class="table">
				<tr>
					<th>Course Name</th>
					<th>Credits</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${courses }" var="course">
					<tr>
						<td>${course.courseName }</td>
						<td>${course.credit }</td>
						<td>
							<button class="btn btn-danger delete-btn" data-bs-toggle="modal"
								data-bs-target="#deleteModal" data-course-id="${course.id}">
								Delete</button>
						</td>

					</tr>
				</c:forEach>

			</table>
			<div class="modal fade" id="deleteModal" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1">

				<div class="modal-dialog">
					<div class="modal-content">

						<div class="modal-header">
							<h1 class="modal-title fs-5">Delete Confirmation</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
						</div>

						<div class="modal-body">Are you sure you want to delete this
							course?</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">No</button>

							<a id="confirmDeleteBtn" href="#" class="btn btn-danger">
								Delete </a>

						</div>

					</div>
				</div>
			</div>
			<div class="justify-content-center">
				<ul class="pagination justify-content-center">
					<c:choose>
						<c:when test="${(currentPage-1)==0 }">

							<li class="page-item"><span class="page-link disabled">Previous</span></li>
						</c:when>
						<c:otherwise>

							<li class="page-item"><a
								href="/studentMVC/studentPage/${student.email }/${currentPage-1 }"
								class="page-link">Previous</a></li>
						</c:otherwise>

					</c:choose>
					<c:forEach begin="1" end="${totalPages }" step="1" var="i">
						<c:choose>
							<c:when test="${i == currentPage }">
								<li class="page-item"><span class="page-link active">${i }</span>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item"><a
									href="/studentMVC/studentPage/${student.email }/${i }"
									class="page-link">${i }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${(currentPage)==totalPages }">

							<li class="page-item"><span class="page-link disabled">Next</span></li>
						</c:when>
						<c:otherwise>

							<li class="page-item"><a
								href="/studentMVC/studentPage/${student.email }/${currentPage+1 }"
								class="page-link">Next</a></li>
						</c:otherwise>

					</c:choose>

				</ul>
			</div>

		</div>
	</main>

</body>
<script>
	$('.ui.dropdown').dropdown();
	document.addEventListener("DOMContentLoaded", function () {
console.log("${totalItems-1}")
	    const deleteButtons = document.querySelectorAll(".delete-btn");
	    const confirmBtn = document.getElementById("confirmDeleteBtn");

	    deleteButtons.forEach(button => {
	        button.addEventListener("click", function () {

	            const courseId = this.getAttribute("data-course-id");
	            const studentId = "${student.id}";
	            const page="${currentPage}"

	            confirmBtn.href =
	                "/studentMVC/deleteCourseStudent/" +
	                studentId + "/" +
	                courseId+"/"+("${totalItems-1}"%"${pageSize}"==0?parseInt(page-1):page);
	        });
	    });

	});
</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
</html>