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
<title>게시글 조회 페이지 입니다.</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style>
	.background-blue{background-color: #3A7CA5}
	.background-white{background-color: white;}
	.tmp{font-style: italic; color: blue}
	
	.container{padding: 30px; padding-bottom: 60px}
	
	.post-info1 .post-view{float:right}
	.post-info1 .post-title{float:left}
	.post-info1::after{clear: both; content: ''; display: block;}
	
	.src{color:black; text-style: bold;}
	
	.btn-group{margin-top: 30px}
</style>
<!-- 부트스트랩5 css/js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="background-blue">
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container background-white mt-3">
	<c:choose>
		<c:when test="${post != null}">
			<div class="post-info">
				<div class="post-info1">
					<h1 class="post-title">${post.p_title}</h1>
					<div class="post-view">조회수: ${post.p_view}</div>
				</div>
				<div class="post-info2">
					<div class="post-src">
						<!-- 카테고리, 게시판 명을 누르면 해당 카테고리와 게시판으로 넘어가도록 설정해야함 -->
						<span class="src">${post.category}</span> 
						>
						<span class="src">${post.board}</span>
					</div>
					<div class="post-writer">${post.p_mb_id }</div>
					<div class="post-date">${post.p_date}</div>
					
				</div>
			
			
			</div>
			<hr>
			<div class="post-content">
				<h3 class="tmp">게시글 내용:</h3>
				${post.p_content}
			</div>
			<div class="btn-group">
			<!-- 현재 로그인 한 사용자와 id와 board의 작성자 id가 같거나 관리자 사용자일 경우 보이게 함 -->
				<c:if test="${post.p_mb_id == user.mb_id || postAuth}">
					<a href="<c:url value="/post/delete?num=${post.p_num}"/>" class="btn btn-outline-danger">삭제</a>
				</c:if>
				<c:if test="${post.p_mb_id == user.mb_id}">
					<a href="<c:url value="/post/update?num=${post.p_num}"/>" class="btn btn-outline-danger">수정</a>
				</c:if>
			</div>

			
			<hr>
			  	<div class="mt-3 mb-3 reply-box">
			  		<h3>댓글</h3>
			  		<!-- 댓글 리스트를 보여주는 박스 -->
			  		<div class="reply-list">
			  			<div class="input-group mb-3">
							<div class="col-3">abc123</div>
							<div class="col-9">댓글 내용</div>
						</div>
			  		</div>
			  		<!-- 댓글 페이지네이션 박스 -->
			  		<div class="reply-pagination">
			  			<ul class="pagination justify-content-center">
			  			</ul>
			  		</div>
			  		<!-- 댓글 입력 박스 -->
			  		<div class="reply-input-box">
						<div class="input-group">
							<textarea class="form-control reply-content"></textarea>
							<button type="button" class="btn btn-outline-success btn-reply-insert">등록</button>
						</div>
			  		</div>
			  	</div>
			
			
		</c:when>
		<c:otherwise>
			<h1>존재하지 않는 게시글 입니다.</h1>
		</c:otherwise>
	</c:choose>
	
</div>


<!-- =========================================================== -->
댓글
<!-- 댓글 등록 구현 -->
<script type="text/javascript">
//(댓글)등록 버튼 클릭 이벤트를 등록
$(".btn-reply-insert").click(function(){
		
		//로그인 체크
		if('${user.mb_id}' == ''){
			//확인 누르면 로그인 페이지로
			if(confirm("로그인이 필요한 서비스입니다. 로그인으로 이동하겠습니까?")){
				location.href = "<c:url value='/login'/>";
				return;
			}
			//취소 누르면 현재 페이지에서 추천/비추천 동작을 안함
			else{
				return;
			}
		}
	
	//입력받은 댓글을 가져옴 입력 태그면 value 입력 태그가 아니면 text를 이용해서 가져옴
		let content = $(".reply-content").val(); //클래스라서 .을 붙임
	//게시글 번호를 가져옴 어느 게시글의 댓글인지를 알기 위해서	
		let num = '${post.p_num}';
	
	$.ajax({
		url : '<c:url value="/reply/insert"/>',
		method : "post",
		data : {
			content, //content : content
			num //num : num
		},
		success : function(data){
			if(data == "ok"){
				alert("댓글을 등록했습니다.");
				cri.page = 1;
				getReplyList(cri);
				$(".reply-content").val("");
			}else{
				alert("댓글을 등록하지 못했습니다.");
			}
		}, 
		error : function(a, b, c){
			
		}
		
	});
});//click end
</script>
<!-- 댓글 조회 구현 -->
<script type="text/javascript">
//댓글 현재 페이지 정보
let cri = {
		page : 1,
		pNum : '${post.p_num}'
	}
//댓글 리스트를 화면에 출력하는 함수
function getReplyList(cri){
	console.log(cri);
	$.ajax({
		url : '<c:url value="/reply/list"/>',
		method : "post",
		data : cri,
		success : function(data){
			//data.list가 가능한 이유는 	ReplyListServlet에서 jobj.put("list", list);에서 "list"로 해줬기 때문에 가능함
					
				let str = '';
			for(reply of data.list){
				let btns = '';
				//현재 로그인한 유저와 댓글을 쓰기 위한 유저의 아이디가 동일하거나 댓글 관리 권한이 있다면 수정,삭제 버튼이 나타나고 아니라면 나타나지 않음
				if('${user.mb_id}' == reply.r_mb_id || ${rplyAuth}){	
				btns +=
					`
					<div class="btn-reply-group">
						<button class="btn btn-outline-warning btn-reply-update" data-num="\${reply.r_num}">수정</button>
						<button class="btn btn-outline-danger btn-reply-delete" data-num="\${reply.r_num}">삭제</button>
					</div>
					`
				}
				
			str +=
			`
					<div class="input-group mb-3 box-reply">
					<div class="col-3">\${reply.r_mb_id}</div>
					<div class="col-6 rp_content">\${reply.r_content}</div>
							\${btns}
					</div>
					`;
			}
			
			$(".reply-list").html(str);
			//JSON.parse(문자열) : json형태의 문자열을 객체로 변환
			//JSON.stringify(객체) : 객체를 json형태의 문자열로 변환
			let pm = JSON.parse(data.pm);
			let pmStr = "";
			
			//이전 버튼 활성화 여부
			if(pm.prev){
				pmStr += `
				<li class="page-item">								//data-page 몇 페이지로 이동할 건지 설정하기 위한 것
					<a class="page-link" href="javascript:void(0);" data-page="\${pm.startPage-1}">이전</a>
				</li>
				`;
			}
			//숫자 페이지
			for(i = pm.startPage; i<= pm.endPage; i++){
				let active = pm.cri.page == i ? "active" :"";
				pmStr += `
				<li class="page-item \${active}">
					<a class="page-link" href="javascript:void(0);" data-page="\${i}">\${i}</a>
				</li>
				`
			}
			//다음 버튼 활성화 여부
			if(pm.next){
				pmStr += `
					<li class="page-item">
						<a class="page-link" href="javascript:void(0);" data-page="\${pm.endPage+1}">다음</a>
					</li>
					`;
				}
			//class:reply-pagiantion 밑에 있는 ul 태그 안에 html 코드로 pmStr을 넣어주겠다는 의미
			$(".reply-pagination>ul").html(pmStr);
		}, 
		error : function(a, b, c){
			
		}
	});
}
$(document).on("click",".reply-pagination .page-link", function(){
	cri.page = $(this).data("page");
	getReplyList(cri);
})

getReplyList(cri);
</script>
<!-- 댓글 삭제 기능  -->
<script type="text/javascript">
//document객체에 이벤트를 등록하기 때문에 요소가 나중에 추가되도 동작 //버튼 등록 후 동작하는 것
$(document).on("click",".btn-reply-delete", function(){
	let num = $(this).data("num");
	console.log(num);
	$.ajax({
			url : '<c:url value="/reply/delete"/>',
			method : "post",
			data : {
				num
			},
			success : function(data){
				console.log(data);
				if(data == 'ok'){
					alert("댓글을 삭제했습니다.");
					getReplyList(cri);	//댓글을 삭제한 이후에 댓글들을 가져오는 작업
				}else{
					alert("댓글을 삭제하지 못했습니다.");	
				}
			},
			error : function(a, b, c){
				
			}
		

	});

});
</script>
<!-- 댓글 수정 구현 -->
<script type="text/javascript">
$(document).on("click", ".btn-reply-update", function(){	//223 수정버튼 btn-reply-update
	initReply();
	//현재 댓글 보여주는 창이 textarea태그로 변경
	//기존 댓글 창을 감춤
	$(this).parents(".box-reply").find(".rp_content").hide();	//회원 아이디와 댓글 내용을 가진 div 클래스를 숨김.
	let reply = $(this).parents(".box-reply").find(".rp_content").text();	//reply .box-reply에서 댓글 내용을 찾아서.
	let textarea = 
		//let reply 278번줄 것을 가져온것
	`
	<textarea class="form-control com-input">\${reply}</textarea>  
	`
	$(this).parents(".box-reply").find(".rp_content").after(textarea); //댓글 내용을 찾아서 textarea로 덮음
	
	//수정 삭제 버튼 대신 수정 완료 버튼으로 변경
	$(this).parent().hide();
	let num = $(this).data("num");
	let btn = 
	`
	<button class="btn btn-outline-success btn-complete" id="ry" data-num="\${num}">수정완료</button>-
	`;
	$(this).parent().after(btn);
	
});//click end

function initReply(){
	//감추었던 댓글 내용을 보여줌
	$(".r_content").show();
	//감추었던 수정/삭제 버튼을 보여줌
	$(".btn-reply-group").show();
	//추가했던 댓글 textarea태그를 삭제
	$(".com-input").remove();
	//추가했던 수정 완료 버튼을 삭제
	$(".btn-complete").remove();
}
//수정 완료 버튼 클릭 이벤트
$(document).on("click",".btn-complete", function(){
	//수정하기 위해 필요한 정보를 가져옴 : 수정된 내용, 댓글 번호
	let num = $(this).data("num");
	//let content = $(".com-input").val();
	let content = $('.com-input').val();
	$.ajax({
		url : '<c:url value="/reply/update"/>',
		method : 'post',
		data : {
			num, //num : num,
			content //content : content
		},
		success : function(data){
			if(data == "ok"){
				alert("댓글을 수정했습니다.");
				getReplyList(cri);
			}else{
				alert("댓글을 수정하지 못했습니다.");
			}
		}, 
		error : function(xhr, status, error){
			
		}
	});
});
</script>
</body>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</html>