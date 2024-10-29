<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<meta charset="UTF-8">
<title>게시판 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container custom-container">
			<h1>게시판 수정</h1>
 			<form action="<c:url value="/board/update"/>" method="post">
			<label for="category" class="form-label">바꿀 카테고리 명(필수):</label>
			
				<select class="form-control form-select cm_num" name="category" id="category">
					<c:forEach items="${list}" var="category">
						<option value="${category.c_num}" selected>
							${category.c_name}
						</option>
					</c:forEach>
				</select>
				
			<label for="new_board" class="form-label">기존 카테고리의 게시판 명:</label>
				<input type="text" class="form-control" id="category" value="${ori_category.b_name}" name="category" readonly>
				<div class="mb-3 mt-3 category_List">
					<label for="new_board" class="form-label">새로운 게시판 명:</label>
					<input type="text" class="form-control" id="new_board" name="new_board">
				</div>
				<button type="submit" class="btn btn-outline-danger btn-update" >수정하기</button>
				
			 </form> 
</div>

</body>

<script type="text/javascript">
$('.btn-update').click(function () {
	let c_name = getCategoryName();
	var result = window.confirm("선택하신 카테고리는 " + c_name + "입니다. \n" + "해당 카테고리로 게시판을 수정합니다.");
 	if (result) {
 		return true;
	} else {
		return false;
	}
});

function getCategoryName() {
    var selectElement = document.getElementById("category"); // select 요소 가져오기
    var selectedOption = selectElement.options[selectElement.selectedIndex]; // 선택된 옵션 가져오기
    var categoryName = selectedOption.text; // 선택된 옵션의 텍스트 값 가져오기
    return categoryName;
}
</script>
</html>