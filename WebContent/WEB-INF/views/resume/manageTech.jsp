<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate cssFiles="resume.css,rating.css"
	jsFiles="rating.js,${pageContext.request.contextPath}/resources/js/pages/manage_tech.js"
	sidebarFragment="resume_sidebar.jsp">

	<div class="v-bottom-margin-20 font-large bold">Manage Technical Expertise</div>
	<form method="post"
		action="${pageContext.request.contextPath}/resume/tech/save"
		enctype="multipart/form-data">
		<div class="v-margin-20">
			<div class="bold v-margin-4">Expertise Name</div>
			<div>
				<input name="title" value="" />
			</div>
		</div>
		<div class="v-margin-20">
			<div class="bold v-margin-4">Rating</div>
			<div id="rating"></div>
		</div>
		<div class="v-margin-20">
			<div class="bold v-margin-4">Image File</div>
			<div>
				<input type="file" name="file" />
			</div>
		</div>
		<input type="submit" class="control" value="Save" />
	</form>

</ttTags:documentTemplate>

