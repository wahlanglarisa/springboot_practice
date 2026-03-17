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

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:if
	test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/listStudent'}"><jsp:include
		page="adminHeader.jsp"></jsp:include></c:if>
<body>
	<br></br>
	<main id="main-content">
		<div class="mx-auto p-5">
			<a class="btn btn-primary" href="/studentMVC/addstudent">Add
				Student</a>
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
						<td><a class="btn btn-danger"
							href="/studentMVC/deleteStudent/${student.id}">Delete</a><a
							class="btn btn-primary"
							href="/studentMVC/updatestudent/${student.id}">Update</a></td>

					</tr>
				</c:forEach>
			</table>
		</div>
	</main>
</body>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
</html>