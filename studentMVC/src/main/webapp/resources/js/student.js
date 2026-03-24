/**
 * 
 */
function addState(stateElement,countryElement) {
    stateElement.empty();
    stateElement.removeAttr('disabled');

    stateElement.append(`<option value="0">
			Select a State</option>`);
    $.ajax({
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

function invalidateElement(elementMarkInvalid) {
	elementMarkInvalid.removeClass("is-valid");
	elementMarkInvalid.addClass("is-invalid");
}

function validateElement(elementMarkValid) {
	elementMarkValid.removeClass("is-invalid");

	elementMarkValid.addClass("is-valid");
}

$(document).ready(function() {
	const NumPattern = /[A-Za-z\s!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const EmailPattern = /(?:((?:[\w-]+(?:\.[\w-]+)*)@(?:(?:[\w-]+\.)*\w[\w-]{0,66})\.(?:[a-z]{2,6}(?:\.[a-z]{2})?));*)/
	const FLNamePattern = /[0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/
	const upperCasePass = /^(?=.*[A-Z])/
	const lowerCasePass = /^(?=.*[a-z])/
	const numberPass = /^(?=.*[0-9])/
	const specialCharPass = /^(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]/
	var emailValid = false;
	var phoneNoValid = false;
	var phoneNoValidLen = false;
	var PassHaveUcase = false;
	var passHaveLcase = false;
	var Passhavenumber = false;
	var passHaveSpchar = false;
	var PassValidLen = false;
	var fNameValid = false;
	var LNameValid = false;
	var dupEmail = false;
	var dupPhoneNo = false;
	var validPhoto = false;
	var EmailOTPvalid=false;
	const checkValidity = function() {
		if (emailValid &&
			phoneNoValid &&
			fNameValid &&
			LNameValid &&
			phoneNoValidLen &&
			!dupEmail &&
			!dupPhoneNo &&
			validPhoto && PassHaveUcase &&
			passHaveLcase && Passhavenumber &&
			passHaveSpchar && PassValidLen &&

			$("#email").val().length != 0 &&
			$("#first_name").val().length != 0 &&
			$("#last_name").val().length != 0 &&
			$("#phone_no").val().length != 0 &&
			$("#password").val().length != 0) {
			return true;
		}
		else {
			if ($("#country_code").val() == 0) {
				$("#countryEmpty").removeAttr("hidden")
			}
			if ($("#state_code").val() == 0) {
				$("#stateEmpty").removeAttr("hidden")
			}
			if ($("#district_code").val() == 0) {
				$("#districtEmpty").removeAttr("hidden")
			}
			return false;
		}
	}
	$("#OTP-email").on("change", (e) => {
		$.ajax({
			url: contextPath + "/verifyOTP",
			data: {
				otp: $("#OTP-email").val()
			},
			success: function(response) {
				if (response) {
					validateElement($("#OTP-email"))
					$("#otpInvalid").attr("hidden",true)
					EmailOTPvalid=true;

				}
				else{
					invalidateElement($("#OTP-email"))
					$("#otpInvalid").removeAttr("hidden")
					EmailOTPvalid=false
				}
			},
			method: "get"

		})
	})
	$("#password").on("input", (e) => {
		PassHaveUcase = newFunction(upperCasePass, $("#password"), $("#ucaseErr"));
		passHaveLcase = newFunction(lowerCasePass, $("#password"), $("#lcaseErr"));
		Passhavenumber = newFunction(numberPass, $("#password"), $("#numberErr"));
		passHaveSpchar = newFunction(specialCharPass, $("#password"), $("#scharErr"));
		if ($("#password").val().length >= 8) {
			elementErr = $("#lenErr")
			elementErr.removeClass("text-danger");
			elementErr.addClass("text-success");
			elementErr.addClass("list");
			PassValidLen = true;
		}
		else {
			elementErr = $("#lenErr")

			elementErr.addClass("text-danger");
			elementErr.removeClass("list");
			PassValidLen = false;
		}
		function newFunction(regEx, element, elementErr) {
			if (regEx.test(element.val())) {

				elementErr.removeClass("text-danger");
				elementErr.addClass("text-success");
				elementErr.addClass("list");
				return true;

			}
			else {
				elementErr.addClass("text-danger");
				elementErr.removeClass("list");
				return false;
			}
		}
	})
	$("#permCountryCode").on("change", (e) => {
		addState($("#state_code"),$("#country_code"));
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

	$("#phone_no").on("input", (e) => {


	})
	//$("#phone_no").on("blur", (e) => {
	$("#phone_no").on("input", (e) => {
		var PhoneNoValidity = NumPattern.test($("#phone_no").val());

		console.log();
		if (PhoneNoValidity) {
			console.log("in if block")
			console.log("phone number has characters")
			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValid = false;
			console.log("why not focus")
			$("#phoneCharErr").removeAttr("hidden");
			invalidateElement($("#phone_no"))
			return;

		}
		else {
			phoneNoValid = true;
			console.log("in else block")
			$("#phoneCharErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled");

		}
		if ($("#phone_no").val().length < 10 || $("#phone_no").val().length > 10) {
			console.log("in if block")

			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValidLen = false;
			console.log("Invalid phone number length")
			invalidateElement($("#phone_no"))
			$("#phoneLength").removeAttr("hidden");
			return;

		}
		else {
			phoneNoValidLen = true;
			console.log("Valid Phone number length")
			$("#phoneLength").attr("hidden", "true");
			$("#submitButton").removeAttr("disabled");

		}
		$.ajax({
			"url": contextPath + "/getStudent",

			"data": {
				"phone_no": $("#phone_no").val()
			},
			"success": function(response) {
				if (!$.isEmptyObject(response)) {
					$("#dupPhoneNo").removeAttr("hidden")
					dupPhoneNo = true;
					invalidateElement($("#phone_no"))
					return;
				}
				else {
					console.log("not duplicate phone number")
					$("#dupPhoneNo").attr("hidden", "true")
					validateElement($("#phone_no"));
					dupPhoneNo = false;
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText + " " + textStatus + " " + errorThrown)
			},
			"method": "get"

		})


	})

	$("#email").on("input", function() {

		let emailValue = $("#email").val();
		let isEmailValid = EmailPattern.test(emailValue);

		if (!isEmailValid) {
			emailValid = false;
			$("#EmailCharErr").removeAttr("hidden");
			invalidateElement($("#email"));
			return; // stop here — no need to check duplicate
		} else {
			emailValid = true;
			$("#EmailCharErr").attr("hidden", "hidden");
		}

		// Only check duplicate if format is valid
		$.ajax({
			url: contextPath + "/getUser",
			method: "get",
			data: { email: emailValue },
			success: function(response) {

				if (!$.isEmptyObject(response)) {
					dupEmail = true;
					$("#dupEmail").removeAttr("hidden");
					invalidateElement($("#email"));
				} else {
					dupEmail = false;
					$("#dupEmail").attr("hidden", "hidden");
					validateElement($("#email"));
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText + " " + textStatus + " " + errorThrown);
			}
		});
	});
	$("#email").one("change", function() {
		let emailValue = $("#email").val();
		$.ajax({
			url: contextPath + "/sendMail",
			method: "get",
			data: { email: emailValue },
			success: function(response) {

				if (response) {
					$("#OTP-email").removeAttr("hidden")

					$("#OTP-email").attr("placeholder", `Enter OTP sent to ${emailValue}`)
				} else {

				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText + " " + textStatus + " " + errorThrown);
			}
		});
	})
	$("#first_name").on("input focusout", (e) => {
		var isFNameValid = FLNamePattern.test($("#first_name").val());

		if (isFNameValid) {
			console.log("in if block")
			$("#FnameErr").removeAttr("hidden");
			fNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
			invalidateElement($("#first_name"))
		}
		else {
			fNameValid = true
			console.log("in else block")
			$("#FnameErr").attr("hidden", "true");
			validateElement($("#first_name"));
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$("#last_name").on("input focusout", (e) => {
		var isFNameValid = FLNamePattern.test($("#last_name").val());

		if (isFNameValid) {
			console.log("in if block")
			$("#LnameErr").removeAttr("hidden");
			LNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
			invalidateElement($("#last_name"))
		}
		else {
			LNameValid = true
			console.log("in else block")
			$("#LnameErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled");
			validateElement($("#last_name"));

		}

	})
	$("buttonDiv").on("click", (e) => {
		console.log("clicke");
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

		}
	})

})


