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
<jsp:include page="adminHeader.jsp" />

<br>
<br>
<br>
<body>
	<main id="main-content">
		<section style="background-color: #eee;">
			<div class="container py-5 mx-auto">
				<div class="row">
					<div class="col-lg-4">
						<div class="card mb-4 shadow">
							<div class="card-body text-center">
								<h1>Number of students enrolled</h1>
								<h2>${noOfStudents }</h2>
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
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
</html>