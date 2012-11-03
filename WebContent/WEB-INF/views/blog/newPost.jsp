<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.toThought();</title>
<jsp:include page="../fragments/base_css.jsp" />
<link href="../resources/css/post.css" rel="stylesheet" />
<link href="../resources/css/post_tags.css" rel="stylesheet" />
<jsp:include page="../fragments/jquery_files.jsp" />
<script type="text/javascript" src="../resources/js/tag_editor.js"></script>
<jsp:include page="../fragments/text_editor_files.jsp" />
<script>
	$(document).ready(function() {
		var myTinyMce = new MyTinyMce("post-editor");
		myTinyMce.load();
		
		$(".tag-editor").ttTagEditor();
		$("input").focus();
	});
</script>
</head>
<body>
	<jsp:include page="../fragments/header.jsp" />
	<div id="container" class="clearfix bg2">
		<div id="center-panel" class="clearfix">
			<div class="font-medium bold">New Blog Post</div>
			<form action="save" method="POST">
				<div class="v-margin-10">
					<div class="bold v-margin-4">Title</div>
					<div>
						<input name="title" class="post-title" />
					</div>
				</div>
				<div class="v-margin-10 clearfix">
					<textarea id="post-editor" class="post-editor" name="body" ></textarea>
				</div>
				<div class="v-margin-10">
					<div class="bold v-margin-4">Tags</div>
					<div class="tag-editor"></div>
				</div>
			</form>
		</div>
		<div id="side-bar" class="frame"></div>
	</div>
</body>
</html>
