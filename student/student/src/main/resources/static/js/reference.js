function courseclick(item){
		alert(item);
	}
	function clicked() {
		alert(document.getElementById('deptSelect').value)
		itemVal=document.getElementById('deptSelect').value
		$.ajax({
			url: "/admin/"
				+ "getDepartmentAJAX/",
			method: "get",
			dataType:"json",
			data: {
				id: itemVal
			}
			, success: function (response) {
				courseElement = document.getElementById("course_table_body")
				courseElement.innerHTML="";
					response.forEach(element => {
						i = 0
						newRow = courseElement.insertRow(i)
						cell = newRow.insertCell(0)
						cell.innerHTML = `<input type='checkbox' class='form-check-input' value='${element}' name='courses' onclick='courseclick(${element})'>  <label value='${element}' class='form-check-label'>${element}</label>`
						console.log(element)
						i++;
					});
			}
		})
	}
	$(document).ready(function () {

		$("#toggle").change(function () {
			$("#hodid").val($(this).prop("checked"));
			alert($("#hodid").val())
		})

	});