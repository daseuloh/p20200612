<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 수정</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet"/>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>	
</head>
<body>

<!-- 
<form:form action="${pageContext.request.contextPath}/board/update" method="post" modelAttribute="vo">
			<form:input type="text" class="form-control" path="brd_no" readonly="true"/>
			<form:input type="text" class="form-control" path="brd_title" />
			<form:textarea id="content" class="form-control" path="brd_content"></form:textarea>
			<form:input type="text" class="form-control" path="brd_id" readonly="true"/>
			
			 
			<hr />
			<form:select items="${slist}" path="tmp"/> <br />
			<form:checkboxes items="${slist}" path="tmp"/> <br />
			<form:radiobuttons items="${slist}" path=""/>
			
		
			<hr />
			<input type="submit" class="btn btn-success" value="글수정 " />
			<a href="${pageContext.request.contextPath}/board/list" class="btn btn-success">글목록</a>
		</form:form>
		 -->
	


 
 
 	<div class="container">


		<form action="${pageContext.request.contextPath}/board/update" method="post" enctype="multipart/form-data">
			<input type="text" class="form-control" name="brd_no" value="${vo.brd_no}" readonly />
			<input type="text" class="form-control" name="brd_title" value="${vo.brd_title}" />
			<textarea id="content" class="form-control" name="brd_content">${vo.brd_content}</textarea>
			<input type="text" class="form-control" name="brd_id" value="${vo.brd_id}" readonly />
			<img style="cursor:pointer;" id="imgpreview" src="${pageContext.request.contextPath}/board/getimg?no=${vo.brd_no}" width="100px" height="100px" />
			<input type="file" name="img" id="img" style="display:none;"/>
			<hr />
			<input type="submit" class="btn btn-success" value="글수정 " />
			<a href="${pageContext.request.contextPath}/board/list" class="btn btn-success">글목록</a>
		</form>
	</div>
	
	<script>
		$(function(){ //jQuery 문법 상용

			$('#imgpreview').click(function(){
				$('#img').click();
			})
			
			//태그중에서 id="img" 인것을 찾아 변경이 되면 반응
			$('#img').on('change', function(){
				var img = this; //읽은 이미지를 img변수에 보관함
				if(img.files && img.files[0]) { //img변수에 이미지가 있다면
					var reader = new FileReader();
					reader.onload = function(e){
						//이미지를 읽어서 id="imgpreview" 인곳의 src를 읽은 이미지로 변경
						$('#imgpreview').attr('src', e.target.result);
					}
					reader.readAsDataURL(img.files[0]);
				}
			});

	
			
		}) //jquer문법 종료
	</script>
</body>
</html>