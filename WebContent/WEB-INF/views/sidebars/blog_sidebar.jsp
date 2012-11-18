<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<div class="control-container">
<c:choose>
	<c:when test="${isNew}">
		<ttTags:control text="Create New Post"
			href="${pageContext.request.contextPath}/post/new" classes="shadow"
			imageClass="new-control" />	
	</c:when>
	<c:otherwise>
		<ttTags:control text="Edit Post"
			href="${pageContext.request.contextPath}/post/${post.postId }/edit"
			classes="shadow" imageClass="edit-control" />
		<ttTags:control text="Delete Post"
			href="${pageContext.request.contextPath}/post/${post.postId }/delete"
			imageClass="delete-control" classes="shadow" />	
	</c:otherwise>
</c:choose>
	

</div>
<div class="v-margin-20 h-margin-20">
	<div class="bold fg3 v-margin-4 font-medium">Find Posts By Tag</div>
	<div class="bg2 pad-5 border shadow">
		<c:forEach items="${tags}" var="tag">
			<div class="v-margin-4 clearfix">
				<a href="${pageContext.request.contextPath}/blog/tag/${tag.tagId}/page/0"
					class="post-tag">${tag.name}</a>
				<span class="bold fg3"> x ${tag.count}</span>
			</div>
		</c:forEach>
	</div>
</div>
