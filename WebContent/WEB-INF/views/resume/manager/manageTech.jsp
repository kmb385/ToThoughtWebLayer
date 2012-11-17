<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,rating.css"
	jsFiles="tag_editor.js,rating.js,${pageContext.request.contextPath}/resources/js/pages/manage_tech.js"
	sidebarFragment="resume_sidebar.jsp" requiresTextEditor="true">
	<div class="v-bottom-margin-20 font-large bold">Manage Technical Expertise</div>
	<form method="post"
		action="${pageContext.request.contextPath}/resume/manager/tech/save"
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
			<div id="rating"></div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Image File</div>
			<div>
				<input type="file" name="file" size="35" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Tags</div>
			<div class="tag-editor"></div>
		</div>
		<input type="submit" class="control" value="Save" />
	</form>

</ttTags:documentTemplate>

