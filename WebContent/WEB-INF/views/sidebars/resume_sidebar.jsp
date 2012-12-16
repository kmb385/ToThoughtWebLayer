<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="control-container">
	<ttTags:control text="Profile"
		href="${pageContext.request.contextPath}/resume/profile"
		classes="shadow" imageClass="profile-btn" />
	<ttTags:control text="Technical Skills"
		href="${pageContext.request.contextPath}/resume/skills"
		imageClass="skills-btn" classes="shadow control-26" />
	<ttTags:control text="Work Experience"
		href="${pageContext.request.contextPath}/resume/experience"
		imageClass="work-btn" classes="shadow control-26" />
	<ttTags:control text="Education"
		href="${pageContext.request.contextPath}/resume/degree"
		imageClass="education-btn" classes="shadow control-26" />
	<ttTags:control text="PDF Resume" 
		href="${pageContext.request.contextPath}/resume/full/generatePDF" 
		imageClass="pdf-btn" classes="shadow control-26"/>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<ttTags:control text="Resume Manager"
			href="${pageContext.request.contextPath}/secure/resume/manager/"
			imageClass="manager-btn" classes="shadow control-26" />
	</sec:authorize>
</div>