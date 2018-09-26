<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<a href="/">Home</a>
<a href="/login">Login</a>
<a href="/dash">Dash</a>
<a href="/create">Create</a>
<a href="/logout">Logout</a>
<a href="/show/1">Show</a>

<h1>Show Thread</h1>
<form:form action="/show/${id}" method = "post" modelAttribute="post">
	<form:input path="comment"/>
	<form:input path="v_id"/>
	<form:input path = "post_owner" value="${current_user}"/> 
	<form:input path="thread" value="${current_thread}"/>
	

</form:form>



</body>
</html>