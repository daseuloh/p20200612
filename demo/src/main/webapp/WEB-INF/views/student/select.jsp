<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>학생목록</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>
	<div class="container"> 
		<table class="table table-sm">
			<thead>
				<tr>	
					<th>아이디</th>
					<th>이름</th>
					<th>국어점수</th>
					<th>영어점수</th>
					<th>수학점수</th>
					<th>등록일</th>
					<th>버튼</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.id}</td>
					<td>${tmp.name}</td>
					<td>${tmp.kor}</td>
					<td>${tmp.eng}</td>
					<td>${tmp.math}</td>
					<td>${tmp.date}</td>
					<td>
						<form action="/student/delete" method ="post">
							<input type="hidden" name="id" value="${tmp.id}" />
							<input type="submit"  value="삭제" />
						</form>
						
						<a href="/student/update?id=${tmp.id}">수정</a>
					
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>