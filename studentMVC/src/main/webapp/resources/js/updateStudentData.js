import { addState, addDistrict } from "./addState.js";
import { invalidateElement, validateElement } from "./invalidateElement.js";
var invalidTabSwitchEl;
var addressTabVisited = 0
function deleterow(el) {
	$(el).parents("tr").remove()

}

$(document).ready(() => {
	var oldPreAddr1 = ""
	var oldPreAddr2 = ""
	var oldPreAddr3 = ""

	var phoneNoValid = true;
	var fNameValid = true;
	var LNameValid = true;
	var phoneNoValidLen = true;
	var emailValid = true;
	const EmailPattern = /(?:((?:[\w-]+(?:\.[\w-]+)*)@(?:(?:[\w-]+\.)*\w[\w-]{0,66})\.(?:[a-z]{2,6}(?:\.[a-z]{2})?));*)/
	var validPhoto = true;
	const NumPattern = /[A-Za-z\s!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const FLNamePattern = /[0-9!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
	const checkAddressTab = function() {
		if ($("#permaddressline1").val().length != 0 &&
			$("#preaddressline1").val().length != 0 &&
			$("#permCountryCode").val() != 0 &&
			$("#permStateCode").val() != 0 &&
			$("#permDistrictCode").val() != 0 &&
			$("#preCountryCode").val() != 0 &&
			$("#preStateCode").val() != 0 &&
			$("#preDistrictCode").val() != 0) {
			$("#addrperm1err").attr("hidden", true)

			return true;
		}
		else {
			if ($("#permaddressline1").val().length == 0) {
				invalidateElement($("#permaddressline1"))
				$("#addrperm1err").removeAttr("hidden")
			}
			if ($("#preaddressline1").val().length == 0) {
				invalidateElement($("#preaddressline1"))
				$("#addrpre1err").removeAttr("hidden")
			}
			if ($("#permCountryCode").val() == 0) {
				invalidateElement($("#permCountryCode"))

				$("#permCountryErr").removeAttr("hidden")
			}
			if ($("#preCountryCode").val() == 0) {
				invalidateElement($("#preCountryCode"))

				$("#preCountryErr").removeAttr("hidden")
			}
			if ($("#permStateCode").val() == 0) {
				invalidateElement($("#permStateCode"))

				$("#permStateErr").removeAttr("hidden")
			}
			if ($("#preStateCode").val() == 0) {
				invalidateElement($("#preStateCode"))

				$("#preStateErr").removeAttr("hidden")
			}
			if ($("#permDistrictCode").val() == 0) {
				invalidateElement($("#permDistrictCode"))

				$("#permDistrictErr").removeAttr("hidden")
			}
			if ($("#preDistrictCode").val() == 0) {
				invalidateElement($("#preDistrictCode"))

				$("#preDistrictErr").removeAttr("hidden")
			}
			return false;
		}
	}

	const checkValidity = function() {
		if (emailValid &&
			phoneNoValid &&
			fNameValid &&
			LNameValid &&
			phoneNoValidLen && validPhoto && $("#email").val().length != 0 &&
			$("#first_name").val().length > 1 &&
			$("#last_name").val().length >= 1 &&
			$("#phone_no").val().length != 0) {
			return true;
		}
		else {
			if ($("#email").val().length == 0) {
				invalidateElement($("#email"));
				$("#emailReqErr").removeAttr("hidden");

			}
			if ($("#first_name").val().length <= 1) {
				invalidateElement($("#first_name"))

				$("#FnameEmptyErr").removeAttr("hidden");

			}
			if ($("#last_name").val().length <= 1) {
				invalidateElement($("#last_name"))

				$("#LnameEmptyErr").removeAttr("hidden");

			}
			return false;
		}
	}
	$("#permaddressline1").on("input", (e) => {
		validateElement($("#permaddressline1"))
		$("#addrperm1err").attr("hidden", true)

	})
	$("#preaddressline1").on("input,change", (e) => {
		validateElement($("#preaddressline1"))
		$("#addrpre1err").attr("hidden", true)
	})


	$("#presentAddrCheck").change(async () => {
		console.log($("#presentAddrCheck").is(":checked"))
		if ($("#presentAddrCheck").is(":checked")) {
			oldPreAddr1 = $("#preaddressline1").val()
			oldPreAddr2 = $("#preaddressline2").val()
			oldPreAddr3 = $("#preaddressline3").val()

			console.log($("#presentAddrCheck").is(":checked"))

			$("#preaddressline1").val($("#permaddressline1").val())
			$("#preaddressline2").val($("#permaddressline2").val())
			$("#preaddressline3").val($("#permaddressline3").val())
			$("#preCountryCode").val($("#permCountryCode").val())
			await addState($("#preStateCode"), $("#preCountryCode"))
			$("#preStateCode").val($("#permStateCode").val())
			await addDistrict($("#preDistrictCode"), $("#permStateCode"))

			$("#preDistrictCode").val($("#permDistrictCode").val())

		}
		else {
			$("#preaddressline1").val(oldPreAddr1)
		}
	})
	$("#phone_no").on("input", (e) => {
		var PhoneNoValidity = NumPattern.test($("#phone_no").val());

		console.log();
		if (PhoneNoValidity) {
			console.log("in if block")
			$("#phoneCharErr").removeAttr("hidden");
			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValid = false;
			console.log("why not focus")
			$("#phone_no").addClass("is-invalid")


		}
		else {
			phoneNoValid = true;
			console.log("in else block")
			$("#phoneCharErr").attr("hidden", "true");
			$("#phone_no").removeClass("is-invalid");
			$("#phone_no").addClass("is-valid")
			$("#submitButton").removeAttr("disabled");

		}
		if ($("#phone_no").val().length < 10 || $("#phone_no").val().length > 10) {
			console.log("in if block")
			$("#phoneLength").removeAttr("hidden");
			$("#phone_no").addClass("is-invalid")

			//$("#phoneLength").html("Phone number should have 10 digits");

			//$("#submitButton").attr("disabled", "true");
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			phoneNoValidLen = false;
			console.log("why not focus")

		}
		else {
			phoneNoValidLen = true;
			console.log("in else block")
			$("#phone_no").removeClass("is-invalid");
			$("#phone_no").addClass("is-valid")
			$("#phoneLength").attr("hidden", "true");
			$("#submitButton").removeAttr("disabled");

		}

	})
	$("#first_name").on("input", (e) => {
		var isFNameValid = FLNamePattern.test($("#first_name").val());

		if (isFNameValid) {
			console.log("in if block")
			$("#FnameErr").removeAttr("hidden");
			fNameValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			$("#first_name").addClass("is-invalid")
			console.log("why not focus")
		}
		else {
			fNameValid = true
			console.log("in else block")
			$("#first_name").removeClass("is-invalid")

			$("#first_name").addClass("is-valid")
			$("#FnameErr").attr("hidden", "true");
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$("#last_name").on("input", (e) => {
		var isFNameValid = FLNamePattern.test($("#last_name").val());

		if (isFNameValid) {
			console.log("in if block")
			$("#LnameErr").removeAttr("hidden");
			LNameValid = false
			$("#last_name").addClass("is-invalid")

			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			console.log("why not focus")
		}
		else {
			LNameValid = true
			console.log("in else block")
			$("#LnameErr").attr("hidden", "true");
			$("#last_name").removeClass("is-invalid");
			$("#last_name").addClass("is-valid")
			//$("#submitButton").removeAttr("disabled");
		}

	})
	$("#email").on("input", (e) => {
		var isEmailValid = EmailPattern.test($("#email").val());

		if (!(isEmailValid)) {
			console.log("in if block")
			$("#EmailCharErr").removeAttr("hidden");
			emailValid = false
			//$("#phone_no").focus();
			//document.getElementById("phone_no").focus();
			$("#email").addClass("is-invalid")

			console.log("why not focus")
		}
		else {
			emailValid = true
			console.log("in else block")
			$("#EmailCharErr").attr("hidden", "true");
			$("#email").removeClass("is-invalid");
			$("#email").addClass("is-valid")
			//$("#submitButton").removeAttr("disabled");
		}

	})

	$("#permCountryCode").on("change", (e) => {
		validateElement($("#permCountryCode"))

		$("#permCountryErr").attr("hidden", true)
		addState($("#permStateCode"), $("#permCountryCode"))
	})
	$("#permStateCode").on("change", (e) => {
		validateElement($("#permStateCode"))

		$("#permStateErr").attr("hidden", true)
		addDistrict($("#permDistrictCode"), $("#permStateCode"));
	})
	$("#preCountryCode").on("change", (e) => {
		validateElement($("#preCountryCode"))

		$("#preCountryErr").attr("hidden", true)
		addState($("#preStateCode"), $("#preCountryCode"))
	})
	$("#preStateCode").on("change", (e) => {
		validateElement($("#preStateCode"))

		$("#preStateErr").attr("hidden", true)
		addDistrict($("#preDistrictCode"), $("#preStateCode"));
	})

	$("#file").on("change", (e) => {
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
				console.log(e.target.result)
			}
			console.log(file)
			reader.readAsDataURL(file);

		}
	})
	$("#profile-tab").on("click", (e) => {
		addressTabVisited++;
	})
	$("#pDetailsButton").on("click", (e) => {
		console.log($(e.target).is("button[type='button']") + " " + $(e.target).is("button[type='submit']"));
		if (checkValidity()) {
			$('#myTab button[id="home-tab"').removeAttr("disabled");

			$('#myTab button[id="profile-tab"').tab("show")
			addressTabVisited++;
		}
		else {
			return
		}

	})
	$("#pDetailsSubmitButton").on("click", (e) => {

		if (checkValidity()) {
			if (addressTabVisited == 0)
				return
			else if (!checkAddressTab()) {
				ShowModal("Address Details Tab")
				invalidTabSwitchEl = $('#myTab button[id="profile-tab"]')
				e.preventDefault()
			}
			else
				return
		}
		else {
			e.preventDefault();
		}


	})
	$("#AddressButton").on("click", (e) => {

		if (checkAddressTab()) {
			$('#myTab button[id="profile-tab"').removeAttr("disabled");

			$('#myTab button[id="contact-tab"').tab("show")
		}
		else {
			return
		}


	})
	$("#AddressSubmitButton").on("click", (e) => {
		if (checkAddressTab()) {
			if (checkValidity())
				return
			else {
				ShowModal("Personal Details Tab");
				invalidTabSwitchEl = $('#myTab button[id="home-tab"]');
				e.preventDefault();

			}
		}
		else {
			e.preventDefault();
		}
	})
	$("#updateUserLink").on("click", (e) => {
		const modalEl = document.getElementById("UpdateModal");
		const modalInstance = bootstrap.Modal.getInstance(modalEl);

		modalInstance.hide();
		new bootstrap.Tab(invalidTabSwitchEl).show()

	})
	var qualbuttonclicked = 0

	$("#addQualButton").on("click", async (e) => {
		var qualOptions = ""
		var duplicateQual = false
		var qualtype = $("#qualtype").val();
		var stream = $("#stream").val();
		var percentage = $("#percentage").val();
		if (stream.length == 0 && percentage == 0) {
			$("#eduAddErr").text("Please enter your education stream and percentage");
			return
		}
		else if (stream.length == 0) {
			$("#eduAddErr").text("Please enter your education stream");
			return
		}
		else if (percentage == 0) {
			$("#eduAddErr").text("Please enter your percentage`");
			return
		}
		await qualArray.forEach(element => {
			if (qualtype == element.qtype && stream == element.stream) {
				duplicateQual = true;
			}
		})
		if (duplicateQual) {
			alert("Cannot add duplicate Qualification<b> " + $("#qualtype option:selected").text() + " " + stream + "</b>");
		}
		else {
			$("#qualTable tbody").append(`<tr><td>${$("#qualtype option:selected").text()} <input type="hidden"
		name="studentQualifications[${qualbuttonclicked}].qualificationType" 
		id="studentQualifications${qualbuttonclicked}.qualificationType" value="${$("#qualtype").val()}">
		<input type="hidden"
		name="studentQualifications[${qualbuttonclicked}].stID" 
		id="studentQualifications${qualbuttonclicked}.stID" value="${$("#st_id").val()}">
		</td><td>${stream} 
		<input type="hidden"
		name="studentQualifications[${qualbuttonclicked}].qualificationStream"
		id="studentQualifications${qualbuttonclicked}.qualificationStream"
		value="${stream}"/></td><td>${percentage}
		<input type="hidden" name="studentQualifications[${qualbuttonclicked}].percentage"
		id="studentQualifications${qualbuttonclicked}.percentage" value="${percentage}"
		 /></td><td><button type="button" id="delButton${qualbuttonclicked}" class="btn btn-danger" onclick="$(this).parents('tr').remove();">Delete</button></td><tr>	
	`)
		}
		var ob = { "qualification": $("#qualtype option:selected").text(), "stream": stream, "percentage": percentage, "qtype": qualtype }

		qualArray.push(ob);

		qualbuttonclicked++;
		console.log(qualArray);
	})
	$("#qualificationsSumbit").on("click", (e) => {
		const qualSet = new Set();
		const hasDuplicate = qualArray.some(element => {
			console.log("qualification ", element, " is already in set: ", qualSet.has(element));
			// returns true for the first duplicate and terminates .some()
			return (qualSet.size === qualSet.add(element).size)
		});
		if (hasDuplicate) {
			e.preventDefault();
		}
		else {
			if (checkAddressTab()) {
				if (checkValidity())
					return
				else {
					ShowModal("Personal Details Tab");
					invalidTabSwitchEl = $('#myTab button[id="home-tab"]');
					e.preventDefault();

				}
			}
			else {
				ShowModal("Address Details Tab");
				invalidTabSwitchEl = $('#myTab button[id="profile-tab"]');
				e.preventDefault();
			}
		}
	})

})



function ShowModal(TabName) {
	const modalElement = $("#UpdateModal");
	const modal = new bootstrap.Modal(modalElement);
	$("#UpdateModal .modal-title").text(`Invalid fields found in ${TabName}`);

	$("#UpdateModal .modal-body").text(`You have invalid fields in ${TabName}. Please correct them before Submitting`);
	modal.show();
}
