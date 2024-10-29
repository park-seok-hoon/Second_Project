<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<meta charset="UTF-8">
<title>역할 관리</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<scriptsrc="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.background-blue{background-color: #3A7CA5}
	.background-white{background-color: white;}
	.tmp{font-style: italic; color: blue}
	
	.container{padding: 30px; padding-bottom: 60px}
	
	.auth-info .auth-count{float: left;}
	.auth-info .auth-insert-button{float: right;}
	.auth-info{width: 100%;}
	.auth-info:after{clear: both; content: '';}
	
	.auth-delete{width:20%;}
	.auth-delete-btn{visibility: hidden;}
</style>
</head>
<body class="background-blue">
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="container background-white">
	<h1>역할 조회</h1>
	<div class="auth-info">
		<h3 class="auth-count">역할 목록(${count})</h3>
		<a href="<c:url value='/auth/manage?state=insert'/>" class="auth-insert-button btn">역할 추가</a>
	</div>
	
	<div class="auth-list mb-3 mt-3">
		<table class="table table-hover">
		    <thead>
		      <tr>
		        <th>역할 이름</th>
		        <th>인원</th>
		        <th><span></span></th>
		      </tr>
		    </thead>
		    <tbody class="auth-body">
		    	<c:forEach items="${list}" var="auth">
		    		<tr class="auth-list-tr">
		    			<td class="auth-name src">
						<a href="<c:url value='/auth/manage?state=update&prevName=${auth.a_name}'/>">${auth.a_name}</a>
						</td>
						<td class="auth-count src">
							<a href="">${auth.a_count }명</a>
						</td>
						<td class="auth-delete">
							<a href="<c:url value='/auth/delete?name=${auth.a_name}'/>" class="auth-delete-btn btn">삭제</a>
						</td>
		    		</tr>
					
				</c:forEach>
		    </tbody>
		  </table>
	</div>
	
	<!-- 페이지네이션 구현 예정 -->
	<!-- 
	<div class="box-pagination">
		<ul class="pagination justify-content-center">
			<c:if test="${pm.prev}">
				<li class="page-item">
					<a class="page-link" href="${url}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pm.startPage }" end="${pm.endPage}" var="i">
				<li class="page-item <c:if test="${pm.cri.page == i}">active</c:if>">
					<a class="page-link" href="${url}">${i}</a>
				</li>
			</c:forEach>
			<c:if test="${pm.next}">
				<li class="page-item">
					<a class="page-link" href="${url}">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
	 -->
</div>
<!-- 마우스 호버 이벤트 -->
<script type="text/javascript">
	$('.auth-list-tr').mouseenter(function(){
		$(this).find('.auth-delete-btn').css("visibility", "visible")
	})
	$('.auth-list-tr').mouseleave(function(){
		$(this).find('a.auth-delete-btn').css("visibility", "hidden")
	})
	
</script>
</body>
</html>