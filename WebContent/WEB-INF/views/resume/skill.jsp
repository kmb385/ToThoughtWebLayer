<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<ttTags:documentTemplate cssFiles="tabs.css, resume.css, skill.css, rating.css"
	jsFiles="pages/skill.js" sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottm-margin-20 clearfix">
		<img
			src="<c:url value="/resources/images/resume/skills/uploaded-icons/${skill.image.name}"/>"
			class="float-left" />
		<div class="skill-page-title float-left">
			<div class="font-large bold">${skill.name}</div>
		</div>
		<div class="float-right rating rating-${skill.rating}"></div>
	</div>
	<div>${skill.provider}</div>
	<div class="v-margin-20">${skill.description}</div>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="v-margin-10 clearfix">
			<a
				href="<c:url value="/secure/resume/manager/skills/edit/${skill.skillId}"  />"
				class="button-32 edit-control-btn"></a> <a
				href="<c:url value="/secure/resume/manager/skills/delete/${skill.skillId}"  />"
				class="button-32 delete-control-btn"></a>
		</div>
	</sec:authorize>
	<div class="tabs clearfix">
		<!--  <div class="font-medium bold subtle tab active">GitHub Commits<div class="tab-pointer"></div></div>
		<div class="font-medium bold subtle tab">StackOverflow Answers<div class="tab-pointer"></div></div>-->
		<a class="font-medium bold subtle tab ${detailType == 'github' ? 'active':''}" 
			href="<c:url value="/resume/skills/${skill.skillId}/detail/github/detailpage/0"/>">
				<span>GitHub Commits</span>
				<div class="tab-pointer"></div>
		</a>
		<a class="font-medium bold subtle tab ${detailType == 'stack' ? 'active':''}" 
			href="<c:url value="/resume/skills/${skill.skillId}/detail/stack/detailpage/0"/>">
				<span>StackOverflow Answers</span>
				<div class="tab-pointer"></div>
		</a>
	</div>
	<ul id="skill-details">
		<c:forEach var="detail" items="${details}">
			<li class="v-margin-5">
				<a href="${detail.url}" target="_blank" class="commit">${detail.title}</a>
				<span class="subtle font-small"><fmt:formatDate
					value="${detail.createdDt}" pattern="MM/dd/yyyy" /></span>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="<c:url value="/secure/resume/manager/commit/edit/${detail.id}"/>"
					class="small-edit-btn h-margin-3"></a>
				</sec:authorize>
			</li>
		</c:forEach>
	</ul>
	<c:if test="${!empty nextPage}">
		<a href="<c:url value="/resume/skills/${skill.skillId}/detail/${detailType}"/>"
			class="more subtle">View More</a>
	</c:if>
</ttTags:documentTemplate>

