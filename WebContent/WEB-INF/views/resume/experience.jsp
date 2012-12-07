<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,list.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">Work Experience</div>
	<c:forEach var="experience" items="${experiences}">
		<div class="v-bottom-margin-20">
			<div class="clearfix">			
				<div class="font-medium bold float-left">
					<a href="<c:url value="/resume/manager/experience/edit/${experience.experienceId}"  />" 
						class="no-decoration-anchor">${experience.position }</a>
				</div>
				<div class="float-right">
					<fmt:formatDate value="${experience.startDate}" pattern="MMMM yyyy" /> -
					<c:choose>
						<c:when test="${experience.isPresent}">
							Present
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${experience.endDate}" pattern="MMMM yyyy" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="subtle">${experience.organization }</div>
			<p class="v-margin-10">${experience.description }</p>
			<div class="simple-list">
				<ul>
					<c:forEach var="detail" items="${experience.experienceDetails }">
						<li>${detail.description }</li>
					</c:forEach>
				</ul>
			</div>
			<div class="post-tags clearfix">
				<c:forEach var="tag" items="${experience.tags}">
					<div class="post-tag">${tag.name}</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</ttTags:documentTemplate>

