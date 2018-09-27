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
<h1>Show Thread ${current_thread.getCreator().getUsername() }</h1>

<p>Categories: 
	<c:forEach items="${current_thread.getCategories()}" var= "cat">
		${cat.getCategory_name()} , 
	</c:forEach>
</p>
<p>OP: ${current_thread.getCreator().getUsername() }</p>
<p>Description: ${current_thread.getDescription() }</p>
<p>Current Rating: ${current_thread.getRating() }</p>
<p>Created: ${current_thread.getCreatedAt()}</p>

<c:if test="${current_thread.getUpdatedAt()!=null}">
	<p>Last Updated: ${current_thread.getUpdatedAt()}</p>
</c:if>

<!-- POSTS -->
<c:forEach items="${current_thread.getPosts()}" var ="post">
	<p>Coments: ${post.getComment()} </p>
	<p>User Rating : ${post.getPersonal_rating()}</p>	
	<iframe width="854" height="480" src="https://www.youtube.com/embed/${post.getV_id()}" 
	frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
</c:forEach>

<!-- form  -->
<p>${existserror}</p>
<form:form action="/show/${threadid}" method = "post" modelAttribute="post">
	<form:hidden path= "post_owner" value="${current_user.getId()}" /><br>
	<form:hidden path= "thread" value="${current_thread.getId()}" /><br>

	<p> *** this should be hideable ****
		If your link is : https://www.youtube.com/watch?v=0jHOCc5VWE8&list=PLATy4A7kRga0DXH9bXIFqZHLN8fq40ZvS&index=37
		<br>The videoID is the gibberish between "watch?v=" and "&"
		<br>VideoID: 0jHOCc5VWE8
	</p> 
	<form:input path="v_id" placeholder="Add the youtube videoID"/>
	<form:input path="personal_rating"  min="1" type="number" placeholder="What do you rate this 1-10?"/><br>
	<form:input path="comment" placeholder="Comment (max=300)"/><br>
	<button type="submit">Create post</button>

</form:form>





</body>
</html>