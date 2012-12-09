<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<ttTags:documentTemplate
	cssFiles="post_tags.css,resume.css,list.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">Education</div>
	<c:forEach var="degree" items="${degrees}">
		<div class="v-bottom-margin-20 clearfix">
			<div class="clearfix">
				<div class="float-left">
					<div class="font-medium bold">${degree.institution }</div>
					<div>${degree.program }</div>
					<div>${degree.degreeType }</div>
				</div>
				<div class="float-right">
					<div class="text-right">
						<fmt:formatDate value="${degree.startDate}" pattern="MMMM yyyy" />
						-
						<c:choose>
							<c:when test="${degree.isPresent}">
								Present
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${degree.endDate}" pattern="MMMM yyyy" />
							</c:otherwise>
						</c:choose>
					</div>
					<div class="text-right">
						<span class="subtle">GPA </span>${degree.gpa}
					</div>
				</div>
			</div>
			<div class="simple-list">
				<ul>
					<li>${degree.emphasis } Emphasis</li>
					<c:forEach var="detail" items="${degree.degreeDetails }">
						<li>${detail.description}</li>
					</c:forEach>
				</ul>
			</div>
			<sec:authorize access="hasRole('ROLE_ADMIN')" >
				<div class="control-group clearfix">
					<a href="<c:url value="/secure/resume/manager/degree/edit/${degree.degreeId}" />"
						class="img edit-control"></a>
					<a href="<c:url value="/secure/resume/manager/degree/delete/${degree.degreeId}"  />"
						class="img delete-control"></a>
				</div>
			</sec:authorize>
		</div>
	</c:forEach>
</ttTags:documentTemplate>

