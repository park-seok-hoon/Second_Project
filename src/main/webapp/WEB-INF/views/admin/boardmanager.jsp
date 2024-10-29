<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
<link href="/team5/css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="container custom-container">
		
		<h1>게시판 관리</h1>
		<div class="mb-3 mt-3 category_List">
			<label for="category" class="form-label">카테고리:</label>
			<select class="form-control form-select cm_num" name="category" id="category">
				<c:forEach items="${list}" var="category">
					<option value="${category.c_num}" selected>
						${category.c_name}
					</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="board-list">
			<label for="board" class="form-label">게시판:</label>
			<select class="form-control form-select bo_num" name="board" id="board">		
			</select>
			<a class="btn btn-outline-info  btn-board-add">추가</a>
			<a class="btn btn-outline-success  btn-board-update">수정</a>
			<a class="btn btn-outline-danger  btn-board-delete">삭제</a>
		</div>
</div>

<script type="text/javascript">
//====================DELETE=================================

$(document).on("click", ".btn-board-delete", function() {
	let bo_num = $(".bo_num").val();
	if(bo_num == null){
		alert("게시판을 선택해주세요")
		location.href= `<c:url value="/board/manager"/>`;
		return;
	}
	location.href = `<c:url value ="/board/delete?num=\${bo_num}" />`;
})
//================= UPDATE ===================================

$(document).on("click", ".btn-board-update", function(){
	let bnum = $(".bo_num").val();
	let cnum = $(".cm_num").val();
	if(bnum == null){
		alert("게시판을 선택해주세요")
		location.href= `<c:url value ="/board/manager"/>`;
		return;
	}
	location.href= `<c:url value = "/board/update?bnum=\${bnum}&cnum=\${cnum}"/>`;
})

//=========================INSDERT===============================
$(document).on("click", ".btn-board-add", function(){
	let cnum = $(".cm_num").val();
	var servletUrl = `<c:url value = "/board/insert?num=\${cnum}"/>`;
	//window.open(servletUrl, 
			//"soyoInfoPop" ,"height=500,width=500,top=100,left=200,scrollbars=yes,resizable=yes");
	location.href = servletUrl;
})

//================* 카테고리 선택후 게시판 출력 메서드 *======================/
	$('.cm_num').click(function(){
		$('.post-body').hide();
		let categoryNum = $(".cm_num").val();
		let search = $("[name=search]").val('');
		getBoardByCategory(categoryNum)
	})
	
	
	function getBoardByCategory(categoryNum){
		$.ajax({
			method : 'post',
			url : '<c:url value="/board/manager"/>',
			data : {
				categoryNum
			},
			success : function(data){
				let str ="";
				let board;
				for(board of data.boardList){
					str +=
						`
							<option value="\${board.b_num}" selected>
								\${board.b_name}
							</option>
						`
				}
				$('.bo_num').html(str)
			}
		})
	}
</script>
</body>
</html>