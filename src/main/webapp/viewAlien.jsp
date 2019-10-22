<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring View All</title>
</head>
<body>
	AGRWQG<br/>
	
	<ol>
		<c:forEach var="alien" items="${alienList}">
		
			<li>${alien.aid}, ${alien.aname}, ${alien.tech}</li>
			
		</c:forEach>
	</ol>
	
</body>
</html>