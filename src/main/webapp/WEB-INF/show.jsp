<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
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
<style>
.video{
	display: inline-block;
}
</style>
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


<div class="row">

<!-- Left Column -->
<div class="col">
<div class="form-group"></div>
<div class="form-group">
	<h4>Topic ${current_thread.getTitle()}</h4>
</div>
<b>Created by:</b> ${current_thread.getCreator().getUsername()}<br>
<b>Description:</b> ${current_thread.getDescription() }<br>
<b>Published Date:</b> <fmt:formatDate value="${current_thread.getCreatedAt()}" pattern="MM-dd-yyyy," />
<b>Time:</b> <fmt:formatDate value="${current_thread.getCreatedAt()}" pattern="HH:mm:ss a" /><br>
<b>Average User Rating:</b> ${post.getPersonal_rating()}
<p><b>Categories Tag#:</b> 
	<c:forEach items="${current_thread.getCategories()}" var= "cat">
		${cat.getCategory_name()} , 
	</c:forEach>

<p></p>
</div>

<div class="col">
<!-- Form is on the Right Column -->

<form:form action="/show/${threadid}" method = "post" modelAttribute="post">
	<form:hidden path= "post_owner" value="${current_user.getId()}" /><br>
	<form:hidden path= "thread" value="${current_thread.getId()}" /><br>

	
	<div class="row">
	
		<%-- <div class="col">
			<label for="basic-url">-- Add Your Video Here --</label>
		</div>
		<div class="form-group">
			<div class="col">
				<form:input path="personal_rating"  class="form-control" min="1" type="number" placeholder="What do you rate this 1-10?"/>
			</div>
		</div> --%>
		<div class="col">
		<div class="input-group mb-3">
  			<div class="input-group-prepend">
    			<label class="input-group-text" for="inputGroupSelect01">-- Add Your Favorite Video Here --</label>
  			</div>
  			<form:select class="custom-select" id="inputGroupSelect01" path="personal_rating">
			    <form:option value="">Rating(1-10)</form:option>
			    <form:option value="1">1</form:option>
			    <form:option value="2">2</form:option>
			    <form:option value="3">3</form:option>
			    <form:option value="4">4</form:option>
			    <form:option value="5">5</form:option>
			    <form:option value="6">6</form:option>
			    <form:option value="7">7</form:option>
			    <form:option value="8">8</form:option>
			    <form:option value="9">9</form:option>
			    <form:option value="10">10</form:option>
			</form:select>
		</div>
		</div>
		
	</div>
	
	<div class="input-group mb-3">
  		<div class="input-group-prepend">
    		<span class="input-group-text" id="basic-addon3">https://www.youtube.com/watch?v=</span>
  		</div>
  		<form:input path="v_id" type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3" />
	</div>
	<form:input path="comment" placeholder="Comment" class="form-control"/><br>
	<button type="submit" class="btn btn-dark">Create post</button>
	<p>${existserror}</p>
</form:form>

</div>
</div>

<%-- <c:if test="${current_thread.getUpdatedAt()!=null}">
	<p>Last Updated: ${current_thread.getUpdatedAt()}</p>
</c:if> --%>


<!-- POSTS -->
<c:forEach items="${current_thread.getPosts()}" var ="post">
		
	
		<iframe class="embed-responsive-item" width="300" height="250" src="https://www.youtube.com/embed/${post.getV_id()}" 
		frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
			
</c:forEach>
Comments: ${post.getComment()}

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
</div>
</body>
</html>