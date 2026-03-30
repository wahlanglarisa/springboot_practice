$(document).ready(async function() {
	await courseFetch(1);
	await addressFetch()
});

async function courseFetch(pageNum) {
	$.ajax({
		url: contextPath + "/getCourseByStudent/",
		data: {
			page: pageNum,
			"id": $("#st_id").val()
		},
		success: async (response) => {
			console.log(response);
			$("#card-body").empty()
			$("#card-body").append(`<div class="container-fluid">
						
						<table class="table table-striped" id="courseTable">
								<thead><tr><th>
									Course Name
								
								</th>
								<th>Course Credit</th>
								</tr></thead><tbody></tbody>
									</table>
											</div>`);
			await (response["content"].forEach((element) => {

				$("#courseTable tbody").append(`<tr><td>${element.courseName}</td><td>${element.credit}</td></tr>
			
			`);
			}))
			if (response["totalElements"] == response["numberOfElements"]) {
				console.log("no pagination needed");
			}
			else {
				console.log("pagination initiate");
				if (pageNum == 1) {
					$("#pagination").empty()
					var pageIncr = parseInt(pageNum) + 1
					console.log(pageIncr)
					$("#pagination").append(`<ul class="pagination justify-content-center" id="page-list">					
				<li class="page-item justify-content-start d-flex">
				<button class="page-link disabled rounded " id="prev-page" value="${pageNum - 1}" onclick="pageButtonClicked(this,this.value);">
				&laquo;</button></li>&nbsp;&nbsp;&nbsp;&nbsp;<li class="page-item  justify-content-end d-flex">
					<button class="page-link rounded" id="next-page" value="${pageIncr}" onclick="pageButtonClicked(this,this.value);">
					&raquo;</button></li></ul>
				`);
				}
				if (response.last) {
					$("#next-page").addClass("disabled")
				}
				if (!response.first) {
					$("#prev-page").removeClass("disabled")
				}

			}
		}
	});
}
async function addressFetch() {
	$.ajax({
		url: contextPath + "/getStudentAddress",
		data: {
			"id": $("#st_id").val()
		},
		success: async (response) => {
			$("#permAddrCard").append(`			<div class="row">
			<div class="col-sm-3">			<p class="mb-0">Address Line 1</p>
			</div>			<div class="col-sm-9">			<p class="text-muted mb-0">${response.addressPermLine1}</p>
			</div>
			</div>
			<hr>
			<div class="row">
			<div class="col-sm-3">
			<p class="mb-0">Address Line 2</p>
			</div>
		<div class="col-sm-9">
		<p class="text-muted mb-0">${response.addressPermLine2}</p>
		</div>
	</div>
	<hr>
			<div class="row">
			<div class="col-sm-3">
			<p class="mb-0">Address Line 3</p>
			</div>
		<div class="col-sm-9">
		<p class="text-muted mb-0">${response.addressPermLine3}</p>
		</div>
	</div>
	<hr>
	<div class="row">
				<div class="col-sm-3">
				<p class="mb-0">District</p>
				</div>
			<div class="col-sm-9">
			<p class="text-muted mb-0">${response.permDistrictName}</p>
			</div>
		</div>
		<hr>
			<div class="row">
						<div class="col-sm-3">
						<p class="mb-0">State</p>
						</div>
					<div class="col-sm-9">
					<p class="text-muted mb-0">${response.permStateName}</p>
					</div>
				</div>
				<hr>
					<div class="row">
								<div class="col-sm-3">
								<p class="mb-0">Country</p>
								</div>
							<div class="col-sm-9">
							<p class="text-muted mb-0">${response.permCountryName}</p>
							</div>
						</div>`)
			$("#preAddrCard").append(`			<div class="row">
								<div class="col-sm-3">
								<p class="mb-0">Address Line 1</p>
								</div>
								<div class="col-sm-9">
								<p class="text-muted mb-0">${response.addressPreLine1}</p>
								</div>
								</div>
								<hr>
								<div class="row">
								<div class="col-sm-3">
								<p class="mb-0">Address Line 2</p>
								</div>
							<div class="col-sm-9">
							<p class="text-muted mb-0">${response.addressPreLine2}</p>
							</div>
						</div>
						<hr>
								<div class="row">
								<div class="col-sm-3">
								<p class="mb-0">Address Line 3</p>
								</div>
							<div class="col-sm-9">
							<p class="text-muted mb-0">${response.addressPermLine3}</p>
							</div>
						</div>
						<hr>
						<div class="row">
									<div class="col-sm-3">
									<p class="mb-0">District</p>
									</div>
								<div class="col-sm-9">
								<p class="text-muted mb-0">${response.preDistrictName}</p>
								</div>
							</div>
							<hr>
								<div class="row">
											<div class="col-sm-3">
											<p class="mb-0">State</p>
											</div>
										<div class="col-sm-9">
										<p class="text-muted mb-0">${response.preStateName}</p>
										</div>
									</div>
									<hr>
										<div class="row">
													<div class="col-sm-3">
													<p class="mb-0">Country</p>
													</div>
												<div class="col-sm-9">
												<p class="text-muted mb-0">${response.preCountryName}</p>
												</div>
											</div>`)
		}

	});
}
async function pageButtonClicked(element, page) {
	id = element.id
	if (id == "next-page") {
		await courseFetch(parseInt(page))

		element.value = parseInt(page) + 1
		document.getElementById("prev-page").value = page - 1
	}
	if (id == "prev-page") {
		console.log("going to prev page")
		await courseFetch(parseInt(page))

		element.value = parseInt(page) - 1
		document.getElementById("next-page").value = page

	}

}
