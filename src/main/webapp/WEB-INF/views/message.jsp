<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		let msg = '${msg}';
		if(msg != ''){
			alert(msg);
		}
		location.href = '<c:url value="/${url}"/>';
		//value에 "/"로 들어가 있어서 servlet에서 쓸 때는 ""로 쓰는 것ㄴ
	</script>
</body>
</html>