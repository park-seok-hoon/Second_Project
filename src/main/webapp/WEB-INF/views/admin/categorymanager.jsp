<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리</title>
<link href="/team5/css/styles.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
 <style type="text/css">

 </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container custom-container">
		<h1>카테고리 조회</h1>
		
		<div class="mb-3 mt-3 category_List">
			<label for="category" class="form-label">카테고리:</label>
			<select class="form-control form-select cm_num" name="category" id="category">
				<c:forEach items="${list}" var="category">
					<option value="${category.c_num}" selected>
						${category.c_name}
					</option>
				</c:forEach>
			</select>
			<a class="btn btn-outline-info btn-category-add" >추가</a>
			<a class="btn btn-outline-success btn-category-update">수정</a>
			<a class="btn btn-outline-danger btn-category-delete">삭제</a>
		</div>
</div>


<script type="text/javascript">

//====================DELETE=================================
$(document).on("click", ".btn-category-delete", function () {
	let cnum = $(".cm_num").val();

	//location.href = `<c:url value="/category/delete?num=\${cnum}"/>`
	location.href = `<c:url value = "/category/delete?cnum=\${cnum}"/>`;
})

//================= UPDATE ===================================
$(document).on("click", ".btn-category-update", function(){
	let cnum = $(".cm_num").val();
	location.href= `<c:url value="/category/update?num=\${cnum}"/>`
})

//=========================INSDERT=============================
$(document).on("click", ".btn-category-add", function(){
	location.href= `<c:url value='/category/insert'/>`;
})
</script>
</body>
</html>