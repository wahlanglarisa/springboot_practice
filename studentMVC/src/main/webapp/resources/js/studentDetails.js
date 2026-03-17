$(document).ready(async function() {
	await courseFetch(1);
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
			$("#card-body").append(`			<div class="card-title justify-content-center d-flex">
													<h3>Courses Taken</h3>
												</div><div class="container-fluid">
						
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
