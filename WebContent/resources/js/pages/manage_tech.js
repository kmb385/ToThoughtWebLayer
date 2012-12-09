$(document).ready(function() {
	$("#rating").ttRating();
	$(".tag-editor").ttTagEditor({
		max_tags : 1,
		param : "tag"
	});

	var skillId = $("#skillId").val();
	if (skillId) {
		var url = ttRoot + "/secure/resume/manager/skills/" + skillId + "/tags";
		$(".tag-editor").ttTagEditor("load", url);
	}
	
	$("input:first").focus();
});
