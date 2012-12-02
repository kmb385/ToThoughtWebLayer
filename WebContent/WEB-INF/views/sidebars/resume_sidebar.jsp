<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

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
	<div class="v-margin-20 font-medium subtle bold">Admin Controls</div>
	<ttTags:control text="Resume Manager"
		href="${pageContext.request.contextPath}/resume/manager/"
		imageClass="manager-btn" classes="shadow control-26" />
	<c:if test="${skill != null}">
		<ttTags:control text="Edit Skill"
			href="${pageContext.request.contextPath}/resume/manager/skills/${skill.skillId}/edit"
			imageClass="edit-control" classes="shadow control-26" />
		<ttTags:control text="Delete Skill"
			href="${pageContext.request.contextPath}/resume/manager/skills/${skill.skillId}/delete"
			imageClass="delete-control" classes="shadow control-26" />	
	</c:if>
</div>