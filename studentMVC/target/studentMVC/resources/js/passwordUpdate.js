$(document).ready(function() {
	var oldPassValid = false;
	var confirmPassValid=false;
	var checkValid=function(){
		if(oldPassValid && confirmPassValid){
			return true;
		}
		else{
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
			confirmPassValid=true;
			$("confirmNewPassErr").attr("hidden", "hidden");

			validateElement($("#confirmPass"));
		}
		else {
			confirmPassValid=false;
			$("#confirmNewPassErr").removeAttr("hidden");
			invalidateElement($("#confirmPass"));

		}

	})
	$("#submit").on("click",(e)=>{
		if(checkValid()){
			return;
		}
		else{
			e.preventDefault();
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