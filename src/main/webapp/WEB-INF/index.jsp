<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<c:if test="${userid!=null}">
					Welcome, ${current_user.getUsername()}
				</c:if>
				</a></li>
				<li class="nav-item"><a href="/allthreads" class="nav-link">AllThreads</a></li>
				<li class="nav-item"><a href="/create" class="nav-link">Create</a></li>
				<li class="nav-item"><a href="/show/1" class="nav-link">Show</a></li>
				<li class="nav-item"><a href="/login" class="nav-link">Log In</a></li>
				<li class="nav-item"><a href="/logout" class="nav-link">Log	Out</a></li>
			</ul>
		</div>
	</nav>
	<!-- End of Nav -->

	<div class="container">
	<!-- Registration form -->

	<c:if test="${userid== null}">
		<br>
		<div class="shadow p-3 mb-5 bg-white rounded">
		<div class="row">
			<div class="col">
				<form:form action="/register" method="post" modelAttribute="user"
					class="main-form">

					<h1>Register</h1>
					<div class="form-group">
						<form:input placeholder=" Username" path="username"
							class="form-control" />
					</div>
					<div class="form-group">
						<form:input placeholder=" Password" path="password"
							class="form-control" type="password" />
					</div>
					<div class="form-group">
						<form:input placeholder=" Retype Password"
							path="passwordConfirmation" class="form-control" type="password" />
					</div>
					<button type="submit" class="btn btn-dark">Register</button>
				<p><form:errors path="user.*" /></p>
				</form:form>
			</div>


			<!-- Login Form -->

			<div class="col">
				<form action="/login" method="post" class="main-form">
					<h1>Login</h1>
					<div class="form-group">
						<input placeholder=" Username" name="username"
							value="${loginretry}" class="form-control" />
					</div>
					<div class="form-group">
						<input placeholder=" Password" name="password" type="password"
							class="form-control" />
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-dark">Login</button>
					</div>
					<p>
						<c:out value="${loginerror}" />
					</p>
				</form>
			</div>
		</div>
	<br><br>
	</div>
	</c:if>
	
	
	
	<!-----------------------  Create Thread form  -------------------------------- -->


	<c:if test="${userid!= null}">
		<h4 class="thread_topic">Create Thread</h4>
		<p>${error}</p>
		
		<div class="shadow-sm p-3 mb-5 bg-white rounded">
		<form:form action="/createthread" method="post"
			modelAttribute="thread">
			
			<div class="row">
			<div class="col">			
				<form:errors path="title"></form:errors>
				<form:input path="title" placeholder="Title" class="form-control"></form:input><br>
				<div class="form-group">
				<button type="submit" class="btn btn-dark">Create</button>
				</div>
			</div>
			<div class="col">
				<form:errors path="description"></form:errors>
				<form:textarea cols="30" rows="3" path="description" placeholder="Description" class="form-control"></form:textarea>
			</div>
			<div class="col">
				<form:errors path="categories" ></form:errors>
				<form:select path="categories" class="form-control">
					<c:forEach items="${categoryOptions}" var="cat">
						<form:option value="${cat.getId()}"
						label="${cat.getCategory_name()}" />
					</c:forEach>
				</form:select>
			</div>
			</div>
		</form:form>
		</div>
	<!------------------------------------ User Interest --------------------------------->
		<h4 class="thread_topic">User Interests</h4>
		<div class="shadow p-3 mb-5 bg-white rounded">
		<p>${current_user.getUsername()}, You have ${interests.size()} Interests</p>
		<p>Master Array: ${interests}</p>
		
		
		<c:if test="${Food.size() >0}">
			<h2>Food</h2>
			<c:forEach items="${Food}" var="vid">
				<div>
					<iframe width="426" height="240"
						src="https://www.youtube.com/embed/${vid.getV_id()}"
						frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
				</div>
			</c:forEach>
		</c:if>

		<c:if test="${Coding.size() >0}">
			<p>***************************************************************************</p>
			<h2>Coding</h2>
			<c:forEach items="${Coding}" var="vid">
				<div>
					<iframe width="426" height="240"
						src="https://www.youtube.com/embed/${vid.getV_id()}"
						frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
				</div>
			</c:forEach>
		</c:if>

		<c:if test="${Music.size() >0}">
			<p>***************************************************************************</p>
			<h2>Music</h2>
			<c:forEach items="${Music}" var="vid">
				<div>
					<iframe width="426" height="240"
						src="https://www.youtube.com/embed/${vid.getV_id()}"
						frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
				</div>
			</c:forEach>
		</c:if>

		<c:if test="${Coding.size() >0}">
			<p>***************************************************************************</p>
			<h2>Coding</h2>
			<c:forEach items="${Coding}" var="vid">
				<div>
					<iframe width="426" height="240"
						src="https://www.youtube.com/embed/${vid.getV_id()}"
						frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
				</div>
			</c:forEach>
		</c:if>





		<p>***************************************************************************</p>
		
		</div>
		
		<h1>Temporary form to add Categories to current user</h1>

		<form action="/addcategorytouser" method="post">
			<select name="categories">
				<c:forEach items="${categoryOptions}" var="cat2">
					<option value="${cat2.getId()}" label="${cat2.getCategory_name()}"></option>
				</c:forEach>
			</select>
			<button type="submit">Add</button>

		</form>






	</c:if>
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