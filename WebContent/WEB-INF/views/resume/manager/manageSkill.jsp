<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,rating.css, skill.css"
	jsFiles="tag_editor.js,rating.js,${pageContext.request.contextPath}/resources/js/pages/manage_tech.js"
	sidebarFragment="resume_sidebar.jsp" requiresTextEditor="true">
	<div class="page-title">Manage Technical Skills</div>
	<form method="post" action="<c:url value="/secure/resume/manager/skills/save"/>"
		enctype="multipart/form-data">
		<div class="v-margin-10">
			<div class="bold v-margin-4">Name</div>
			<div>
				<input name="name" value="${skill.name}" class="input-medium" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Skill Category</div>
			<form:select path="skill.skillCategory" cssClass="input-medium">
				<form:option value="0" label="" />
				<form:options items="${skillCategories}" itemValue="skillCategoryId"
					itemLabel="name"></form:options> />
			</form:select>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Provider</div>
			<div>
				<input name="provider" value="${skill.provider}"
					class="input-medium" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Link</div>
			<div>
				<input name="url" value="${skill.url}" class="input-medium" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Description</div>
			<div>
				<textarea name="description" class="input-medium">${skill.description}</textarea>
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Rating</div>
			<div id="rating">${skill.rating}</div>
		</div>
		<div class="v-margin-10">
			<c:choose>
				<c:when test="${empty skill.image}">
					<div class="bold v-margin-4">Image File</div>
					<div>
						<input type="file" name="file" size="35" value="${skill.image}"/>
					</div>
				</c:when>
				<c:otherwise>
					<div class="bold v-margin-4">Image File</div>
					<div class="delete-image-link clearfix">
						<div class="delete-text">${skill.image.name}</div>
						<div class="small-delete-btn h-margin-3"></div>
						<a href="<c:url value="/secure/resume/manager/skills/${skill.skillId}/deleteimage"/>">
							<span class="div-link">&nbsp;</span>
						</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Tags</div>
			<div class="tag-editor"></div>
		</div>
		<input type="submit" class="control" value="Save" />
		<input id="skillId" type="hidden" name="skillId" value="${skill.skillId}"/>
	</form>
</ttTags:documentTemplate>

