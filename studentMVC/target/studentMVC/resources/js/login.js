
$(document).ready(() => {
	const EmailPattern = /(?:((?:[\w-]+(?:\.[\w-]+)*)@(?:(?:[\w-]+\.)*\w[\w-]{0,66})\.(?:[a-z]{2,6}(?:\.[a-z]{2})?));*)/
	var emailValid = false;
	$("#email").on("input", (e) => {
		var isEmailValid = EmailPattern.test($("#email").val());
		if (!(isEmailValid)) {
			console.log("in if block")
			$("#EmailCharErr").removeAttr("hidden");
			emailValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
		}
		else {
			emailValid = true
			console.log("in else block")
			$("#EmailCharErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$(document).on("submit", (e) => {
		if (emailValid) {
			

			return;
			

		}
		else {
			e.preventDefault();
		}
	})
})


