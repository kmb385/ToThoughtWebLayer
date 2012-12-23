<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="comment-form" class="bg4">
	<a id="postcomment" class="font-large bold subtle">Post a Comment</a>
	<form action="<c:url value="/comment/save" />" method="POST" class="h-margin-20">
		<div class="v-margin-5">
			<div class="bold subtle v-margin-4">
				<span class="required">*</span>Name
			</div>
			<input name="author" class="input-medium" />
		</div>
		<div class="v-margin-5">
			<div class="bold subtle v-margin-4">Email</div>
			<input name="email" class="input-medium" />
		</div>
		<div class="v-margin-5">
			<div class="bold subtle v-margin-4">Site</div>
			<input name="site" class="input-medium" />
		</div>
		<div class="v-margin-5 clearfix">
			<div class="bold subtle v-margin-4">Comment</div>
			<textarea name="body" class="input-large"></textarea>
		</div>
		<div>
			<input type="submit" class="control" value="Submit" />
		</div>
		<input type="hidden" name="post" value="${post.postId}" />
	</form>
</div>