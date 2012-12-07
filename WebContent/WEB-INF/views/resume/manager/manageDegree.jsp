<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css, manage_degree.css"
	jsFiles="tag_editor.js,${pageContext.request.contextPath}/resources/js/pages/manage_degree.js"
	sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottom-margin-20 font-large bold">Manage Education</div>
	<form method="post" action="${pageContext.request.contextPath}/resume/manager/degree/save">
		<div class="v-margin-10">
			<div class="bold v-margin-4">Institution</div>
			<input name="institution" value="${degree.institution}" class="input-medium" />
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Program</div>
			<input name="program" value="${degree.program}" class="input-medium" />
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Emphasis</div>
			<input name="emphasis" value="${degree.emphasis}" class="input-medium" />
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">GPA</div>
			<input name="gpa" value="${degree.gpa}" class="input-medium" />
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Start Date</div>
			<div>
				<input name="startDate" value="<fmt:formatDate value="${degree.startDate }" pattern="MM/dd/yyyy"/>" 
					class="date-picker input-medium" />
			</div>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">End Date</div>
			<div>
				<input name="endDate" value="<fmt:formatDate value="${degree.endDate }" pattern="MM/dd/yyyy"/>" 
					class="date-picker input-medium" />
			</div>
			<div class="bold v-margin-4">Is currently being obtained?</div>
			<form:checkbox path="degree.isPresent" value="${degree.isPresent}"/>
		</div>
		<div class="v-margin-10">
			<div class="bold v-margin-4">Details</div>
			<div class="clearfix">
				<textarea id="details-input" class="float-left"></textarea>
				<div id="add-detail" class="no-text-control new-control float-left"></div>
			</div>
			<div class="experience v-margin-20">
				<ul id="details">
					<c:forEach var="detail" items="${degree.degreeDetails }">
							<li>
								<div class="float-left">${detail.description}</div>
								<div class="small-delete-btn h-margin-3 float-left">
									<a href="<c:url value="/resume/manager/degree/${detail.degreeDetailId}/deletedetail"/>">
										<span class="div-link">&nbsp;</span>
									</a>
								</div>	
								<input type="hidden" name="degreeDetails" value="${detail.degreeDetailId }"/>
							</li>
					</c:forEach>
				</ul>
			</div>		
		</div>
		<input type="submit" class="control" value="Save" />
		<input id="degreeId" type="hidden" name="degreeId" value="${degree.degreeId}"/>
	</form>

</ttTags:documentTemplate>

