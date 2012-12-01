<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css, manage_experience.css"
	jsFiles="tag_editor.js,${pageContext.request.contextPath}/resources/js/pages/manage_experience.js"
	sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottom-margin-20 font-large bold">Manage Work Experience</div>
	<form method="post" action="${pageContext.request.contextPath}/resume/manager/experience/save"
		enctype="multipart/form-data">
		<div class="v-margin-10">
			<div class="bold v-margin-4">Position</div>
			<div>
				<input name="position" value="${experience.position}" class="input-medium" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Organization</div>
			<input name="organization" value="${experience.organization}" class="input-medium" />
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Start Date</div>
			<div>
				<input name="startDate" value="<fmt:formatDate value="${experience.startDate }" pattern="MM/dd/yyyy"/>" 
					class="date-picker input-medium" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">End Date</div>
			<div>
				<input name="endDate" value="<fmt:formatDate value="${experience.endDate }" pattern="MM/dd/yyyy"/>" 
					class="date-picker input-medium" />
			</div>
			<div class="bold v-margin-4">Is present experience?</div><form:checkbox path="experience.isPresent" value="${experience.isPresent}"/>
		</div>
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
		<div class="v-margin-10">
			<div class="bold v-margin-4">Details</div>
			<div class="clearfix">
				<textarea id="details-input" class="float-left"></textarea>
				<div id="add-detail" class="no-text-control new-control float-left"></div>
			</div>
			<div class="experience v-margin-20">
				<ul id="details">
					<c:forEach var="detail" items="${experience.experienceDetails }">
						<ul id="details">
							<li>
								<div class="float-left">${detail.description}</div>
								<div class="small-delete-btn h-margin-3 float-left">
									<a href="<c:url value="/resume/manager/experience/${detail.experienceDetailId}/deleteexperience"/>">
										<span class="div-link">&nbsp;</span>
									</a>
								</div>	
								<input type="hidden" name="experienceDetails" value="${detail.experienceDetailId }"/>
							</li>
						</ul>
					</c:forEach>
				</ul>
			</div>
		</div>
		<input type="submit" class="control" value="Save" />
		<input id="experienceId" type="hidden" name="experienceId" value="${experience.experienceId}"/>
	</form>

</ttTags:documentTemplate>

