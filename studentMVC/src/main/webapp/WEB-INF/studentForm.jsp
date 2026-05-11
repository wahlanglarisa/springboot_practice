<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html>
<html>

<head>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" />
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/student.css" />"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<c:choose>
	<c:when
		test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
		<nav class="navbar bg-body-tertiary">
			<div class="container-fluid">
				<a class="navbar-brand text-large" href="/studentMVC/"><strong>
						Student Course Management</strong> </a>
			</div>
		</nav>
	</c:when>
	<c:otherwise>
		<jsp:include page="studentHeader.jsp" />
	</c:otherwise>
</c:choose>
<br>
<br>
<br>
<br>

<body class="">
	<div class="modal fade" id="UpdateModal" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1">

		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h1 class="modal-title fs-5 text-danger"></h1>
				</div>

				<div class="modal-body"></div>

				<div class="modal-footer">


					<button id="updateUserLink" class="btn btn-primary">Go to
						Tab</button>

				</div>

			</div>
		</div>
	</div>
	<c:if
		test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
		<main id="main-content">
	</c:if>

	<form:form modelAttribute="student" method="post"
		action="/studentMVC/setSession" class="needs-validation"
		autocomplete="off" enctype="multipart/form-data">
		<c:choose>
			<c:when
				test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
				<div class="mx-auto tab-content">
			</c:when>
			<c:otherwise>
				<div class="mx-auto container">
			</c:otherwise>
		</c:choose>

		<c:if
			test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="home-tab" data-bs-toggle="tab"
						data-bs-target="#personalDetails" type="button" role="tab"
						aria-controls="personalDetails" aria-selected="true">Personal
						Details</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
						data-bs-target="#addressDetails" type="button" role="tab"
						aria-controls="addressDetails" aria-selected="false">Address
						Details</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="contact-tab" data-bs-toggle="tab"
						data-bs-target="#qualificationDetails" type="button" role="tab"
						aria-controls="qualificationDetails" aria-selected="false">Educational
						Qualification</button>
				</li>
			</ul>
		</c:if>


		<div class="alert alert-danger" id="EmptyError" hidden="true">Please
			fill up all fields</div>

		<c:if test="${param.success}">
			<div class="alert alert-success">
				User Created Successfully. <a href="/studentMVC/" class="link">Click
					Here to Login</a>
			</div>
		</c:if>
		<c:if test="${param.updateSuccess}">
			<div class="alert alert-success">Update Successful</div>
		</c:if>
		<c:if test="${param.emptyFields}">
			<div class="alert alert-danger">Please fill up all fields</div>
		</c:if>
		<c:choose>
			<c:when
				test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">
				<div class="text-center">
					<h4>Register Here</h4>
				</div>
			</c:when>
			<c:otherwise>
				<div class="text-center">
					<h1>Update User Details</h1>
				</div>
			</c:otherwise>
		</c:choose>
		<form:input type="hidden" path="st_id" class="col-5" />
		<form:input type="hidden" path="user_id" class="col-5" />
		<div class="tab-pane fade show active" id="personalDetails"
			role="tabpanel" aria-labelledby="home-tab">
			<jsp:include page="updateProfile.jsp"></jsp:include>

		</div>
		<div class="row tab-pane fade" id="addressDetails" role="tabpanel"
			aria-labelledby="profile-tab">

			<c:if
				test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
				<jsp:include page="updateAddress.jsp"></jsp:include>
			</c:if>
		</div>
		<div class="row tab-pane fade" id="qualificationDetails"
			role="tabpanel" aria-labelledby="contact-tab">

			<c:if
				test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
				<jsp:include page="updateQualification.jsp"></jsp:include>
			</c:if>
		</div>
		<div class="row p-2" id="buttonDiv"></div>

		</div>
	</form:form>

	<c:if
		test="${requestScope['jakarta.servlet.forward.request_uri'] != '/studentMVC/addstudent'}">
		</main>
	</c:if>
</body>
<jsp:include page="footer.jsp" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jsencrypt/bin/jsencrypt.min.js"></script>


<script>
	var contextPath = "${pageContext.request.contextPath}";
	const publicKey = "${publicKey}";
	var studentQual=${studentQualJson }
	console.log(typeof(studentQual))

	var qualArray = [];

	/*var requestURI = "${requestScope['jakarta.servlet.forward.request_uri']}";
	stateElement=document.getElementById("state_code")
	district_element=document.getElementById("district_code")
	if(requestURI=='/studentMVC/updatestudent/${student.st_id}'){
		console.log(document.getElementById("state_code")[0])
		state_codes=stateElement[0]
		state_codes.remove();
		district_code=district_element[0]
		district_code.remove();
		stateElement.removeAttribute("disabled")
		district_element.removeAttribute("disabled")
	}*/
	 studentQual.forEach(element=> {
		 console.log(element.qtype)
		var ob={"qualification":element.qualification,"stream":element.stream,"percentage":element.percentage,"qtype":element.qtype}
		qualArray.push(ob);
	})
	var requestURI = "${requestScope['jakarta.servlet.forward.request_uri']}"
		if(requestURI=='/studentMVC/updatestudent/${student.st_id}'){
		document.getElementById("file").onchange = function(event) {
		const file = event.target.files[0];

		const reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById("image-view").src = e.target.result;
		};

		reader.readAsDataURL(file);
		
		}
	}
	const creationStatus = "${user.user_creation_status}";
	if (creationStatus == "NR") {
			var tabs=document.querySelectorAll("#myTab .nav-item button");
			console.log(tabs)
			tabs.forEach((element)=>{
				element.setAttribute("disabled",true);
			})
	}
	let selectedRow = null;
	let selectedId = null;
	let counter = 0;

	document.addEventListener("DOMContentLoaded", function () {

	    const deleteButtons = document.querySelectorAll(".delete-btn");
	    const confirmBtn = document.getElementById("confirmDeleteBtn");

	    deleteButtons.forEach(button => {
	        button.addEventListener("click", function () {
	            selectedRow = this.closest('tr');
	            selectedId = this.getAttribute("data-studentqual-id");
	            var qual=selectedRow.cells[0].textContent;
			var stream=selectedRow.cells[1].textContent;
			 const modalBody = document.querySelector("#deleteStQualModal .modal-body");
				modalBody.innerHTML="Are you sure you want to delete Qualification <b><i>"
				+qual+" "+stream+"</i></b>?"
	        });
	    });

	    confirmBtn.addEventListener("click", function () {
			
			

	        addCell(selectedRow, counter, selectedId);
	        counter++;
	       
	        const modalElement = document.getElementById("deleteStQualModal");
	        let modal = bootstrap.Modal.getInstance(modalElement);
			modal.hide();
	    });

	});

	function addCell(row, index, studentID) {
	    const delTable = document.querySelector("#delQualTable tbody");
	    const newRow = delTable.insertRow(-1);
	    const cell = newRow.insertCell(0);

	    row.remove(); // no need for async/await

	    cell.innerHTML =
	        '<input type="hidden" name="ids[' + index + ']" value="' + studentID + '">';
	}
</script>
<c:choose>
	<c:when
		test="${requestScope['jakarta.servlet.forward.request_uri'] == '/studentMVC/addstudent'}">

		<script type="module" src="<c:url value="/resources/js/student.js" />"></script>
	</c:when>
	<c:otherwise>

		<script type="module"
			src="<c:url value="/resources/js/updateStudentData.js" />">
						</script>
	</c:otherwise>
</c:choose>

</html>