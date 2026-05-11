import { invalidateElement, validateElement } from "./invalidateElement.js";
import { encryptPassword } from "./passwordEncrypt.js";
import { validatePassword } from "./passwordValidate.js";
$(document).ready(function() {
	var oldPassValid = false;
	var confirmPassValid = false;

	var newPassValid = false;
	var checkValid = function() {
		if (oldPassValid
			&& confirmPassValid
			&& newPassValid) {
			return true;
		}
		else {
			return false;
		}
	}
	$("#oldPassword").on("change", (e) => {
		if ($("#oldPassword").val().length != 0) {
			encryptPassword("oldPassword")
			let emailValue = $("#email").val();
			console.log($("#oldPassword").val())
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
		}
		else {
			oldPassValid = false
			$("#password_err").removeAttr("hidden");
			invalidateElement($("#oldPassword"));
		}

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
			encryptPassword("newPassword");
			encryptPassword("confirmPass");

			return;
		}
		else {
			e.preventDefault();
		}
	})
	$("#newPassword").on("focusin", (e) => {
		$("#passRules").removeAttr("hidden")
	})

	$("#newPassword").on("input", (e) => {

		newPassValid = validatePassword($("#newPassword"), $("#ucaseErr"), $("#lcaseErr"), $("#numberErr"), $("#scharErr"))

	})
})
