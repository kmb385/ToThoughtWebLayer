<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,rating.css, skill.css"
	jsFiles="tag_editor.js,rating.js,${pageContext.request.contextPath}/resources/js/pages/manage_commit.js"
	sidebarFragment="resume_sidebar.jsp" requiresTextEditor="true">
	<div class="page-title">Manage Commit ${skillId}</div>

	<form method="post" action="<c:url value="/secure/resume/manager/commit/${commit.commitId}/save"/>">

		<div class="v-margin-10">
			<div class="bold v-margin-4"><span class="required">*</span>Title</div>
			<div>
				<input name="title" value="${commit.title}" class="input-medium" />
			</div>
		</div>
		<form:errors path="commit.title" cssClass="error field-error" />

		<div class="v-margin-10">
			<div class="bold v-margin-4"><span class="required">*</span>Link</div>
			<div>
				<input name="htmlUrl" value="${commit.htmlUrl}" class="input-medium" />
			</div>
		</div>
		<form:errors path="commit.htmlUrl" cssClass="error field-error" />

		<div class="v-margin-10">
			<div class="bold v-margin-4">Tags</div>
			<div class="tag-editor"></div>
		</div>

		<input type="submit" class="control" value="Save" />
		<input id="commitId" type="hidden" name="commitId" value="${commit.commitId}"/>
	</form>
</ttTags:documentTemplate>

