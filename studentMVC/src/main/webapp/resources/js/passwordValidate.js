import { invalidateElement, validateElement } from "./invalidateElement.js";

export function validatePassword(passwordField, ucaseErr, lcaseErr, numberErr, scharErr) {
	const upperCasePass = /^(?=.*[A-Z])/
	const lowerCasePass = /^(?=.*[a-z])/
	const numberPass = /^(?=.*[0-9])/
	const specialCharPass = /^(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]/
	var PassValidLen;
	var PassHaveUcase = newFunction(upperCasePass, passwordField, ucaseErr);
	var passHaveLcase = newFunction(lowerCasePass, passwordField, lcaseErr);
	var Passhavenumber = newFunction(numberPass, passwordField, numberErr);
	var passHaveSpchar = newFunction(specialCharPass, passwordField,scharErr);
	if (passwordField.val().length >= 8) {
		var elementErr = $("#lenErr")
		elementErr.removeClass("text-danger");
		elementErr.addClass("text-success");
		elementErr.addClass("list");
		PassValidLen = true;
	}
	else {
		var elementErr = $("#lenErr")

		elementErr.addClass("text-danger");
		elementErr.removeClass("list");
		PassValidLen = false;
	}

	if (PassHaveUcase && passHaveLcase && Passhavenumber && passHaveSpchar && PassValidLen) {
		validateElement(passwordField)

		return true;
	}
	else {
		invalidateElement(passwordField)
		return false;
	}
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