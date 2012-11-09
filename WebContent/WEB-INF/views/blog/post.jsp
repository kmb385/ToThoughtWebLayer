<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags"
	uri="http://tothought.cloudfoundry.com/ttTags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToThought</title>
<jsp:include page="../fragments/base_css.jsp" />
<link href="${pageContext.request.contextPath}/resources/css/post.css"
	rel="stylesheet" />
<jsp:include page="../fragments/text_editor_files.jsp" />
<script>
	SyntaxHighlighter.all();
</script>
</head>
<body>
	<jsp:include page="../fragments/header.jsp" />
	<div id="container" class="clearfix bg4">
		<div id="inner-container" class="bg2">
			<div id="center-panel" class="bg2 clearfix">
				<jsp:include page="../fragments/post.jsp" />
			</div>
			<div id="side-bar">
				<div class="control-container">
					<ttTags:control text="Edit Post"
						href="${pageContext.request.contextPath}/post/${post.postId }/edit"
						classes="shadow" imageClass="edit-post" />
					<ttTags:control text="Delete Post"
						href="${pageContext.request.contextPath}/post/${post.postId }/delete"
						imageClass="delete-post" classes="shadow" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
