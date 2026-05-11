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
<link href="<c:url value="/resources/css/student.css" />"
	rel="stylesheet">

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
	<div class="modal fade" id="UpdateModal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1">

		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h1 class="modal-title fs-5">Update Profile Required</h1>
				</div>

				<div class="modal-body">Please Update User profile before
					proceeding to Home Page</div>

				<div class="modal-footer">


					<a id="updateUserLink"
						href="/studentMVC/student/updatestudent/${user.st_id}"
						class="btn btn-primary"> Update Profile </a>

				</div>

			</div>
		</div>
	</div>
	<main id="main-content">
		<section style="background-color: #eee;">
			<div class="container py-5 mx-auto">
				<div class="row">
					<div class="col-lg-4">
						<div class="card mb-4 shadow">
							<div class="card-body text-center">
								<h1>Number of courses applied</h1>
								<h2>${noOfCourses }</h2>
							</div>
						</div>

					</div>
					<div class="col-lg-4">
						<div class="card mb-4 shadow">
							<div class="card-body text-center">
								<h1>Number of classes today</h1>
								<h2>${classes.size() }</h2>
							</div>
						</div>
					</div>

				</div>
			</div>
		</section>

	</main>
</body>

<jsp:include page="footer.jsp" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script>
					document.addEventListener("DOMContentLoaded", function () {
						const creationStatus = "${user.user_creation_status}";
						if (creationStatus === "NR") {
							const modalElement = document.getElementById("UpdateModal");
							const modal = new bootstrap.Modal(modalElement);
							modal.show();
						}
					});
					document.addEventListener("DOMContentLoaded", function () {
						console.log("${totalItems-1}")
						const deleteButtons = document.querySelectorAll(".delete-btn");
						const confirmBtn = document.getElementById("confirmDeleteBtn");

						deleteButtons.forEach(button => {
							button.addEventListener("click", function () {

								const courseId = this.getAttribute("data-course-id");
								const studentId = "${student.id}";
								const page = "${currentPage}"

								confirmBtn.href =
									"/studentMVC/deleteCourseStudent/" +
									studentId + "/" +
									courseId + "/" + ("${totalItems-1}" % "${pageSize}" == 0 ? parseInt(page - 1) : page);
							});
						});

					});</script>

</html>