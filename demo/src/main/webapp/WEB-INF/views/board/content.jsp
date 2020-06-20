<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 내용</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"  rel="stylesheet"/>
</head>

<body>

	
	
	
	
	 	<div class="container">
		<%@ include file="/WEB-INF/views/menu.jsp" %>
		
		<%-- <input type="hidden" name="no" class="tdbrdno" value="${obj.brd_no}"/> --%>
		
		글번호 : ${obj.brd_no}<br />
		글제목 : ${obj.brd_title}<br />
		글내용 : ${fn:replace(obj.brd_content, newLineChar, "<br />")}<br />
		작성자 : ${obj.brd_id}<br />
		조회수 : ${obj.brd_hit}<br />
		날짜 : ${obj.brd_date}<br />
		<img src="${pageContext.request.contextPath}/board/getimg?no=${obj.brd_no}" width="100px" height="100px" /><br />
		<hr />
		<a href="${pageContext.request.contextPath}/board/list" class="btn btn-success">목록</a>
		<a href="${pageContext.request.contextPath}/board/update?no=${obj.brd_no}" class="btn btn-success">수정</a>
		<a href="#" class="btn btn-success mydeletebtn">삭제</a>
		
		<c:if test="${prev != 0}">
		<a href="${pageContext.request.contextPath}/board/content?no=${prev}" class="btn btn-success">이전글</a>
		</c:if>
		
		<c:if test="${next != 0}">
		<a href="${pageContext.request.contextPath}/board/content?no=${next}" class="btn btn-success">다음글</a>
		</c:if>
	</div> 
	
	
	
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script type="text/javascript">
		$(function(){ //jquery 라이브러리 사용 시작
			//alert('알림창 동작');
			//console.log('콘솔 확인');
			//위의 태그중에서 class="mydeletebtn"인 것을 찾음
			$('.mydeletebtn').click(function(){ //위에있는 태그중에서 .하고 뒤에는 찾고싶은 개체의 명칭
				
				var idx = $(this).index('.mydeletebtn');
				var no = "${obj.brd_no}";
				/* var no = $('.tdbrdno').val(); */
				Swal.fire({
					  title: '삭제확인',
					  text: "삭제하시겠습니까?",
					  icon: 'warning',
					  showCancelButton: true,
					  confirmButtonColor: '#3085d6',
					  cancelButtonColor: '#d33',
					  confirmButtonText: '확인',
					  cancelButtonText:'취소'
					}).then((result) => {
					  if (result.value) {
						window.location.href = "/board/delete?no=" + no;
						/* href로 보내는 거는 get방식이다 */
						
					  }
					})
				}); 

			
			}); //jquery라이브러리 사용 종료
		
	</script>
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>