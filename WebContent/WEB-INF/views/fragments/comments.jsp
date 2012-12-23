<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:length(post.comments) > 0}">
	<div class="comments v-margin-20">
		<div class="clearfix">
			<div class="font-large bold subtle float-left">Comments</div>
			<a href="#postcomment" class="font-small subtle float-right">Post a Comment</a>
		</div>
		<c:forEach var="comment" items="${post.comments}">
			<div class="comment">
				<span class="bold subtle"><c:out value="${comment.author}"/></span> commented on 
				<span class="bold subtle"><fmt:formatDate value="${comment.postedDt}" pattern="MMMM dd yyyy H:mm aa" /></span><br /> 
				<span><c:out value="${comment.body}" /></span>
				<sec:authorize access="hasRole('ROLE_ADMIN')">			
					<a href="<c:url value="/secure/comment/${comment.commentId}/delete"/>" class="small-delete-btn h-margin-3"></a>
				</sec:authorize>
				<br />
				<c:if test="${!empty comment.email}">
					<a href="mailto:${comment.email}" class="font-small">Email</a>
				</c:if>
				<c:if test="${!empty comment.site}">
					<a href="${comment.site}" class="font-small">Site</a>
				</c:if>
			</div>
		</c:forEach>
	</div>
</c:if>