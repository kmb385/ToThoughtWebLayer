<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,list.css"
	sidebarFragment="resume_sidebar.jsp">
	<jsp:include page="../fragments/resume/experience.jsp"/>
</ttTags:documentTemplate>

