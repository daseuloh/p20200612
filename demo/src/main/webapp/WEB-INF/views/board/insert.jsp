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
	<div class="container">
		<div style="width:600px; padding:30px; border:1px solid #cccccc">
			<form action="${pageContext.request.contextPath}/board/insert" method="post" enctype="multipart/form-data">
				<div style="margin-bottom:10p
				x">
					<input type="text" class="form-control" name="brd_title" placeholder="글제목" />
				</div>
				<div style="margin-bottom:10px">
					<textarea id="content" class="form-control" name="brd_content" placeholder="글내용"></textarea>
				</div>
				<div style="margin-bottom:10px">
					<input type="text" class="form-control" name="brd_id" value="${userid}" readonly />
				</div>
				<div style="margin-bottom:10px">
					<input type="file" class="form-control" name="imgs" />
				</div>	
				<hr />
				<input type="submit" class="btn btn-success" value="글쓰기 " />
			</form>
		</div>
	</div>
</body>
</html>

