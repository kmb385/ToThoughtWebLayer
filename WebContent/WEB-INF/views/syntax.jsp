<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<jsp:include page="fragments/text_editor_files.jsp"/>
	<script>
	$(document).ready(function(){
		var myTinyMce = new MyTinyMce("myTextArea");
		myTinyMce.load();		
	});
	</script>
</head>
<body>
	<div style="width:500px; margin: 20px auto">
		<textarea id="myTextArea" name="content"></textarea>
	</div>
</body>
</html>