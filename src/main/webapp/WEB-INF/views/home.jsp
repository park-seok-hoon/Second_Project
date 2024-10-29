<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
c태그 라이브러리 추가
!!! 추가하지 않으면 오류도 안뜨고 화면은 실행돼서 시간낭비 할 수 있음!!! 
-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면입니다</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"/>
<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
	  <div class="carousel-item active">
	    <img src="https://cdn.pixabay.com/photo/2023/01/08/08/40/road-7704729_1280.jpg" class="d-block w-100" alt="..." style="height: 500px; width: 500px;object-fit:cover;">
	    </div>
	    <div class="carousel-item">
	      <img src="https://cdn.pixabay.com/photo/2024/02/17/00/18/cat-8578562_1280.jpg" class="d-block w-100" alt="..." style="height: 500px; width: 500px; object-fit:cover;">
	    </div>
	    <div class="carousel-item">
	      <img src="https://cdn.pixabay.com/photo/2023/03/20/20/35/sunset-7865844_1280.jpg" class="d-block w-100" alt="..." style="height: 500px; width: 500px;object-fit:cover;">
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
</body>
<jsp:include page="/WEB-INF/views/footer.jsp"/>

</html>