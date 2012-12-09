<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ttTags:documentTemplate cssFiles="resume.css, skill.css, rating.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottm-margin-20 clearfix">
		<img src="../../resources/images/resume/skills/uploaded-icons/${skill.image.name}" class="float-left"/>
		<div class="skill-page-title float-left">
			<div class="font-large bold">${skill.name}</div>
		</div>
		<div class="float-right rating rating-${skill.rating}"></div>
	</div>
	<div>
		${skill.provider}
	</div>
	<div class="v-margin-20">
		${skill.description}
	</div>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="v-margin-10 control-group clearfix">
			<a href="<c:url value="/secure/resume/manager/skills/edit/${skill.skillId}"  />"
				class="img edit-control"></a>
			<a href="<c:url value="/secure/resume/manager/skills/delete/${skill.skillId}"  />"
				class="img delete-control"></a>
		</div>
	</sec:authorize>
</ttTags:documentTemplate>

