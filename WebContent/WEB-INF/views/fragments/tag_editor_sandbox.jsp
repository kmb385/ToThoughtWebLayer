<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../fragments/base_css.jsp" />
<link href="/tothought/resources/css/post.css" rel="stylesheet" />
<link href="/tothought/resources/css/post_tags.css" rel="stylesheet" />
<jsp:include page="../fragments/jquery_files.jsp" />
<script type="text/javascript" src="/tothought/resources/js/tag_editor.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script>
	$(document).ready(function() {
		$(".tag-editor").ttTagEditor();
		$("input").focus();
	});
</script>
</head>
<body>
<form>
	<div class="v-margin-10">
		<div class="tag-editor">
		</div>
	</div>
</form>
</body>
</html>