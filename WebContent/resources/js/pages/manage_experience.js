$(document).ready(function() {
	$(".tag-editor").ttTagEditor({
		max_tags : 20,
		param : "tags"
	});

	$(".simple-list").ttSimpleList("experienceDetails");

	$(".date-picker").datepicker();

	var experienceId = $("#experienceId").val();
	if (experienceId) {
		var url = ttRoot + "/resume/experience/" + experienceId + "/tags";
		$(".tag-editor").ttTagEditor("load", url);
	}
	
	$("input:first").focus();

});
