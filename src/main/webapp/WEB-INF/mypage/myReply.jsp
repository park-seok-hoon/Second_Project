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

<h1>내가 쓴 댓글</h1>
<hr>
<table class="table table-hover">

		<thead>
			<tr>
				<th>번호</th>
				<th>게시글 제목</th>
				<th>내용</th>
				<th>작성자</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${replyList}" var="ReplyVO" varStatus="status">
				<tr>
					<td>${ReplyVO.r_num}</td>
					<td>
					 
						<a href="<c:url value="/post/detail?num=${ReplyVO.r_p_num}"/>">${postList[status.index].p_title}</a>
					</td>
					<td>
						${ReplyVO.r_content}
					</td>
					<td>
					${ReplyVO.r_mb_id}
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination justify-content-center">
		<c:if test="${pm.prev}">
			<li class="page-item">
				<c:url var="prevUrl" value="/myReply">
					<c:param name="type" value="${pm.cri.type}" />
					<c:param name="search" value="${pm.cri.search}" />
					<c:param name="page" value="${pm.startPage-1}" />
				</c:url>
				<a class="page-link" href="${prevUrl}">이전</a>
			</li>
		</c:if>
		<c:forEach begin="${pm.startPage}" end="${pm.endPage }" var="i">
			<li class="page-item <c:if test="${pm.cri.page == i }">active</c:if>">
				<c:url var="page" value="/myReply">
					<c:param name="type" value="${pm.cri.type}" />
					<c:param name="search" value="${pm.cri.search}" />
					<c:param name="page" value="${i}" />
				</c:url>
				<a class="page-link" href="${page}">${i}</a>
			</li>
		</c:forEach>
		<c:if test="${pm.next }">
			<li class="page-item">
				<c:url var="nextUrl" value="/myReply">
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