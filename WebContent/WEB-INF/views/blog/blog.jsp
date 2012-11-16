<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate sidebarFragment="blog_sidebar.jsp"
	cssFiles="post.css,post_tags.css" requiresTextEditor="true">
		<c:set var="isNew" value="true" scope="request"/>
		<c:forEach items="${posts}" var="tmpPost">
			<c:set var="post" value="${tmpPost}" scope="request" />
			<jsp:include page="../fragments/post.jsp"></jsp:include>
		</c:forEach>
		<div>
			<c:if test="${!lastPage}">
				<ttTags:control text="Next 5 Entries" href="${nextPage}"
					classes="float-right shadow" imageClass="next-btn" shrinkwrap="true"
					floatImage="right" />
			</c:if>
			<c:if test="${prevPage > -1}">
				<ttTags:control text="Previous 5 Entries" href="${prevPage}"
					classes="float-right shadow" shrinkwrap="true" imageClass="prev-btn" />
			</c:if>
		</div>
</ttTags:documentTemplate>
