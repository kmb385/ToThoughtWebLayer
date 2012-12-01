<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate
	cssFiles="post_tags.css, resume.css, experience.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottom-margin-20 clearfix">
		<div class="float-left">
			<div class="font-large bold">Work Experience</div>
		</div>
	</div>
	<c:forEach var="experience" items="${experiences}">
		<div class="experience v-bottom-margin-20">
			<div class="clearfix">
			
				<div class="font-medium bold float-left"><a href="<c:url value="/resume/manager/experience/edit/${experience.experienceId}"  />">${experience.position }</a></div>
				<div class="float-right">
					<fmt:formatDate value="${experience.startDate}" pattern="MMMM yyyy" /> -
					<c:choose>
						<c:when test="${experience.isPresent}">
							Present
						</c:when>
						<c:otherwise>
							<fmt:formatDate value="${experience.endDate}" pattern="MMMM yyyy" />
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="subtle">${experience.organization }</div>
			<p class="v-margin-10">${experience.description }</p>
			<ul>
				<c:forEach var="detail" items="${experience.experienceDetails }">
					<li>${detail.description }</li>
				</c:forEach>
			</ul>
			<div class="post-tags clearfix">
				<c:forEach var="tag" items="${experience.tags}">
					<div class="post-tag">${tag.name}</div>
				</c:forEach>
			</div>
		</div>
	</c:forEach>
	<div class="experience v-bottom-margin-20">
		<div class="clearfix">
			<div class="font-medium bold float-left">Web Application
				Developer</div>
			<div class="float-right">July 2009 - Present</div>
		</div>
		<div class="subtle">NAVSUP Business Systems Center</div>
		<p class="v-margin-10">Developed J2EE applications that supported
			logistics and supply chain operations for the United States Navy and
			Department of Defense. Introduced new application development
			approaches such as object relational mapping, interactive ajax user
			interfaces and the front-controller pattern.</p>
		<ul>
			<li>Lead a team of three J2EE developers, raising competencies
				in OOP design principles and MVC pattern. Created a reusable
				application architecture adopted by several internal development
				teams.</li>
			<li>Managed team tasks, release deliverables and progress;
				keeping developers engaged with challenging tasks and learning
				opportunities through paired programming.</li>
			<li>Developed and maintained six other J2EE applications;
				working initiatives in parallel. Balancing the workload and needs of
				each customer, business analyst team and manager. Applications
				support business processes such as supply discrepancies, contract
				management, hazardous material cataloging, mail delivery and RFID
				enhanced supply management.</li>
		</ul>
		<div class="post-tags clearfix">
			<div class="post-tag">Java</div>
			<div class="post-tag">J2EE</div>
			<div class="post-tag">HTML</div>
			<div class="post-tag">CSS</div>
			<div class="post-tag">Hibernate</div>
			<div class="post-tag">Spring</div>
			<div class="post-tag">Jquery</div>
			<div class="post-tag">Jquery UI</div>
			<div class="post-tag">Tomcat</div>
			<div class="post-tag">Oracle</div>
			<div class="post-tag">SSL</div>
		</div>
	</div>
</ttTags:documentTemplate>

