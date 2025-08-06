/**
 * 
 */

$(document).ready(function(){
	$("input[type='checkbox']").change(function(){
		if($("input[type='checkbox']").is(':checked') )
		{
			alert($("input[type='checkbox']").val())
		}
			
		
	})
})