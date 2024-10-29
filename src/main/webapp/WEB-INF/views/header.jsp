<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="/team5/css/styles.css" rel="stylesheet" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.2.0/mdb.min.js"></script>
<title>Insert title here</title>
 <style>
    header {
      position: sticky;
      top: 0;
      z-index: 1000;
      background-color: white;
    }

    .content {
      background-color: rgba(240, 240, 240, 0.1);
      padding: 20px;
      margin-bottom: 100px; /* Footer 높이만큼 여백을 줍니다. */
    }
    body {
	  overflow-y: auto;
	  padding-bottom: 150px; /* footer의 높이만큼 body의 하단 여백을 확보 */
	}
   .custom-container {
	  background-color: #f8f9fa; /* 배경색 */
	  border-radius: 10px; /* 테두리의 꼭지점을 둥글게 만듦 */
	  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
	  padding: 20px; /* 내부 여백 */
	  margin-top: 50px; /* 위쪽 여백 */
	  max-width: 500px; /* 최대 너비 */
	  margin-left: auto; /* 좌우 중앙 정렬을 위해 왼쪽 여백을 auto로 설정 */
	  margin-right: auto; /* 좌우 중앙 정렬을 위해 오른쪽 여백을 auto로 설정 */
	  height: 80vh; /* 높이를 화면 높이의 80%로 설정 */
	  overflow-y: auto; /* y축 스크롤이 내용을 넘어갈 경우 스크롤을 생성 */
}

.custom-container .btn {
  display: block; /* 인라인 요소를 블록 요소로 변경하여 너비와 높이 조절 가능 */
  margin-top: 20px; /* 링크 위 여백 */
  width: 100%; /* 링크 폭을 부모 요소의 100%로 설정하여 가로 폭에 맞춤 */
  text-align: center; /* 텍스트를 중앙 정렬 */
}
.custom-container:hover {
  box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.3); /* 마우스 호버 시 그림자 효과 추가 */
}

 footer {
     position: fixed; /* 화면 하단에 고정 */
		bottom: 0;
		width: 100%;
		background-color: rgba(240, 240, 240, 0.9);
		text-align: center;
		z-index: 999; 
		height: 150px; 
    }
  </style>
</head>
<body>
<header>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg bg-body">
    <div class="container-fluid">
      <button
        data-mdb-collapse-init
        class="navbar-toggler"
        type="button"
        data-mdb-target="#navbarExample01"
        aria-controls="navbarExample01"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarExample01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="navbar-brand" href="<c:url value="/"/>">Team5</a>
          </li>
          <c:if test="${user == null}">
					<li class="nav-item">	<!-- 회원가입이 null일때 보이게 함 -->
						<a class="nav-link" href="<c:url value="/signup"/>">회원가입</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/login"/>">로그인</a>
					</li>
				</c:if>
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/category"/>">게시판</a>
				</li> 
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/post/allpost"/>">전체 게시글 조회</a>
				</li> 
			<c:if test="${user != null}">
				<li class="nav-item">	<!-- 로그아웃은 user가 null이 아닐때 보이게 함 -->
					<a class="nav-link" href="<c:url value="/logout"/>">로그아웃</a>
				</li>
				<li class="nav-item dropdown">
				  <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">마이페이지</a>
				  <ul class="dropdown-menu">
				    <li><a class="dropdown-item" href="<c:url value="/deleteMember"/>">회원탈퇴</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/check"/>">정보 변경</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/keyword"/>">키워드 등록</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/myPost"/>">내가 쓴 글</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/myReply"/>">내가 쓴 댓글</a></li>
				  </ul>
				</li>
				
			</c:if> 
			 <c:if test="${user.a_list.size() > 0}">  <!-- 관리자일 경우에만 보이게함(임시) -->
				<li class="nav-item dropdown">
				  <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">관리자 메뉴</a>
				  <ul class="dropdown-menu">
				    <li><a class="dropdown-item" href="<c:url value="/category/manager"/>">카테고리 관리</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/board/manager"/>">게시판 관리</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/"/>">회원 관리</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/auth/list"/>">역할 관리</a></li>
				    <li><a class="dropdown-item" href="<c:url value="/"/>">감사로그</a></li>
				  </ul>
			</c:if> 
        </ul>
      </div>
    </div>
  </nav>
  <!-- Navbar -->
</header>

<div class="content">
    <!-- Page content -->
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
<script type="text/javascript">

</script>

</body>
</html>