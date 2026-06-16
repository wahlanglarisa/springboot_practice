<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


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
	<div class="position-sticky">
		<div class="list-group list-group-flush mx-3 mt-4">
			<br>

			<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
				<li class="nav-item  text-light"><a
					href="/studentMVC/admin/adminHomepage/"
					class="nav-item  text-light p-2"> <span> <img
							src="<c:url value="/resources/home-page-white-icon.png"/>"
							class="icon" />&nbsp; Home
					</span>
				</a></li>
				<li><hr class="text-light"></li>
				<li class="nav-item  text-light dropdown"><a href="#"
					class="nav-item  text-light dropdown-toggle p-2" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> <span>
							<img src="<c:url value="/resources/add.png"/>" class="icon" />&nbsp;
							Add
					</span>
				</a>
					<ul class="dropdown-menu">
						<li class="nav-item"><a href="/studentMVC/addprofessor"
							class="dropdown-item"> Add Professor</a></li>
							<li class="nav-item"><a href="/studentMVC/admin/addClass"
							class="dropdown-item"> Add Class</a></li>
					</ul></li>
				<li><hr class="text-light"></li>
				<li class="nav-item  text-light"><a
					href="/studentMVC/admin/listStudent/1"
					class="nav-item  text-light p-2"> <span><img
							src="<c:url value="/resources/search-magnifying-glass-white-icon.png"/>" class="icon" />&nbsp; View
							Students</span>
				</a></li>

				


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
		<a class="navbar-brand" href="/studentMVC/admin/listStudent/1"> <img
			th:src="@{/ChatGPT Image Sep 3, 2025, 01_04_50 PM.png}" height="25"
			alt="" loading="lazy" /> <span>Student Course Management </span>
		</a>
		<ul class="navbar-nav">
			<li class="nav-item dropdown "><a
				class="nav-link dropdown-toggle" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> <img
					src="<c:url value="/resources/user_pic.png" />" width="30px"
					height="30px" /></a>
				<ul class="dropdown-menu dropdown-menu-end">


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
		<!-- Search 
		<!-- Search form -->


		<!-- Right links -->

	</div>
	<!-- Container wrapper -->
</nav>

</html>