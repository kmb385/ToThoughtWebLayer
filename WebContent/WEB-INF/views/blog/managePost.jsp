<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>.toThought();</title>
<jsp:include page="../fragments/base_css.jsp" />
<link href="${pageContext.request.contextPath}/resources/css/post.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/post_tags.css" rel="stylesheet" />
<jsp:include page="../fragments/base_js.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tag_editor.js"></script>
<jsp:include page="../fragments/text_editor_files.jsp" />
<script>
	$(document).ready(function() {
		var myTinyMce = new MyTinyMce("post-editor");
		myTinyMce.load();
		
		$(".tag-editor").ttTagEditor();
		
		var postId = $("#postId").val();
		if(postId){
			var url = ttRoot + /post/ + postId + "/tags";
			$(".tag-editor").ttTagEditor("load", url);
		}
		
		$("input").eq(0).focus();
	});
</script>
</head>
<body>
	<jsp:include page="../fragments/header.jsp" />
	<div id="container" class="clearfix bg2">
		<div id="center-panel" class="clearfix">
			<div class="font-large bold">New Blog Post</div>
			<form action="${pageContext.request.contextPath}/post/save" method="POST">
				<div class="v-margin-10">
					<div class="bold v-margin-4">Title</div>
					<div>
						<input name="title" class="post-title" value="${post.title}"/>
					</div>
				</div>
				<div class="v-margin-10 clearfix">
					<textarea id="post-editor" class="post-editor" name="body" >${post.postPart.body}</textarea>
				</div>
				<div class="v-margin-10">
					<div class="bold v-margin-4">Tags</div>
					<div class="tag-editor"></div>
				</div>
				<input id="postId" name="postId" type="hidden" value="${post.postId}"/>
			</form>
		</div>
		<div id="side-bar" class="frame"></div>
	</div>
</body>
</html>
