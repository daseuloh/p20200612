<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생등록</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet"/>
</head>

<body>
	<div class="container">
		<form action="/student/insert" method="post">
			<input type="text" name="id" placeholder="아이디"/><br />
			<input type="text" name="name" placeholder="이름"/><br />
			<input type="text" name="kor" placeholder="국어점수"/><br />
			<input type="text" name="eng" placeholder="영어점수"/><br />
			<input type="text" name="math" placeholder="수학점수"/><br />
			<input type="submit" value="등록"/>
			
		</form>
	
	</div>
</body>
</html>