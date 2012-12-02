<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate
	cssFiles="post_tags.css,resume.css,degrees.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottom-margin-20 clearfix">
		<div class="float-left">
			<div class="font-large bold">Education</div>
		</div>
	</div>
	<c:forEach var="degree" items="${degrees}">
  	<div class="degree v-bottom-margin-20">
			<div class="clearfix">
				<div class="float-left">			
					<div class="font-medium bold float-left">
						<a href="<c:url value="/resume/manager/degree/edit/${degree.degreeId}"  />" 
							class="degree-title">${degree.institution }</a>
					</div>
					<div>${degree.program }</div>
					<div>${degree.degreeType }</div>
					<ul>
						<li>${degree.emphasis } Emphasis</li>
					</ul>
				</div>
				<div class="float-right">
					<div class="text-right">
						<fmt:formatDate value="${degree.startDate}" pattern="MMMM yyyy" /> -
						<c:choose>
							<c:when test="${degree.isPresent}">
								Present
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${degree.endDate}" pattern="MMMM yyyy" />
							</c:otherwise>
						</c:choose>					
					</div>
					<div class="text-right"><span class="subtle">GPA </span>${degree.gpa}</div>	
				</div>
			</div>
		</div>
	</c:forEach>
</ttTags:documentTemplate>

