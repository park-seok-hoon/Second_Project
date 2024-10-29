<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
	<h1>내가 쓴 글</h1>
	<hr>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>[번호]</th>
				<th>[게시판]</th>
				<th>[제목]</th>
				<th>[작성자]</th>
				<th>[조회수]</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList}" var="PostVO">
				<tr>
					<td>${PostVO.p_num}</td>
					<td>${PostVO.p_b_num}</td>
					<td>
						<a href="<c:url value="/post/detail?num=${PostVO.p_num}"/>">${PostVO.p_title}</a>
					</td>
					<td>
						${PostVO.p_mb_id}
					</td>
					<td>${PostVO.p_view}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination justify-content-center">
		<c:if test="${pm.prev}">
			<li class="page-item">
				<c:url var="prevUrl" value="/myPost">
					<c:param name="type" value="${pm.cri.type}" />
					<c:param name="search" value="${pm.cri.search}" />
					<c:param name="page" value="${pm.startPage-1}" />
				</c:url>
				<a class="page-link" href="${prevUrl}">이전</a>
			</li>
		</c:if>
		<c:forEach begin="${pm.startPage}" end="${pm.endPage }" var="i">
			<li class="page-item <c:if test="${pm.cri.page == i }">active</c:if>">
				<c:url var="page" value="/myPost">
					<c:param name="type" value="${pm.cri.type}" />
					<c:param name="search" value="${pm.cri.search}" />
					<c:param name="page" value="${i}" />
				</c:url>
				<a class="page-link" href="${page}">${i}</a>
			</li>
		</c:forEach>
		<c:if test="${pm.next }">
			<li class="page-item">
				<c:url var="nextUrl" value="/myPost">
					<c:param name="type" value="${pm.cri.type}" />
					<c:param name="search" value="${pm.cri.search}" />
					<c:param name="page" value="${pm.endPage+1}" />
				</c:url>
				<a class="page-link" href="${nextUrl}">다음</a>
			</li>
		</c:if>
	</ul>
		
	
</div>
</body>
</html>