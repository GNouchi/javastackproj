<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<a href="/">Home</a>
<a href="/login">Login</a>
<a href="/allthreads">AllThreads</a>
<a href="/create">Create</a>
<a href="/logout">Logout</a>
<a href="/show/1">Show</a>

<h1>Index test hello, ${current_user.getUsername()}</h1>

<c:if test="${userid== null}">


<form:form action="/register" method = "post"  modelAttribute="user">

	<h1>Register</h1>
	<form:input placeholder=" Username" path = "username"/> <br>
	<form:input placeholder=" Password" path = "password"/><br>
	<form:input placeholder=" Retype Password" path ="passwordConfirmation"/><br>
	<button type ="submit"> Register</button>

</form:form>

<br>
<p>***************************************************************************</p>

<form action="/login" method = "post">
	
	<h1>Login</h1>
    <p><c:out value="${loginerror}" /></p>	
	<input placeholder=" Username" name = "username" value="${loginretry}"/> <br>
	<input placeholder=" Password" name = "password"/><br>
	<button type ="submit"> Login</button>

</form>
</c:if>
<!-- ---------------------  Create Thread form  -------------------------------- -->

<c:if test="${userid!= null}">

	<p>${error}</p>

<form:form action="/createthread" method="post" modelAttribute="thread">
<h1>Create Thread</h1>
	<form:errors path="categories"></form:errors>
	<label >Categories</label><br> 
	<form:select path="categories" >
		<c:forEach items="${categoryOptions}" var ="cat">
			<form:option value="${cat.getId()}" label="${cat.getCategory_name()}"/> 
		</c:forEach>
	</form:select><br>
	
	<form:errors path="description"></form:errors>
	<label>Description</label><br>
	<form:textarea cols="30" rows="10" path="description"></form:textarea><br>
	
	<button type ="submit">Create</button>
</form:form>
<br>
<p>***************************************************************************</p>
<h1>User Interests</h1>
<p>User has ${interests.size()} Interests</p>
<p>Master Array: ${prize}</p>
<c:forEach items="${prize}" var ="vid">
<div>
	<iframe width="426" height="240" src="https://www.youtube.com/embed/${vid}" 
	frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
</div>

</c:forEach>
<p>***************************************************************************</p>

<h1> Index </h1>
<c:forEach items="${categoryOptions}" var="cat">
	<h3>Category name ${cat.getCategory_name()}  |Number of threads( ${cat.getThreads().size()} )</h3>
		<c:forEach items="${cat.getThreads()}" var ="thread">
		 <a href="/show/${thread.getId()}"> ${thread.getDescription()}</a> [${thread.getPosts().size()}]
		 <c:if test="${thread.getPosts().size()>0}">
		<iframe width="426" height="240" src="https://www.youtube.com/embed/${thread.getPosts().get(0).getV_id()}" 
		frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>		 
		 </c:if>
		</c:forEach>
</c:forEach>
<p>***************************************************************************</p>

<h1>Temporary form to add Categories to current user</h1>

<form action="/addcategorytouser" method ="post">
	<select name= "categories">
		<c:forEach items="${categoryOptions}" var="cat2">
				<option value ="${cat2.getId()}" label= "${cat2.getCategory_name()}" ></option>
		</c:forEach>
	</select>
	<button type ="submit">Add</button>

</form>






</c:if>



</body>
</html>