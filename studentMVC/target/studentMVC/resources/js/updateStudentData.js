
$(document).ready(() => {
	var phoneNoValid = true;
	var fNameValid = true;
	var LNameValid = true;
	var phoneNoValidLen = true;
	var emailValid = true;
	const EmailPattern = /(?:((?:[\w-]+(?:\.[\w-]+)*)@(?:(?:[\w-]+\.)*\w[\w-]{0,66})\.(?:[a-z]{2,6}(?:\.[a-z]{2})?));*)/

	const NumPattern = /[A-Za-z\s!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const FLNamePattern = /[0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;

	const checkValidity = function() {
		if (emailValid &&
			phoneNoValid &&
			fNameValid &&
			LNameValid &&
			phoneNoValidLen) {
			return true;
		}
		else {

			return false;
		}
	}
	$("#phone_no").on("input", (e) => {
		var PhoneNoValidity = NumPattern.test($("#phone_no").val());

		console.log();
		if (PhoneNoValidity) {
			console.log("in if block")
			$("#phoneCharErr").removeAttr("hidden");
			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValid = false;
			console.log("why not focus")
			$("#phone_no").addClass("is-invalid")


		}
		else {
			phoneNoValid = true;
			console.log("in else block")
			$("#phoneCharErr").attr("hidden", "true");
			$("#phone_no").removeClass("is-invalid");
			$("#phone_no").addClass("is-valid")
			$("#submitButton").removeAttr("disabled");

		}
		if ($("#phone_no").val().length < 10 || $("#phone_no").val().length > 10) {
			console.log("in if block")
			$("#phoneLength").removeAttr("hidden");
			$("#phone_no").addClass("is-invalid")

			//$("#phoneLength").html("Phone number should have 10 digits");

			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValidLen = false;
			console.log("why not focus")

		}
		else {
			phoneNoValidLen = true;
			console.log("in else block")
			$("#phone_no").removeClass("is-invalid");
			$("#phone_no").addClass("is-valid")
			$("#phoneLength").attr("hidden", "true");
			$("#submitButton").removeAttr("disabled");

		}

	})
	$("#first_name").on("input", (e) => {
		var isFNameValid = FLNamePattern.test($("#first_name").val());

		if (isFNameValid) {
			console.log("in if block")
			$("#FnameErr").removeAttr("hidden");
			fNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			$("#first_name").addClass("is-invalid")
			console.log("why not focus")
		}
		else {
			fNameValid = true
			console.log("in else block")
			$("#first_name").removeClass("is-invalid")

			$("#first_name").addClass("is-valid")
			$("#FnameErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$("#last_name").on("input", (e) => {
		var isFNameValid = FLNamePattern.test($("#last_name").val());

		if (isFNameValid) {
			console.log("in if block")
			$("#LnameErr").removeAttr("hidden");
			LNameValid = false
			$("#last_name").addClass("is-invalid")

			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
		}
		else {
			LNameValid = true
			console.log("in else block")
			$("#LnameErr").attr("hidden", "true");
			$("#last_name").removeClass("is-invalid");
			$("#last_name").addClass("is-valid")
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$("#email").on("input", (e) => {
		var isEmailValid = EmailPattern.test($("#email").val());

		if (!(isEmailValid)) {
			console.log("in if block")
			$("#EmailCharErr").removeAttr("hidden");
			emailValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			$("#email").addClass("is-invalid")

			console.log("why not focus")
		}
		else {
			emailValid = true
			console.log("in else block")
			$("#EmailCharErr").attr("hidden", "true");
			$("#email").removeClass("is-invalid");
			$("#email").addClass("is-valid")
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$(document).on("submit", (e) => {
		if (!checkValidity()) {
			console.log("Preventing sumbit")
			e.preventDefault();
		}
		else {
			return;
		}
	})
	$("#country_code").on("change", (e) => {
		$("#state_code").empty();
		$("#state_code").removeAttr('disabled');

		$("#state_code").append(`<option value="0">
				Select a State</option>`);
		$.ajax({
			"url": contextPath + "/getState_codes",
			dataType: "json",
			"data": {
				"countryCode": $("#country_code").val()
			},
			"success": function(response) {
				response.forEach((element) => {
					$("#state_code").append(`<option value="${element.stateCode}">${element.stateName}</option>`);
				})
				console.log(response)
			},
			"method": "get"

		})
	})
	$("#state_code").on("change", (e) => {

		$("#district_code").empty();
		$("#district_code").removeAttr('disabled');

		$("#district_code").append(`<option value="0">Select a District</option>`);

		$.ajax({
			"url": contextPath + "/getDistrict_codes",
			dataType: "json",
			"data": {
				"stateCode": $("#state_code").val()
			},
			"success": function(response) {
				response.forEach((element) => {
					$("#district_code").append(`<option value="${element.districtCode}">${element.districtName}</option>`);
				})
				console.log(response)
			},
			"method": "get"

		})
	})
})
