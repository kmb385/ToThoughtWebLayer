<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate cssFiles="resume.css,resume_manager.css" sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">Resume Manager</div>
	<div class="tile-container">
		<a href="<c:url value="/secure/resume/manager/skills/new"/>" class="tile bg3 fg1"><span>Technical
				Skills</span>
			<span class="tile-img skills-tile-img"></span> 
		</a> 
		<a href="<c:url value="/secure/resume/manager/experience/new"/>" class="tile bg3 fg1">
			<span>Employment</span>
			<span class="tile-img work-tile-img"></span> 
		</a> 
		<a href="<c:url value="/secure/resume/manager/degree/new"/>" class="tile bg3 fg1">
			<span>Education</span>
			<span class="tile-img education-tile-img"></span> 
		</a> 
		<a href="#" class="tile bg3 fg1">
			<span>Training</span>
			<span class="tile-img skills-tile-img"></span> 
		</a> 
	</div>

</ttTags:documentTemplate>

