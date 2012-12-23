<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,list.css"
	jsFiles="tag_editor.js,list.js,${pageContext.request.contextPath}/resources/js/pages/manage_experience.js"
	sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">Manage Work Experience</div>
	<form method="post" action="<c:url value="/secure/resume/manager/experience/save"/>"
		enctype="multipart/form-data">
		<div class="v-margin-10">
			<div class="bold v-margin-4">Position</div>
			<div>
				<input name="position" value="${experience.position}" class="input-medium" />
			</div>
		</div>
		<form:errors path="experience.position" cssClass="error field-error" />
		<div class="v-margin-10">
			<div class="bold v-margin-4">Organization</div>
			<input name="organization" value="${experience.organization}" class="input-medium" />
		</div>
		<form:errors path="experience.organization" cssClass="error field-error" />
		<div class="v-margin-10">
			<div class="bold v-margin-4">Start Date</div>
			<div>
				<input name="startDate" value="<fmt:formatDate value="${experience.startDate }" pattern="MM/dd/yyyy"/>"
					class="date-picker input-medium" />
			</div>
		</div>
		<form:errors path="experience.startDate" cssClass="error field-error" />
		<div class="v-margin-10">
			<div class="bold v-margin-4">End Date</div>
			<div>
				<input name="endDate" value="<fmt:formatDate value="${experience.endDate }" pattern="MM/dd/yyyy"/>"
					class="date-picker input-medium" />
			</div>
		</div>
		<form:errors path="experience.endDate" cssClass="error field-error" />
		<div class="v-margin-10">
			<div class="bold v-margin-4">
				<span>Is present experience?</span>
				<form:checkbox path="experience.isPresent" value="${experience.isPresent}" cssClass="h-margin-3"/>	
			</div>	
		</div>
		<form:errors path="experience.isPresent" cssClass="error field-error" />
		<div class="v-margin-10">
			<div class="bold v-margin-4">Description</div>
			<div class="clearfix">
				<textarea name="description" class="input-medium float-left">${experience.description}</textarea>
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Tags</div>
			<div class="tag-editor"></div>
		</div>
		<div class="simple-list v-margin-10">
			<div class="bold v-margin-4">Details</div>
			<div class="clearfix">
				<textarea class="simple-list-input float-left"></textarea>
				<div class="add-item button-32 add-control-btn"></div>
			</div>
			<ul class="clearfix">
				<c:forEach var="detail" items="${experience.experienceDetails }">
					<li>
						<span>${detail.description}</span>
						<a href="<c:url value="/secure/resume/manager/experience/${detail.experienceDetailId}/deleteexperience"/>"
							class="small-delete-btn h-margin-3"></a>
						<input type="hidden" name="experienceDetails" value="${detail.experienceDetailId }" />
					</li>
				</c:forEach>
			</ul>
		</div>
		<div>
			<input type="submit" class="control" value="Save" /> 	
			<input id="experienceId" type="hidden" name="experienceId" value="${experience.experienceId}" />
		</div>
	</form>
</ttTags:documentTemplate>
