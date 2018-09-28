<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no"
	charset="UTF-8">
<meta>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<!-- Begin of Navigation bar -->
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<a href="/" class="navbar-brand">JavaVid</a>
		<button class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarmenu">
			<span class="navbar-toggler-icon"> </span>
		</button>
		<div class="collapse navbar-collapse" id="navbarmenu">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="/" class="nav-link">
				</a></li>
				<li class="nav-item"><a href="/allthreads" class="nav-link">AllThreads</a></li>
				<li class="nav-item"><a href="/show/1" class="nav-link">Show</a></li>
		<c:if test="${userid== null}">				
				<li class="nav-item"><a href="/login" class="nav-link">Log In</a></li>
		</c:if>
				<li class="nav-item"><a href="/logout" class="nav-link">Log	Out</a></li>
			</ul>
		</div>
	</nav>
	<!-- End of Nav -->

<div class="container">

<h4> Thread index </h4>
<c:forEach items = "${allthreads}" var = "thread">
	<h4><a href="/show/${thread.getId()}">ID: ${thread.getId()} Title: ${thread.title}</a> - Videos: (${thread.getPosts().size()})</h4>
	<p>Categories: 
	<c:forEach items="${thread.getCategories()}" var = "cat">
		${cat.getCategory_name()} | Description ${thread.title}
	</c:forEach>
	  </p>	
</c:forEach>

</div>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>