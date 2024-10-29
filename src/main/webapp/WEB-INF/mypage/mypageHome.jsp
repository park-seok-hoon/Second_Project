<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

 <h1>마이페이지</h1>
 <hr>
<nav class="navbar bg-light">
  <div class="container-fluid">
  <ul class="container-fluid">
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/deleteMember"/>">회원탈퇴</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/check"/>">정보변경</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/keyword"/>">키워드 설정</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/myPost"/>">내가 쓴 글</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/myReply"/>">내가 쓴 댓글</a>
    </li>
  </ul>
  </div>
</nav>
  

</body>
</html>