<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<ttTags:documentTemplate cssFiles="post_tags.css,resume.css,list.css"
	jsFiles="tag_editor.js,list.js,${pageContext.request.contextPath}/resources/js/pages/manage_degree.js"
	sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">Manage Education</div>
	<form method="post" action="<c:url value="/secure/resume/manager/degree/save"/>">
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
			<div class="bold v-margin-10">
			    Is currently being obtained? 
				<form:checkbox path="degree.isPresent" value="${degree.isPresent}" cssClass="h-margin-3"/>
		    </div>
		</div>
		<div class="simple-list v-margin-10">
			<div class="bold v-margin-4">Details</div>
			<div class="clearfix">
				<textarea class="simple-list-input float-left"></textarea>
				<div class="add-item button-32 add-control-btn"></div>
			</div>
			<ul>
				<c:forEach var="detail" items="${degree.degreeDetails }">
					<li>
						<span>${detail.description}</span>
						<a href="<c:url value="/secure/resume/manager/degree/${detail.degreeDetailId}/deletedetail"/>"
							 class="small-delete-btn h-margin-3"></a>
						<input type="hidden" name="degreeDetails" value="${detail.degreeDetailId }" />
					</li>
				</c:forEach>
			</ul>
		</div>
		<input type="submit" class="control" value="Save" />
		<input id="degreeId" type="hidden" name="degreeId" value="${degree.degreeId}"/>
	</form>
</ttTags:documentTemplate>

