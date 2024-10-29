<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<meta charset="UTF-8">
<title>카테고리 수정</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container custom-container">
			<h1>카테고리 수정</h1>
			<form action='<c:url value="/category/update"/>' method="post">
				<h3>기존 카테고리</h3>
				<input type="text" class="form-control" id="category" value="${ori_category.c_name}" name="category" readonly>
				<div class="mb-3 mt-3 category_List">
					<label for="category" class="form-label">카테고리:</label>
					<input type="text" class="form-control" id="new_category" name="new_category">
				</div>
				<button type="submit" class="btn btn-outline-danger" >수정하기</button>
				
			</form>
</div>

</body>
</html>