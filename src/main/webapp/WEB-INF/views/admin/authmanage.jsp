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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.background-blue{background-color: #3A7CA5}
	.background-white{background-color: white;}
	.boackground-blue1{background-color: #81C3D7}
	.tmp{font-style: italic; color: blue}
	
	.container{padding: 30px; padding-bottom: 60px}
	
	.quick-bar .role-name-input{float: left; width: 80%}
	.quick-bar .submit-btn{float: right;}
	.quick-bar{width: 100%;}
	.quick-bar:after{clear: both; content: ''; display: block;}
	
	.auth-name{font-weight: 900;}
	.auth-info{font-weight: 100;}
	.auth-check{width: 50%;}
	
	.custom-switch{padding: 0px; width: 0px}
	
	.member-set-contianer{margin-top: 80px;}
	
	.member-list{display: none;}
</style>
</head>
<body class="background-blue">
<jsp:include page="/WEB-INF/views/header.jsp" />
<div class="container background-white">
	<form action="" method="post" class="role-input">
	<div class="auth-set-contianer">
		<h1>역할 생성</h1>
			<div class="quick-bar">
				<div class="role-name-input">
					역할 이름: <input type="text" name="name" placeholder="역할 이름을 입력하세요..." <c:if test="${role != null}">value="${role.a_name}"</c:if>>
				</div>
				<button class="submit-btn btn boackground-blue1">생성</button>
			</div>
			
			<table class="auth-table table mt-3">
			    <thead>
			      <tr>
			        <th class="text-center">권한</th>
			        <th class="auth-check text-center">허용</th>
			      </tr>
			    </thead>
			    <tbody class="role-body">
		    		<tr>
		    			<td>
		    				<div class="auth-name">공지사항 작성</div>
		    				<div class="auth-info">공지사항 글 작성 권한을 부여합니다.</div>
		    			</td>
		    			<td>
		    				 <div class="custom-control custom-switch center">
							    <input type="checkbox" class="custom-control-input" name="notice" id="notice-auth-check" 
							    	<c:if test="${role.a_list.charAt(5) == '1'.charAt(0)}">checked="true"</c:if>
						    	>
							    <label class="custom-control-label" for="notice-auth-check"></label>
							  </div>
	    				</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="auth-name">댓글 삭제</div>
		    				<div class="auth-info">댓글 삭제 권한을 부여합니다.</div>
		    			</td>
		    			<td>
		    				 <div class="custom-control custom-switch">
							    <input type="checkbox" class="custom-control-input" name="reply" id="reply-auth-check"
							    	<c:if test="${role.a_list.charAt(4) == '1'.charAt(0)}">checked="true"</c:if>
							    >
							    <label class="custom-control-label" for="reply-auth-check"></label>
							  </div>
	    				</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="auth-name">게시글 삭제</div>
		    				<div class="auth-info">게시글 삭제 권한을 부여합니다.</div>
		    			</td>
		    			<td>
		    				 <div class="custom-control custom-switch">
							    <input type="checkbox" class="custom-control-input" name="post" id="post-auth-check"
							    	<c:if test="${role.a_list.charAt(3) == '1'.charAt(0)}">checked="true"</c:if>
							    >
							    <label class="custom-control-label" for="post-auth-check"></label>
							  </div>
	    				</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="auth-name">게시판 관리</div>
		    				<div class="auth-info">게시판 생성, 수정, 삭제 권한을 부여합니다.</div>
		    			</td>
		    			<td>
		    				 <div class="custom-control custom-switch">
							    <input type="checkbox" class="custom-control-input" name="board" id="board-auth-check"
							    	<c:if test="${role.a_list.charAt(2) == '1'.charAt(0)}">checked="true"</c:if>
							    >
							    <label class="custom-control-label" for="board-auth-check"></label>
							  </div>
	    				</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="auth-name">카테고리 관리</div>
		    				<div class="auth-info">카테고리 생성, 수정, 삭제 권한을 부여합니다.</div>
		    			</td>
		    			<td>
		    				 <div class="custom-control custom-switch">
							    <input type="checkbox" class="custom-control-input" name="category" id="category-auth-check"
							    	<c:if test="${role.a_list.charAt(1) == '1'.charAt(0)}">checked="true"</c:if>
							    >
							    <label class="custom-control-label" for="category-auth-check"></label>
							  </div>
	    				</td>
		    		</tr>
		    		<tr>
		    			<td>
		    				<div class="auth-name">역할 관리</div>
		    				<div class="auth-info">역할 생성, 수정, 삭제, 역할을 부여받은 멤버 설정 권한을 부여합니다.</div>
		    			</td>
		    			<td>
		    				 <div class="custom-control custom-switch">
							    <input type="checkbox" class="custom-control-input" name="role" id="role-auth-check"
							    	<c:if test="${role.a_list.charAt(0) == '1'.charAt(0)}">checked="true"</c:if>
							    >
							    <label class="custom-control-label" for="role-auth-check"></label>
							  </div>
	    				</td>
		    		</tr>
			    </tbody>
			  </table>
		  </div>
		  <button class="submit-btn btn boackground-blue1">생성</button>
		  
		  <div class="member-set-contianer">
		  	<h1>멤버 관리</h1>
		  	<table class="member-table table mt-3">
		  		<thead>
			      <tr>
			        <th class="text-center">멤버 아이디</th>
			        <th class="text-center">권한 부여</th>
			      </tr>
			    </thead>
		  		<tbody class="role-body">
	    			<c:forEach items="${list}" var="member">
	    				<tr>
	    					<td class="text-center" name="${member.mb_id}">${member.mb_id}</td>
			    			<td class="text-center member-check">
							    <input type="checkbox" class="auth-check" name="auth-check" value="${member.mb_id}"
							    	<c:if test="${maList.contains(member.mb_id)}">checked="true"</c:if>
							    >
		    				</td>
	    				</tr>
	    			</c:forEach>
	    		</tbody>
		  	</table>
		  	
		  </div>
		  
		  <!-- script로 체크된 checkbox input의 아이디만 모아서 아래 display: none인 input에 넣어서 Servlet에서 update 및 insert 처리하기...?? 근데 받은 값은 어떻게 처리? -->
		  <input class="member-list" name="member-list">
		
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
		 <button class="submit-btn btn boackground-blue1">생성</button>
	 </form>
</div>

<!-- submit 이벤트
	1. 이름 공백 확인
	2. 이름 중복성 검사
	3. 역할 부여받은 멤버 정보를 보냄
 -->
<script type="text/javascript">
	$("form").submit(function(){
		let input = $("[name=name]")
		let name = input.val();

		let result = true;
		
		if(name.length == 0){
			alert("역할 이름을 입력하세요")
			input.focus();
			return false;
		}
		
		let res= isNameDup(name)
		console.log(res)
		if(res){
			alert("이미 등록된 이름의 역할입니다.")
			input.focus();
			result = false;
		}
		console.log(result)
		return result;
	});
	
	
	function isNameDup(name){
		// 입력된 아이디를 가져옴
		let obj = {
			name: name
		}
		let res;
		// 서버에 아이디를 전송해서 사용 가능 / 불가능 처리
		$.ajax({
			async: false,
			url : '<c:url value="/role/check"/>',
			method : "get",
			data : obj,
			success : function(data){
				if(data){
					res = true;
				}else{
					res= false;
				}
			},
			error : function(a, b, c){
				console.log("error")
			}
		})
		return res;
	}
	
</script>
<!-- 수정, 생성 여부에 따른 버튼 내용 수정 -->
<script type="text/javascript">
	// <c:if test="${role.a_list.charAt(5) == 1}">checked="true"</c:if>
	
	// 서버에서 전송받은 값은 문자열로 변환해서 비교함
	// action="<c:url value="/auth/manage"/>"
	if('${role}'!=''){
		console.log('if')
		$('.submit-btn').text('수정')
		$('form').attr('action', '<c:url value="/auth/update?prevName=${role.a_name}"/>');
	}else{
		$('.submit-btn').text('생성')
		$('form').attr('action', '<c:url value="/auth/manage"/>');
	}
</script>

</body>
</html>