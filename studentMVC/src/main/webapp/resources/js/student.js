import { invalidateElement, validateElement } from "./invalidateElement.js";
import { validatePassword } from "./passwordValidate.js";
import { addState, addDistrict } from "./addState.js";

import { encryptPassword } from "./passwordEncrypt.js";




$(document).ready(function() {
	const NumPattern = /[A-Za-z\s!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const EmailPattern = /(?:((?:[\w-]+(?:\.[\w-]+)*)@(?:(?:[\w-]+\.)*\w[\w-]{0,66})\.(?:[a-z]{2,6}(?:\.[a-z]{2})?));*)/
	const FLNamePattern = /[0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?^$]/;

	var emailValid = false;
	var phoneNoValid = false;
	var phoneNoValidLen = false;
	var passwordValid = false;
	var fNameValid = false;
	var LNameValid = false;
	var dupEmail = false;
	var dupPhoneNo = false;
	var validPhoto = true;
	const checkValidity = function() {
		if (emailValid &&
			phoneNoValid &&
			fNameValid &&
			LNameValid &&
			phoneNoValidLen &&
			!dupEmail &&
			!dupPhoneNo &&
			validPhoto && passwordValid) {
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
			if (!validPhoto) {
				invalidateElement($("#file"))
				$("#invalidFile").removeAttr("hidden")
			}
			return false;
		}
	}

	$("#password").on("input", (e) => {
		passwordValid = validatePassword($("#password"), $("#ucaseErr"), $("#lcaseErr"), $("#numberErr"), $("#scharErr"))

	})
	$("#password").on("focusout", (e) => {
		$("#passRules").attr("hidden", true);
	})
	$("#password").on("focusin", (e) => {
		$("#passRules").removeAttr("hidden");
	})
	$("#permCountryCode").on("change", (e) => {
		addState($("#state_code"), $("#country_code"));
	})
	$("#state_code").on("change", (e) => {
		addDistrict($("#district_code"), $("#state_code"))

	})

	$("#phone_no,#phoneNo").on("input", (e) => {


	})
	//$("#phone_no").on("blur", (e) => {
	$("#phone_no,#phoneNo").on("change", (e) => {
		var PhoneNoValidity = NumPattern.test($(e.target).val());

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
			var id = $(e.target).attr("id")
			idCheck(e, "phone_no", $("#phone_no"), $("#phoneNo"))


			return;

		}
		else {
			phoneNoValid = true;
			console.log("in else block")
			$("#phoneCharErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled");

		}
		if ($(e.target).val().length < 10 || $(e.target).val().length > 10) {
			console.log("in if block")

			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValidLen = false;
			console.log("Invalid phone number length")
			idCheck(e, "phone_no", $("#phone_no"), $("#phoneNo"))
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
			"url": $(e.target).attr("id") == "phone_no" ? contextPath + "/getStudent" : contextPath + "/getProfessorByPhoneNo",

			"data": {
				"phone_no": $(e.target).val()
			},
			"success": function(response) {
				if (!$.isEmptyObject(response)) {
					$("#dupPhoneNo").removeAttr("hidden")
					dupPhoneNo = true;
					idCheck(e, "phone_no", $("#phone_no"), $("#phoneNo"))
					return;
				}
				else {
					console.log("not duplicate phone number")
					$("#dupPhoneNo").attr("hidden", "true")
					idCheckValidate(e, "phone_no", $("#phone_no"), $("#phoneNo"));
					dupPhoneNo = false;
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText + " " + textStatus + " " + errorThrown)
			},
			"method": "get"

		})



	})

	$("#email").on("change", function() {

		let emailValue = $("#email").val();
		let isEmailValid = EmailPattern.test(emailValue);
		if (emailValue.length == 0) {
			emailValid = false;
			$("#EmailCharErr").removeAttr("hidden");
			invalidateElement($("#email"));
			return;
		}
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
					return;
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.responseText + " " + textStatus + " " + errorThrown);
			}
		});

	});

	$("#phone_no").on("change", function() {

	})
	$("#first_name,#firstName").on("input focusout", (e) => {
		var isFNameValid = FLNamePattern.test($(e.target).val());
		if ($(e.target).val().length == 0) {
			console.log("in if block")
			$("#FnameErr").removeAttr("hidden");
			fNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
			idCheck(e, "first_name", $("#first_name"), $("#firstName"));
			return;
		}
		if (isFNameValid) {
			console.log("in if block")
			$("#FnameErr").removeAttr("hidden");
			fNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
			idCheck(e, "first_name", $("#first_name"), $("#firstName"));
		}
		else {
			fNameValid = true
			console.log("in else block")
			$("#FnameErr").attr("hidden", "true");
			idCheckValidate(e, "first_name", $("#first_name"), $("#firstName"));
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$("#last_name,#lastName").on("input focusout", (e) => {
		var isFNameValid = FLNamePattern.test($(e.target).val());
		if ($(e.target).val().length == 0) {
			console.log("in if block")
			LNameValid = false;
			console.log("why not focus")
			$("#LnameErr").removeAttr("hidden");

			idCheck(e, "last_name", $("#last_name"), $("#lastName"));
			return;
		}
		if (isFNameValid) {
			console.log("in if block")
			$("#LnameErr").removeAttr("hidden");
			LNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
			idCheck(e, "last_name", $("#last_name"), $("#lastName"));
		}
		else {
			LNameValid = true
			console.log("in else block")
			$("#LnameErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled")
			idCheckValidate(e, "last_name", $("#last_name"), $("#lastName"))

		}

	})
	$("buttonDiv").on("click", (e) => {
		console.log("clicke");
	})
	$("#submitButton").on("click", (e) => {
		if (!checkValidity()) {
			console.log("Preventing sumbit")
			e.preventDefault();
		}
		else {
			encryptPassword("password");
			return;
		}
	})
	$("#file").on("change", (e) => {
		console.log($("#file").val())
		var fileExt = $("#file").val().split(".")[1]
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
			var file = e.target.files[0]
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#image-view').attr('src', e.target.result);
			}
			reader.readAsDataURL(file);

		}
	})

})



function idCheckValidate(e, fieldCompare, el1, el2) {
	if ($(e.target).attr("id") == fieldCompare)
		validateElement(el1);

	else
		validateElement(el2);
}

function idCheck(e, fieldCompare, el1, el2) {
	if ($(e.target).attr("id") == fieldCompare)
		invalidateElement(el1);

	else
		invalidateElement(el2);
}
