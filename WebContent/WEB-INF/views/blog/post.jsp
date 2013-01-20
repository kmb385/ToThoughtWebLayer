<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld" %>

<ttTags:documentTemplate sidebarFragment="blog_sidebar.jsp"
	cssFiles="post.css,post_tags.css,comments.css" requiresTextEditor="true" title="${post.title}">
		<jsp:include page="../fragments/post.jsp" />
		<jsp:include page="../fragments/comments.jsp" />
		<jsp:include page="../fragments/commentForm.jsp" />
</ttTags:documentTemplate>
