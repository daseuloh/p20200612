<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 글쓰기</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>
	<form action="/board/insert" method="post" enctype="multipart/form-data">
		<input type="text" name="brd_title" placeholder="글제목" />
		<textarea id="content" name="brd_content" placeholder="글내용"></textarea>
		<input type="text" name="brd_id" value="${userid}" readonly />
		<input type="file" name="imgs" />
		<input type="submit" value="글쓰기 " />
	</form>
</body>
</html>