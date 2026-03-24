$(document).ready(function() {
	var oldPassValid = false;
	var confirmPassValid = false;
	const upperCasePass = /^(?=.*[A-Z])/
	const lowerCasePass = /^(?=.*[a-z])/
	const numberPass = /^(?=.*[0-9])/
	const specialCharPass = /^(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]/
	var PassHaveUcase = false;
	var passHaveLcase = false;
	var Passhavenumber = false;
	var passHaveSpchar = false;
	var PassValidLen = false;
	var checkValid = function() {
		if (oldPassValid
			&& confirmPassValid
			&& PassHaveUcase
			&& passHaveLcase
			&& Passhavenumber
			&& passHaveSpchar
			&& PassValidLen) {
			return true;
		}
		else {
			return false;
		}
	}
	$("#oldPassword").on("focusout", (e) => {
		let emailValue = $("#email").val();

		$.ajax({
			url: contextPath + "/validatePassword",
			method: "get",
			data: {
				email: emailValue,
				oldPassword: $("#oldPassword").val(),
			},
			success: function(response) {

				if (!response) {
					oldPassValid = false
					$("#password_err").removeAttr("hidden");
					invalidateElement($("#oldPassword"));
				} else {
					oldPassValid = true
					$("#password_err").attr("hidden", "hidden");
					validateElement($("#oldPassword"));
				}
			},
		})
	})
	$("#confirmPass").on("focusout", (e) => {

		if ($("#confirmPass").val() == $("#newPassword").val()) {
			confirmPassValid = true;
			$("confirmNewPassErr").attr("hidden", "hidden");

			validateElement($("#confirmPass"));
		}
		else {
			confirmPassValid = false;
			$("#confirmNewPassErr").removeAttr("hidden");
			invalidateElement($("#confirmPass"));

		}

	})
	$("#submit").on("click", (e) => {
		if (checkValid()) {
			return;
		}
		else {
			e.preventDefault();
		}
	})
	$("#newPassword").on("input", (e) => {
		PassHaveUcase = newFunction(upperCasePass, $("#newPassword"), $("#ucaseErr"));
		passHaveLcase = newFunction(lowerCasePass, $("#newPassword"), $("#lcaseErr"));
		Passhavenumber = newFunction(numberPass, $("#newPassword"), $("#numberErr"));
		passHaveSpchar = newFunction(specialCharPass, $("#newPassword"), $("#scharErr"));
		if ($("#newPassword").val().length >= 8) {
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
})
function invalidateElement(elementMarkInvalid) {
	elementMarkInvalid.removeClass("is-valid");
	elementMarkInvalid.addClass("is-invalid");
}

function validateElement(elementMarkValid) {
	elementMarkValid.removeClass("is-invalid");

	elementMarkValid.addClass("is-valid");
}