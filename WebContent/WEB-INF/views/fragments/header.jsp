<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="header" class="bg3">
	<div id="logo"></div>
	<div id="nav-menu" class="clearfix">
		<ul>
			<li class="font-large fg1"><a href="<c:url value="/blog/page/0"/>">Blog</a></li>
			<li class="font-large fg1"><a href="<c:url value="/about/"/>">About</a></li>
			<li class="font-large fg1"><a href="<c:url value="/resume/profile"/>" >Resume</a></li>
			<li id="login" class="font-large fg1"><a href="<c:url value="/login.jsp"/>">Admin</a></li>
		</ul>
	</div>
</div>
