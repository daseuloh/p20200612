<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>제품 목록</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>
	<div class="container">
	
	<form action="/admin/item" method="post">
		<a href="/admin/iteminsert" class="btn btn-success">일괄추가</a>
		<input type="submit" name="btn" class="btn btn-success" value="일괄수정" />
		<input type="submit" name="btn" class="btn btn-success" value="일괄삭제" />
		
			<table class="table table-sm">
				<thead>
					<tr>
					<th><input type="checkbox" /></th>
						<th>번호</th>
						<th>품명</th>
						<th>가격</th>
						<th>재고수량</th>
						<th>내용</th>
						<th>날짜</th>
						<th>비고</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="tmp" items="${list}">
					<tr>
					<td><input type="checkbox" name="chk[]" value="${tmp.itemno}" /></td>
						<td class="tditemno">${tmp.itemno}</td>
						<td>${tmp.itemname}</td>
						<td>${tmp.itemprice}</td>
						<td>${tmp.itemqty}</td>
						<td>${tmp.itemdes}</td>
						<td>${tmp.itemdate}</td>
						<td>
							<a href="#" class="btn btn-sm btn-danger mydeletebtn">삭제</a>
							<a href="#" class="btn btn-sm btn-primary myupdatebtn">수정</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	
	
	
	<div class="modal" tabindex="-1" role="dialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">수정</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      
	      <div class="modal-body">
	        <input type="text" class="form-control" placeholder="물품번호"/>
	        <input type="text" class="form-control" placeholder="물품명"/>
	        <input type="text" class="form-control" placeholder="가격"/>
	        <input type="text" class="form-control" placeholder="재고수량"/>
	        <textarea rows="6" class="form-control" placeholder="내용"></textarea>
	      </div>
	      
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary">수정</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
	      </div>
	      
	    </div>
	  </div>
	</div>
	
	
	
	
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script type="text/javascript">
		$(function(){ //jquery 라이브러리 사용 시작
			//alert('알림창 동작');
			//console.log('콘솔 확인');

			
			//수정 버튼이 클릭되면
			$('.myupdatebtn').click(function(){
				
				$('.modal').modal('show'); //클래스가 modal인걸 찾아서 보여줘라
				})
			
			
			//위의 태그중에서 class="mydeletebtn"인 것을 찾음
			$('.mydeletebtn').click(function(){ //위에있는 태그중에서 .하고 뒤에는 찾고싶은 개체의 명칭
				
				var idx = $(this).index('.mydeletebtn');
				var no = $('.tditemno').eq(idx).text();

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
						window.location.href = "/admin/itemdeleteone?no=" + no;
					  }
					})
				});
			
			}); //jquery라이브러리 사용 종료
		
	</script>

</body>
</html>