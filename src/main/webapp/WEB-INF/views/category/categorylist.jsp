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
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="container">
		<h1>게시판 조회</h1>
		<div class="mb-3 mt-3 category_List">
			<label for="category" class="form-label">카테고리:</label>
			
				<select class="form-control form-select cm_num" name="category" id="category">
					<c:forEach items="${list}" var="category">
						<option value="${category.c_num}" selected>
							${category.c_name}
						</option>
					</c:forEach>
				</select>
		</div>
		
		<div class="board-list">
			<label for="board" class="form-label">게시판:</label>
			<select class="form-control form-select bo_num" name="board" id="board">		
			</select>
		</div>
		<br>
		<div class="input-group search-group">
			<span class="input-group-text">비고</span>
			<select name="type" class="form-control form-select" >
				<option value = "all">전체</option>
				<option value = "title">제목</option>
				<option value = "writer">작성자</option>
			</select>
			<input type="text" class="form-control" placeholder="검색어" name="search">	
		    <button class="btn btn-outline-success btn-search" type="submit">검색</button>
  		</div>
		<br>
		<div class="post-list mb-3 mt-3">
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
			    <tbody class="post-body">
			     
			    </tbody>
			  </table>
		</div>

		<a href="<c:url value="/post/insert"/>" class="btn btn-outline-success btn-add" >게시글 등록</a>
	</div>
	
	<script type="text/javascript">
	
	/* 카테고리 선택후 게시판 출력 메서드 */
	$('.cm_num').click(function(){
		$('.post-body').hide();
		let categoryNum = $(".cm_num").val();
		let search = $("[name=search]").val('');
		getBoardByCategory(categoryNum)
	})
	
	$('.cm_num').click(function(){
		$('.post-body').hide();
		let categoryNum = $(".cm_num").val();
		getBoardByCategory(categoryNum)
	})
	
	function getBoardByCategory(categoryNum){
		$.ajax({
			method : 'get',
			url : '<c:url value="/board/list"/>',
			data : {
				categoryNum
			},
			success : function(data){
				let str ="";
				let board;
				for(board of data.boardList){
					str +=
						`
							<option value="\${board.b_num}" selected>
								\${board.b_name}
							</option>
						`
				}
				$('.bo_num').html(str)
			}
		})
	}
	/* 게시판 선택후 게시판 출력 메서드 끝 */
	
	$('.bo_num').click(function(){
		$('.post-body').show();
		let boardNum = $(".bo_num").val();
		let search = $("[name=search]").val('');
		getPostByBoard(boardNum);
	})

	
	function getPostByBoard(boardNum){
		$.ajax({
			method : 'get',
			url : '<c:url value="/post/list"/>',
			data : {
				boardNum
			},
			success : function(data){
				let str ="";
				let str2 = `<button class="btn btn-outline-success btn-add" type="submit">게시글 등록</button>`;
				for(let i = 0; i < data.postList.length; i++){
					str +=
						`
							<tr>
								<td>\${data.postList[i].p_num}</td>
						        <td class="col-5"><a href='<c:url value = "/post/detail?num=\${data.postList[i].p_num}"/>'>\${data.postList[i].p_title}</a></td>
						        <td class="col-3">\${data.postDateList[i]}</td>
						        <td>\${data.postList[i].p_view}</td>
						        <td>\${data.postList[i].p_mb_id}</td>
						    </tr>
						`
				}
				$('.post-body').html(str);
			}
		})
	}//getPostByBoard -- 게시판 선택 메서드 끝
	
	
	
	$('.btn-search').click(function(){
		let type = $("[name=type]").val();
		let search = $("[name=search]").val();
		let caNum = $(".bo_num").val();
		if (type == '' || search == '') {
		    alert("검색어를 다시 입력 해주세요");
		    return;
		}
		searchByBoard(type, search, caNum);
	});
	function searchByBoard(type, search, caNum){
		$.ajax({
			method : 'post',
			url : '<c:url value="/post/list"/>',
			data : {
				"type" : type,
				"search" : search,
				"caNum" : caNum
			},
			success : function(data){
				let str ="";
				for(let i = 0; i < data.searchpostList.length; i++){
					str +=
						`
							<tr>
								<td>\${data.searchpostList[i].p_num}</td>
						        <td class="col-5"><a href='<c:url value = "/post/detail?num=\${data.searchpostList[i].p_num}"/>'>\${data.searchpostList[i].p_title}</a></td>
						        <td class="col-3">\${data.postDateList[i]}</td>
						        <td>\${data.searchpostList[i].p_view}</td>
						        <td>\${data.searchpostList[i].p_mb_id}</td>
						    </tr>
						   
						`
				}
				$('.post-body').html(str);
				return;
			}
		});
	}
</script> 
</body>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</html>