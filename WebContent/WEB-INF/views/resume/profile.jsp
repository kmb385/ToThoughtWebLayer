<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<ttTags:documentTemplate cssFiles="post.css,post_tags.css,resume.css"
	requiresTextEditor="false" sidebarFragment="resume_sidebar.jsp">
		<jsp:include page="../fragments/resume/profile.jsp"/> 
</ttTags:documentTemplate>