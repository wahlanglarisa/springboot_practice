<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<jsp:include page="studentHeader.jsp" />

<body>
	<br>
	<br>
	<br>

	<main id="main-content">

		<div class="container">
			<div class="card">
				<div class="card-header justify-content-center d-flex">
					<h1>
						Class Routine for
						<c:set var="now" value="<%=new java.util.Date()%>" />
						<fmt:formatDate value="${now}" pattern="EEEE, dd-MMM-yyyy" />
					</h1>
				</div>
				<div class="card-body">
					<table class="table table-striped table-hover
					">

						<thead>
							<tr>
								<th>Course</th>
								<th>Day</th>
								<th>Professor</th>
								<th>Time</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${classes }" var="cl">
								<tr>
									<td>${cl.courseName }</td>
									<td>${cl.day }</td>
									<td>${cl.profName }</td>
									<td>${cl.startTime12hr}-${cl.endTime12hr }</td>
								</tr>

							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
</body>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
</html>