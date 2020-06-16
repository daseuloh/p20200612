<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생수정</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet"/>
</head>

<body>
	<div class="container">
		<form action="update" method="post">
			<input type="text" name="id" value="${obj.id}" readonly/><br />
			<input type="text" name="name" value="${obj.name}"/><br />
			<input type="text" name="kor" value="${obj.kor}"/><br />
			<input type="text" name="eng" value="${obj.eng}"/><br />
			<input type="text" name="math" value="${obj.math}"/><br />
			<input type="submit" value="수정"/>
		</form>
	</div>
</body>
</html>