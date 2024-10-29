<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<meta charset="UTF-8">
<title>게시판</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js">
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="post-list">
			<h3>전체 게시글 조회</h3>
			<table class="table table-dark table-striped">
			    <thead>
			      <tr>
			        <th>번호</th>
			        <th>제목</th>
			        <th>날짜</th>
			        <th>조회수</th>
			        <th>아이디</th>
			      </tr>
			    </thead>
			    <tbody>
			    	<c:forEach var="list1" items="${postlist}" varStatus="status">
						<tr>	
							<td>${list1.p_num}</td>
							<td>
								<a href="<c:url value=""/>">${list1.p_title}</a>
							</td>
							<td>
								<a href="<c:url value=""/>">${Datelist[status.index]}</a>
							</td>
							<td>${list1.p_view}</td>
							<td>${list1.p_mb_id}</td>
						</tr>
					</c:forEach>
			    </tbody>
			  </table>
		</div>
</body>
</html>