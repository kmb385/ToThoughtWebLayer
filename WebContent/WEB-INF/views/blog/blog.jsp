<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags"
	uri="http://tothought.cloudfoundry.com/ttTags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToThought</title>
<jsp:include page="../fragments/base_css.jsp" />
<link href="${pageContext.request.contextPath}/resources/css/post.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css/post_tags.css"
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
				<c:forEach items="${posts}" var="tmpPost">
					<c:set var="post" value="${tmpPost}" scope="request" />
					<jsp:include page="../fragments/post.jsp"></jsp:include>
				</c:forEach>
			</div>
			<div id="side-bar">
				<div class="control-container">
					<ttTags:control text="Create New Post"
						href="${pageContext.request.contextPath}/post/new"
						classes="shadow" imageClass="new-post" />
				</div>
				<div class="v-margin-20 h-margin-20">
					<div class="bold fg3 v-margin-4 font-medium">Find Posts By
						Tag</div>
					<div class="bg2 pad-5 border shadow">
						<c:forEach items="${tags}" var="tag">
							<div class="v-margin-4 clearfix">
								<a href="${pageContext.request.contextPath}/blog/tag/${tag.tagId}"
									class="post-tag">${tag.name}</a> <span class="bold fg3">
									x ${tag.count}</span>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
