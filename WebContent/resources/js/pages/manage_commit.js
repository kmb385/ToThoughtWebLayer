$(document).ready(function() {

	$(".tag-editor").ttTagEditor({
		max_tags : 4,
		param : "tags"
	});

	var commitId = $("#commitId").val();
	if (commitId) {
		var url = ttRoot + "/secure/resume/manager/commit/" + commitId + "/tags";
		$(".tag-editor").ttTagEditor("load", url);
	}
	
	$("input:first").focus();
});