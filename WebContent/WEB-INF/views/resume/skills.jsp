<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate cssFiles="resume.css,rating.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">Technical Skills</div>
	<c:forEach var="skillCategory" items="${categories}">
		<div class="font-medium bold section-title subtle">${skillCategory.name}</div>
		<c:forEach var="skill" items="${skillCategory.skills}">
			<ttTags:rated-resume-item text="${skill.name}"
				imageSrc="../resources/images/resume/skills/uploaded-icons/${skill.image.name}" 
				rating="${skill.rating}" href="${pageContext.request.contextPath}/resume/skills/${skill.skillId}" />
		</c:forEach>
	</c:forEach>
</ttTags:documentTemplate>

