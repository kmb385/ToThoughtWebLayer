$(document).ready(function(){
	$(".tag-editor").ttTagEditor({
		max_tags : 20,
		param : "tags"
	});
	
	$("#add-detail").click(function(){
		var value = $("#details-input").val();
		var detail = $("<li />").html(value);
		var hiddenDetail = $("<input />",{
			type: "hidden",
			name: "experienceDetails",
			value: value
			
		});
		$("#details").append(detail);
		$("#details").append(hiddenDetail);
		$("#details-input").val("").focus();
	});
	
	$( ".date-picker" ).datepicker();
	
	var experienceId = $("#experienceId").val();
	if (experienceId) {
		var url = ttRoot + "/resume/manager/experience/"  + experienceId + "/tags";
		$(".tag-editor").ttTagEditor("load", url);
	}

});

