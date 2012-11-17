<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>

<ttTags:documentTemplate cssFiles="resume.css,rating.css"
	sidebarFragment="resume_sidebar.jsp">
	<div class="v-bottm-margin-20 clearfix">
		<div class="float-left">
			<div class="font-large bold">${skill.name}</div>
		</div>
	</div>
</ttTags:documentTemplate>

