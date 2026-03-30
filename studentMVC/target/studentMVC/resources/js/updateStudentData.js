async function addState(stateElement, countryElement) {
	stateElement.empty();
	stateElement.removeAttr('disabled');

	stateElement.append(`<option value="0">
			Select a State</option>`);
	await $.ajax({
		"url": contextPath + "/getState_codes",
		dataType: "json",
		"data": {
			"countryCode": countryElement.val()
		},
		"success": function(response) {
			response.forEach((element) => {
				stateElement.append(`<option value="${element.stateCode}">${element.stateName}</option>`);
			});
			console.log(response);
		},
		"method": "get"
	});
}
async function addDistrict(districtElement, stateElement) {
	districtElement.empty();
	districtElement.removeAttr('disabled');

	districtElement.append(`<option value="0">Select a District</option>`);

	await $.ajax({
		"url": contextPath + "/getDistrict_codes",
		dataType: "json",
		"data": {
			"stateCode": stateElement.val()
		},
		"success": function(response) {
			response.forEach((element) => {
				districtElement.append(`<option value="${element.districtCode}">${element.districtName}</option>`);
			});
			console.log(response);
		},
		"method": "get"
	});
}
$(document).ready(() => {
	var oldPreAddr1 = ""
	var oldPreAddr2 = ""
	var oldPreAddr3 = ""

	var phoneNoValid = true;
	var fNameValid = true;
	var LNameValid = true;
	var phoneNoValidLen = true;
	var emailValid = true;
	const EmailPattern = /(?:((?:[\w-]+(?:\.[\w-]+)*)@(?:(?:[\w-]+\.)*\w[\w-]{0,66})\.(?:[a-z]{2,6}(?:\.[a-z]{2})?));*)/
	var validPhoto = true;
	const NumPattern = /[A-Za-z\s!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const FLNamePattern = /[0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;

	const checkValidity = function() {
		if (emailValid &&
			phoneNoValid &&
			fNameValid &&
			LNameValid &&
			phoneNoValidLen && validPhoto) {
			return true;
		}
		else {

			return false;
		}
	}
	$("#presentAddrCheck").change(async () => {
		console.log($("#presentAddrCheck").is(":checked"))
		if ($("#presentAddrCheck").is(":checked")) {
			oldPreAddr1 = $("#preaddressline1").val()
			oldPreAddr2 = $("#preaddressline2").val()
			oldPreAddr3 = $("#preaddressline3").val()

			console.log($("#presentAddrCheck").is(":checked"))

			$("#preaddressline1").val($("#permaddressline1").val())
			$("#preaddressline2").val($("#permaddressline2").val())
			$("#preaddressline3").val($("#permaddressline3").val())
			$("#preCountryCode").val($("#permCountryCode").val())
			await addState($("#preStateCode"), $("#preCountryCode"))
			$("#preStateCode").val($("#permStateCode").val())
			await addDistrict($("#preDistrictCode"), $("#permStateCode"))

			$("#preDistrictCode").val($("#permDistrictCode").val())

		}
		else {
			$("#preaddressline1").val(oldPreAddr1)
		}
	})
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
	$("#permCountryCode").on("change", (e) => {
		addState($("#permStateCode"), $("#permCountryCode"))
	})
	$("#permStateCode").on("change", (e) => {

		addDistrict($("#permDistrictCode"), $("#permStateCode"));
	})
	$("#preCountryCode").on("change", (e) => {
		addState($("#preStateCode"), $("#preCountryCode"))
	})
	$("#preStateCode").on("change", (e) => {

		addDistrict($("#preDistrictCode"), $("#preStateCode"));
	})
	$("#file").on("change", (e) => {
		fileExt = $("#file").val().split(".")[1]
		var Extensions = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];

		if ($.inArray($("#file").val().split(".")[1], Extensions) == -1) {
			invalidateElement($("#file"))
			$("#invalidFile").removeAttr("hidden")
			validPhoto = false
		}
		else {
			validateElement($("#file"))
			$("#invalidFile").attr("hidden", true)
			validPhoto = true;
			var file=e.target.files[0]
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#image-view').attr('src', e.target.result);
				console.log(e.target.result)
			}
			console.log(file)
			reader.readAsDataURL(file);

		}
	})
	function invalidateElement(elementMarkInvalid) {
		elementMarkInvalid.removeClass("is-valid");
		elementMarkInvalid.addClass("is-invalid");
	}

	function validateElement(elementMarkValid) {
		elementMarkValid.removeClass("is-invalid");

		elementMarkValid.addClass("is-valid");
	}
})


