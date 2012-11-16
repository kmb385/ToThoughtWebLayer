<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<div class="control-container">
	<ttTags:control text="Profile"
		href="${pageContext.request.contextPath}/resume/profile"
		classes="shadow" imageClass="profile-btn" />
	<ttTags:control text="Technical Expertise"
		href="${pageContext.request.contextPath}/resume/tech"
		imageClass="tech-btn" classes="shadow control-26" />
</div>