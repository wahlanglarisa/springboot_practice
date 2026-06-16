<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<!-- <nav class="navbar bg-body-tertiary">
	<div class="container-fluid">
		<a class="navbar-brand" href="/studentMVC/student/studentPage/${sessionScope.user.email }/1"> <strong>Student Course Management</strong>
		</a>

		<button class="navbar-toggler" type="button"
			data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar"
			aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1"
			id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">Menu
				</h5>
				<button type="button" class="btn-close btn-close-white"
					data-bs-dismiss="offcanvas" aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle text-light" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Edit Profile
					</a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item"
								href="/studentMVC/updatestudent/${sessionScope.user.st_id }">Update
									Profile</a></li>
							
							<li><hr class="dropdown-divider"></li>
							<li></li>

						</ul></li>
					<li class="nav-item"><a class="nav-link text-light"
						href="/studentMVC/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>

</nav> -->
<script>
	$(document).ready(function() {
		// Restore active state
		let activeLink = localStorage.getItem("activeLink");
		if (activeLink) {
			$("#sidebarMenu a").removeClass("active");
			$("#sidebarMenu a[href='" + activeLink + "']").addClass("active");
		}

		// On click, set active and save
		$("#sidebarMenu a").click(function() {
			$("#sidebarMenu a").removeClass("active");
			$(this).addClass("active");
			localStorage.setItem("activeLink", $(this).attr("href"));

		});
	});
</script>

<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse">

	<div class="position-sticky container-fluid">
		<div class="list-group list-group-flush mx-3 mt-4">
			<br>

			<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
				<li class="nav-item  text-light"><a
					href="/studentMVC/student/studentHomepage/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email }"
					class="nav-item text-light p-2"> <span> Home</span>
				</a></li>
				<hr class="text-light">
				<li class="nav-item  text-light"><a
					href="/studentMVC/student/studentPage/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email }/1"
					class="nav-item text-light p-2"> <span> View Courses</span>
				</a></li>
				<hr class="text-light">

				<li class="nav-item  text-light"><a
					href="/studentMVC/student/studentViewRoutine/${sessionScope.user.st_id }"
					class="nav-item text-light p-2"> <span> View Routine</span>
				</a></li>
				<hr class=" text-light">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle text-light" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> Account </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item"
							href="/studentMVC/student/updatestudent/${sessionScope.user.st_id }">Update
								Profile</a></li>
						<li><a class="dropdown-item"
							href="/studentMVC/changePassword/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email}">Change
								Password</a></li>

						<li>
							<form action="${pageContext.request.contextPath}/logout"
								method="post">
								<button type="submit" class="dropdown-item">Logout</button>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

							</form>
						</li>
					</ul></li>


			</ul>





		</div>
	</div>
</nav>
<!-- Sidebar -->

<!-- Navbar -->
<nav id="main-navbar"
	class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
	<!-- Container wrapper -->
	<div class="container-fluid">
		<!-- Toggle button -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#sidebarMenu" aria-controls="sidebarMenu"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>

		</button>


		<!-- Brand -->
		<a class="navbar-brand"
			href="/studentMVC/student/studentHomepage/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email }">
			<img th:src="@{/ChatGPT Image Sep 3, 2025, 01_04_50 PM.png}"
			height="25" alt="" loading="lazy" /> <span>Student Course
				Management </span>
		</a>
		<ul class="navbar-nav">
			<li class="nav-item dropdown "><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false">
					${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email}
					&nbsp;<img src="<c:url value="/resources/user_pic.png" />"
					width="30px" height="30px" />
			</a>
				<ul class="dropdown-menu dropdown-menu-end">
					<li><a href="/studentMVC/student/updatestudent/${user.st_id }"
						class="dropdown-item" aria-current="true"><span>Update
								Profile</span> </a></li>

					<li><a class="dropdown-item "
						href="/studentMVC/changePassword/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email}">Change
							Password</a></li>
					<li>
						<form action="${pageContext.request.contextPath}/logout"
							method="post">
							<button type="submit" class="dropdown-item">Logout</button>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

						</form>
					</li>

				</ul></li>
		</ul>
		<!-- Search form -->
	</div>

	<!-- Right links -->

	<!-- Container wrapper -->
</nav>

</html>