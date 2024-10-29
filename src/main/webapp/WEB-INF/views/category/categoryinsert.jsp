<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<meta charset="UTF-8">
<title>카테고리 추가</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="container custom-container">
		<h1>카테고리 추가</h1>
		<form action="<c:url value="/category/insert"/>" method="post">
			<div class="mb-3 mt-3 category_List">
				<label for="category" class="form-label">추가할 카테고리를 입력해주세요:</label>
				<input type="text" class="form-control" id="category" placeholder="추가할 카테고리명" name="category">
				<button class="btn btn-outline-danger" type="submit">등록</button>
			</div>
		</form>
	</div>
</body>
</html>