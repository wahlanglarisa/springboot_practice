<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet" crossorigin="anonymous">
<link href="<c:url value="/resources/css/student.css" />"
	rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:if
	test="${requestScope['jakarta.servlet.forward.request_uri'] == ('/studentMVC/listStudent/'+=currentPage) }"><jsp:include
		page="adminHeader.jsp"></jsp:include></c:if>
<body>
	<br></br>
	<main id="main-content">
		<div class="mx-auto p-5">
			<c:if test="${param.userDeleteSuccess}">
				<div class="alert alert-success">User Deleted
					Successfully</div>
			</c:if>

			<table class="table">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Phone Number</th>
					<th>Actions</th>

				</tr>
				<c:forEach items="${students }" var="student">
					<tr>
						<td>${student.first_name }</td>
						<td>${student.last_name }</td>
						<td>${student.email }</td>
						<td>${student.phone_no }</td>
						<td>
							<button class="btn btn-danger delete-btn" data-bs-toggle="modal"
								data-bs-target="#deleteModal" data-student-id="${student.id}">
								Delete</button> <a class="btn btn-primary"
							href="/studentMVC/viewStudent/${student.id}">View Details</a>
						</td>
						<td></td>

					</tr>
				</c:forEach>
			</table>
		</div>
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
							href="/studentMVC/listStudent/${currentPage-1 }"
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
								href="/studentMVC/listStudent/${i }" class="page-link">${i }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${(currentPage)==totalPages }">

						<li class="page-item"><span class="page-link disabled">Next</span></li>
					</c:when>
					<c:otherwise>

						<li class="page-item"><a
							href="/studentMVC/listStudent//${currentPage+1 }"
							class="page-link">Next</a></li>
					</c:otherwise>

				</c:choose>

			</ul>
		</div>
	</main>
</body>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function () {
console.log("${totalItems-1}")
	    const deleteButtons = document.querySelectorAll(".delete-btn");
	    const confirmBtn = document.getElementById("confirmDeleteBtn");

	    deleteButtons.forEach(button => {
	        button.addEventListener("click", function () {

	            const studentID = this.getAttribute("data-student-id");
	          

	            confirmBtn.href =
	                "/studentMVC/deleteStudent/" +
	                studentID });
	    });

	});</script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
</html>