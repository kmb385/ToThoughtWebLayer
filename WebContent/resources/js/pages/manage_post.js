$(document).ready(function() {
	var myTinyMce = new MyTinyMce("post-editor");
	myTinyMce.load();

	$(".tag-editor").ttTagEditor();

	var postId = $("#postId").val();
	if (postId) {
		var url = ttRoot + "/post/" + postId + "/tags";
		$(".tag-editor").ttTagEditor("load", url);
	}

	$("input").eq(0).focus();
});