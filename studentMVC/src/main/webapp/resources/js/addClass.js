$(document).ready(async () => {
	$("#dayUuid").on("change", async(e) => {
		$("#timeUuid").empty();
		$("#timeUuid").removeAttr("disabled")
		$("#timeUuid").parent().closest("div").removeClass("disabled")
		$("#timeUuid").append(`<option value="0">
			Select a Time Slot</option>`);
		await $.ajax({
			"url": contextPath + "/getAvailableClassTime",
			dataType: "json",
			"data": {
				"day": $("#dayUuid option:selected").text()
			},
			"success": function(response) {
				response.forEach((element) => {
					$("#timeUuid").append(`<option value="${element.id}">${element.startTime}-${element.endTime}</option>`);
				});
				console.log(response);
			},
			"method": "get"
		});
	})

})