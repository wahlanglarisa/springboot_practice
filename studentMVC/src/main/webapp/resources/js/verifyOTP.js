$(document).ready(() => {
	$(document).on("submit", (e) => {
		var emailOTP = $("#emailOTP").val();
		var phoneOTP = $("#phoneOTP").val();
		if (emailOTP.length == 0) {
			$("#EmailOTPEmpty").removeAttr("hidden");
			e.preventDefault();
		}
		else {
			$("#EmailOTPEmpty").attr("hidden", true);
		}
		if (phoneOTP.length == 0) {
			$("#PhoneOTPEmpty").removeAttr("hidden");
			e.preventDefault();

		}
		else {
			$("#PhoneOTPEmpty").attr("hidden", true);
		}
	})
})

