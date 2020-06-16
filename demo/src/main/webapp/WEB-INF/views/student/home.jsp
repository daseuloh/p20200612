<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jpa실습</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
</head>

<body>
	<div class="container">
		<h4>jpa실습</h4>
		<hr />
		<a href="/student/insert">추가</a>
		<a href="/student/delete">삭제</a>
		<a href="/student/update">수정</a>
		<a href="/student/select">조회</a>

	</div>
</body>
</html>