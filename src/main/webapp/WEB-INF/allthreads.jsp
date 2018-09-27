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
<a href="/allthreads">AllThreads</a>
<a href="/create">Create</a>
<a href="/logout">Logout</a>
<a href="/show/1">Show</a>

<h1> Thread index </h1>
<c:forEach items = "${allthreads}" var = "thread">
	<h3> Description : <a href="/show/${thread.getId()}">${thread.description}</a> - Videos: (${thread.getPosts().size()})</h3>
	<p>Categories: 
	<c:forEach items="${thread.getCategories()}" var = "cat">
		${cat.getCategory_name()} | 
	</c:forEach>
	  </p>	
</c:forEach>

</body>
</html>