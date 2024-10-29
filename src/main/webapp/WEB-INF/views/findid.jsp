<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
	<form action="<c:url value="/findid"/>" method="post">
	<h1>아이디 찾기</h1>
		
		<div class="mb-3 mt-3">
			<label for="name" class="form-label">이름</label>
			<input type="text" class="form-control" id="name" placeholder="이름을 입력하세요." name="name">
		</div>
		<div>
			<label for="email" class="form-label">이메일</label>
			<input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요." name="email">
		</div>
		
		<div class="mb-3 mt-3">
			<label for="phonenum" class="form-label">핸드폰번호</label>
			<input type="text" class="form-control" id="phonenum" placeholder="이메일을 입력하세요." name="phonenum">
		</div>
		
			<button class="btn btn-outline-success col-12">아이디 찾기</button>
	</form>	
</div>
</body>
</html>