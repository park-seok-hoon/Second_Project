<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
	<!-- jquery validtaion -->	
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/additional-methods.min.js"></script>
</head>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<div class="container">
<h1>정보 변경</h1>
<hr>
	<form action="<%=request.getContextPath()%>/changeMember" method="post">
		
	  	<div class="mb-3 mt-3">
		    <label for="pw" class="form-label">비번:</label>
		    <input type="password" class="form-control" id="pw" placeholder="비번" name="pw" value="${user.mb_pw}">
	  	</div>
		<div class="mb-3 mt-3">
		    <label for="nickname" class="form-label">닉네임:</label>
		    <input type="text" class="form-control" id="nickname" placeholder="닉네임" name="nickname" value="${user.mb_nickname}">
	  	</div>
	  	<div class="mb-3 mt-3">
		    <label for="mail" class="form-label">이메일:</label>
		    <input type="text" class="form-control" id="mail" placeholder="이메일" name="mail" value="${user.mb_mail}">
	  	</div>
	  	<div class="mb-3 mt-3">
		    <label for="phone" class="form-label">핸드폰 번호:</label>
		    <input type="text" class="form-control" id="phone" placeholder="핸드폰 번호" name="phone" value="${user.mb_phone}">
	  	</div>
	  	<div class="mb-3 mt-3">
		    <label for="name" class="form-label">이름:</label>
		    <input type="text" class="form-control" id="name" placeholder="이름" name="name" value="${user.mb_name}">
	  	</div>
		<button class="btn btn-outline-warning">변경</button>
	</form>
</div>
<script type="text/javascript">
$("form").validate({
	rules : {
		
		pw : {
			required : true,
			regex : /^[a-zA-Z0-9!@#]{6,15}$/
		},
		nickname : {
			required : true,
			regex : /^[a-zA-Z가-힣0-9~!@#$%]{4,12}$/
		},
		
		mail : {
			required : true,
			email : true
		},
		phone : {
			required : true,
			regex : /^\d{2,3}-\d{3,4}-\d{4}$|^\d{3,4}-\d{4}-\d{4}$/
		},
		name : {
			required : true,
			regex : /^[a-zA-Z가-힣]{3,10}$/
		}
		
	}, 
	
	messages : {
		
		pw : {
			required : "필수 항목입니다.",
			regex : "비밀번호는 숫자,영문, !@#만 사용가능하며 6~15자입니다."
		},
		
		mail : {
			required : "필수 항목입니다.",
			email : "이메일 형식이 아닙니다."
		},
		phone : {
			required : "필수 항목입니다.",
			regex : "전화번호 형식이 아닙니다."
		},
		name : {
			required : "필수 항목입니다.",
			regex : "이름은 영문자,한글만 사용 가능하며 3~10자입니다."
		},
		nickname : {
			required : "필수 항목입니다.",
			regex : "영문자,한글,숫자,그리고 특수문자는 ~!@#$%^만 허용하는 4~12자입니다."
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

</body>
</html>