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
<a href="/dash">Dash</a>
<a href="/create">Create</a>
<a href="/logout">Logout</a>
<a href="/show/1">Show</a>

<h1>Index test</h1>

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

<!-- ----------------------------------------------------- -->

<c:if test="${userid!= null}">

<form action="/createthread" method="post">
<h1>Create Thread</h1>
	<label >Categories</label><br> 
	<select name ="categories">
		<c:forEach items="${categoryOptions}" var ="cat">
			<option value="${cat}" label="${cat}"> 
		</c:forEach>
	</select><br>
	
	<label>Description</label><br>
	<textarea cols="30" rows="10" name = "description"></textarea><br>

	<label>Rating</label> <br>
	<input type=number max=10, min=0 name="rating" /><br>
	<button>Create</button>
</form>
<br>


</c:if>



</body>
</html>