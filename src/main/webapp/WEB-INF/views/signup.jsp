<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩5 css/js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<!-- jquery validtaion -->	
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
	<div class="container custom-container">
	<h1>회원가입</h1>
	<!-- 아이디, 비번, 비번확인, 이메일을 입력받아 서버로 전송하는 코드 get과 post 중에 선택해서 전송 -->
	<form action="<c:url value="/signup"/>" method="post">
		<div class="mb-3 mt-3">
		    <label for="id" class="form-label">아이디:</label>
		    <input type="text" class="form-control" id="id" placeholder="아이디" name="id">
	  	</div>
	  	<div class="mb-3 mt-3">
			<button type="button" class="btn btn-outline-success col-12" id="idCheck">아이디 중복 검사</button>
		</div>
	  	<div class="mb-3 mt-3">
		    <label for="pw" class="form-label">비번:</label>
		    <input type="password" class="form-control" id="pw" placeholder="비번" name="pw">
	  	</div>
		<div class="mb-3 mt-3">
		    <label for="pw2" class="form-label">비번확인:</label>
		    <input type="password" class="form-control" id="pw2" placeholder="비번확인" name="pw2">
	  	</div>
		<div class="mb-3 mt-3">
		    <label for="nickname" class="form-label">닉네임:</label>
		    <input type="text" class="form-control" id="nickname" placeholder="닉네임" name="nickname">
	  	</div>
	  	<div class="mb-3 mt-3">
		    <label for="mail" class="form-label">이메일:</label>
		    <input type="text" class="form-control" id="mail" placeholder="이메일" name="mail">
	  	</div>
	  	<div class="mb-3 mt-3">
		    <label for="phone" class="form-label">핸드폰 번호:</label>
		    <input type="text" class="form-control" id="phone" placeholder="핸드폰 번호" name="phone">
	  	</div>
	  	<div class="mb-3 mt-3">
		    <label for="name" class="form-label">이름:</label>
		    <input type="text" class="form-control" id="name" placeholder="이름" name="name">
	  	</div>
		<button type="submit" class="btn btn-outline-success col-12">회원가입</button>
	</form>
</div>
<!-- 정규식 구현 -->
<script type="text/javascript">
$("form").validate({
	rules : {
		id : {
			required : true,
			regex : /^\w{8,13}$/
		},
		pw : {
			required : true,
			regex : /^[a-zA-Z0-9!@#$%]{8,20}$/
		},
		pw2 : {
			equalTo : "[name=pw]" //name이 아닌 id를 써 줌
		},
		mail : {
			required : true,
			email : true
		},
		phonenum : {
			required : true,
			regex : /^\d{2,3}-\d{3,4}-\d{4}$|^\d{3,4}-\d{4}-\d{4}$/
		},
		nickname : {
			required : true,
			regex : /^[a-zA-Z가-힣0-9~!@#$%]{4,12}$/
		},
		name : {
			required : true,
			regex : /^[a-zA-Z가-힣]{3,10}$/
		}
}, 
	messages : {
		id : {
			required : "필수 항목입니다.",
			regex : "아이디는 숫자 영문 8~13자입니다."
		},
		pw : {
			required : "필수 항목입니다.",
			regex : "비밀번호는 숫자,영문, !@#만 사용가능하며 8~15자입니다."
		},
		pw2 : {
			equalTo : "비밀번호와 일치하지 않습니다."
		},
		mail : {
			required : "필수 항목입니다.",
			email : "이메일 형식이 아닙니다."
		},
		phonenum : {
			required : "필수 항목입니다.",
			regex : "전화번호 형식이 아닙니다."
		},
		nickname : {
			required : "필수 항목입니다.",
			regex : "영문자,한글,숫자,그리고 특수문자는 ~!@#$%^만 허용하는 4~12자입니다."
		},
		name : {
			required : "필수 항목입니다.",
			regex : "이름은 영문자,한글만 사용 가능하며 3~10자입니다."
		}
		
	}
});

$.validator.addMethod(
	"regex",
	function (value, element, regexp){
		var re= new RegExp(regexp);
		return this.optional(element) || re.test(value);
	},
	"정규표현식에 맞지 않습니다."
)
</script>

<script type="text/javascript">
	let flag = false;
	$("#idCheck").click(function(){
		
		let id = $("[name=id]").val();
		$.ajax({
			url : '<c:url value="/id/check"/>',
			method : 'get',
			async : true, //동기/비동기 선택, true : 비동기, false : 동기
			data : {
				"id" : id
			},
			success : function(data){
				if(data){
					alert("사용 가능한 아이디입니다.");
					flag = true;
				}else{
					alert("이미 사용중인 아이디입니다.");
				}
			},
			error : function (a, b, c) {
				console.error("예외 발생");
			}
		});//ajax end
		
	});//click end
	$("form").submit(function(){
		if(!flag){
			alert("아이디 중복 검사를 해야합니다.");
			return false;
		}
	})
	$("[name=id]").change(function(){
		flag = false;
	})
</script>
</body>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</html>