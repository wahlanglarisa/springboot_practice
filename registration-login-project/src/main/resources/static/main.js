/**
 * 
 */$(document).ready(function() {
	 value = 0
	 console.log("Page Loaded");

	 $("#checkDefault").change(function() {
		 if ($(this).is(":checked")) {

			 $("#password").attr("type", "text");
		 } else {
			 $("#password").attr("type", "password");
		 }
	 });
	 $("#password").on("input", function() {
		 value++;
	 });
	 $('#reg-button').click(function() {
		 console.log(value)
		 var email = $('#email').val();
		 var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

		 if (emailPattern.test(email)) {
			$("#reg-button").attr("type","submit");

			 $('#emailError').text('');
		 } else {
			 $('#emailError').text('Please enter a valid email address.');
			 $("#reg-button").attr("type","button");

		 } if (value>=8) {
			$("#reg-button").attr("type","submit");

			 $('#passError').text('');
		 } else {
			 $('#passError').text('Password is too short');
			 $("#reg-button").attr("type","button");
		 }
		
	 });

 });