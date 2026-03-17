<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					href="/studentMVC/studentPage/${sessionScope.user.email }/1"
					class="nav-item  text-light p-2" aria-current="true">
					<span> Home</span>
				</a></li>
				<li><hr class="dropdown-divider"></li>


				<li class="nav-item dropdown p-2"><a
					class="nav-link dropdown-toggle text-light" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> Edit Profile </a>
					<ul class="dropdown-menu">
						<li><a
							href="/studentMVC/updatestudent/${sessionScope.user.userid }"
							class="dropdown-item" aria-current="true">&nbsp;&nbsp;<span>Update
									Profile</span>
						</a></li>

						<li><a class="dropdown-item "
							href="/studentMVC/changePassword/${sessionScope.user.email }">Change
								Password</a></li>
						<li><hr class="dropdown-divider"></li>
						<li></li>

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
		<a class="navbar-brand" th:href="@{/student/studentHomepage}"> <img
			th:src="@{/ChatGPT Image Sep 3, 2025, 01_04_50 PM.png}" height="25"
			alt="" loading="lazy" /> <span>Student Course Management </span>
		</a> <a class="navbar-brand d-flex" href="/studentMVC/logout"> Logout
		</a>
		<!-- Search form -->


		<!-- Right links -->

	</div>
	<!-- Container wrapper -->
</nav>

</html>