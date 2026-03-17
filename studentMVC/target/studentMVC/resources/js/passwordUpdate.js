$(document).ready(function() {
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
					dupEmail = true;
					$("#password_err").removeAttr("hidden");
					invalidateElement($("#oldPassword"));
				} else {
					dupEmail = false;
					$("#password_err").attr("hidden", "hidden");
					validateElement($("#oldPassword"));
				}
			},
		})
	})
	$("#confirmPass").on("focusout", (e) => {
			let emailValue = $("#email").val();
			if($("#confirmPass").val()==$("#newPassword").val()){
				$("confirmNewPassErr").attr("hidden", "hidden");

				validateElement($("#confirmPass"));
			}
			else{
				$("#confirmNewPassErr").removeAttr("hidden");
				invalidateElement($("#confirmPass"));

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