<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld" %>

<ttTags:documentTemplate cssFiles="post.css,post_tags.css" 
	jsFiles="tag_editor.js,${pageContext.request.contextPath}/resources/js/pages/manage_post.js" 
	requiresTextEditor="true">
	
	<div class="font-large bold">New Blog Post</div>
	<form action="${pageContext.request.contextPath}/post/save" method="POST">
		<div class="v-margin-10">
			<div class="bold v-margin-4">Title</div>
			<div>
				<input name="title" class="post-title" value="${post.title}" />
			</div>
		</div>
			<div class="v-margin-10 clearfix">
				<textarea id="post-editor" class="post-editor" name="body">${post.postPart.body}</textarea>
			</div>
			<div class="v-margin-10">
				<div class="bold v-margin-4">Tags</div>
				<div class="tag-editor"></div>
			</div>
			<input id="postId" name="postId" type="hidden" value="${post.postId}" />
	</form>
	<div class="spacer"></div>
</ttTags:documentTemplate>
