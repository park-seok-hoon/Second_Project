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
<title>게시글 작성 페이지 입니다.</title>
<style>
	.background{background-color: #3A7CA5}
	.container{background-color: white; padding: 30px; padding-bottom: 60px}
	.tmp{font-style: italic; color: blue}
	.display-none{display:none}
	
	[name=title]{background-color: transparent; border: none; border-bottom: solid 1px black;
				height: 50px; width: 50%;}
	[name=title]::placeholder{color: #D9DCD6; font-size: 20px; font-weight: bold;}
	
	.dropdown{margin-top: 30px;}
	
	.post-content{width: 75%; height: 550px; margin-top: 30px;}
	
	.finish-btn{width: 75%; margin-top: 30px}

</style>
<!-- 부트스트랩5 css/js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.js"></script>
</head>
<body class="background">

<div class="container mt-3">
	<c:choose>
		<c:when test="${post != null}">
			<form action="<c:url value="/post/update"/>" method="post" class="post-write">
				<input type="text" name="num" value="${post.p_num}" class="display-none">
				<input type="text" name="title" value="${post.p_title}" placeholder="제목을 작성하세요">
				
				 <div class="dropdown">
					<div class="board-select">게시글 선택</div>
						<select name="board" class="board-select">
							<c:forEach items="${cateList}" var="cate">
								<optgroup class="" label="${cate.c_name }">
									<c:forEach items="${boardList}" var="board">
										<c:if test="${cate.c_num == board.b_c_num}">
										    <option <c:if test="${post.p_b_num == board.b_num}">selected</c:if> value="${board.b_num}">
										    	${board.b_name}
										    </option>
										</c:if>
									</c:forEach>
						
						      </optgroup>
							</c:forEach>
					      	
						</select>
				  </div>
				  
				  <textarea name="content" class="post-content" placeholder="게시글 내용을 입력하세요...">${post.p_content}</textarea>
				  <br>
				  <button class="finish-btn">완료</button>
			</form>
	</c:when>
		<c:otherwise>
			<h1>존재하지 않는 게시글 입니다.</h1>
		</c:otherwise>
	</c:choose>
</div>

<!-- 데이터 무결성 검사 -->
<script type="text/javascript">
	$("form").submit(function(){
		let title = $("[name=title]").val();
		console.log(title)
		if(title.length == 0){
			alert("제목은 1글자 이상 입력해야 합니다.");
			$("[name=title]").focus();
			return false;
		}
		if(title.length > 20){
			alert("제목은 20글자 이하로 입력해야 합니다.");
			$("[name=title]").focus();
			return false;
		}
		let content = $("[name=content]").val();
		if(content.length == 0){
			alert("내용은 1글자 이상 입력해야 합니다.");
			$("[name=content]").focus();
			return false;
		}
	});
	
	$('[name=content]').summernote({
		tabsize: 2,
		height: 400
	});
</script>

</body>
</html>