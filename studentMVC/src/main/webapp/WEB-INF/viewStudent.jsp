<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<jsp:include page="adminHeader.jsp"></jsp:include>
<body>
	<br>
	<br>
	<br>
	<main id="main-content">

		<section style="background-color: #eee;">
			<div class="container py-5 mx-auto">

				<input type="hidden" id="st_id" value="${student.id}" />
				<div class="row">
					<div class="col-lg-4">
						<div class="card mb-4">
							<div class="card-body text-center">
								<img src="/studentMVC/image/${student.id }" alt="avatar"
									class="rounded-circle img-fluid" style="width: 150px;">
								<h5 class="my-3">${student.first_name }&nbsp;${student.last_name }</h5>
								<p class="text-muted mb-1">Student</p>

							</div>
						</div>

					</div>


					<div class="col-lg-8">
						<div class="card mb-4">
							<div class="card-header justify-content-center d-flex">
								<h3>Student Biodata</h3>
							</div>
							<div class="card-body">


								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">Full Name</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${student.first_name }&nbsp;${student.last_name }</p>
									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">Email</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${student.email }</p>
									</div>
								</div>
								<hr>
								<div class="row">
									<div class="col-sm-3">
										<p class="mb-0">Mobile</p>
									</div>
									<div class="col-sm-9">
										<p class="text-muted mb-0">${student.phone_no }</p>
									</div>
								</div>
								<hr>
							</div>
						</div>
					</div>

					<div class="row" id="addressRow">
						<div class="col-md-6">
							<div class="card mb-4">
								<div class="card-header justify-content-center d-flex">
									<h3>Permanent Address Details</h3>
								</div>
								<div class="card-body" id="permAddrCard"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card mb-4">
								<div class="card-header justify-content-center d-flex">
									<h3> Present Address Details</h3>
								</div>
								<div class="card-body" id="preAddrCard"></div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center d-flex">
						<div class="col-xl-15">
							<div class="card mb-4 mb-md-0  justify-content-center d-flex">
								<div class="mb-4 card-header">
									<h3>Subjects taken</h3>
								</div>
								<div class="card-body " id="card-body"></div>
								<div id="pagination" class="pagination-container"></div>
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
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="<c:url value="/resources/js/studentDetails.js" />"></script>
</html>