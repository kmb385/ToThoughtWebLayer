<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="ttTags" uri="/WEB-INF/tags/tothought-tags.tld"%>
<ttTags:documentTemplate cssFiles="post.css,post_tags.css,resume.css"
	requiresTextEditor="true" sidebarFragment="resume_sidebar.jsp">
	<div class="page-title">About toThought</div>
	<p class="v-margin-text-block">toThought is a blog and online
		profile I created to share my coding adventures. The site functions as
		an outlet to share my programming experiences with others, as well as
		documentation of my work. This site also grants me the oppurtunity to
		reflect upon different programming concepts and practices.</p>

	<p class="v-margin-text-block">
		toThought also serves as a test bed for new ideas and the exploration
		of new technologies. The code behind toThought is available for review
		via GitHub in two repositories, <a
			href="https://github.com/kmb385/ToThoughtDataLayer" target="_blank">ToThoughtDataLayer</a>
		and <a href="https://github.com/kmb385/ToThoughtWebLayer"
			target="_blank">ToThoughtWebLayer</a>. I invite you to fork the code
		for review or development. Any feedback provided is greatly
		appreciated.
	</p>

	<p class="v-margin-text-block">Another activity I enjoy is solving coding issues
		on StackOverflow. Checkout my profile.</p>
	<a href="http://stackoverflow.com/users/714969/kevin-bowersox"> <img
		src="http://stackoverflow.com/users/flair/714969.png" width="208"
		height="58"
		alt="profile for Kevin Bowersox at Stack Overflow, Q&amp;A for professional and enthusiast programmers"
		title="profile for Kevin Bowersox at Stack Overflow, Q&amp;A for professional and enthusiast programmers">
	</a>

</ttTags:documentTemplate>