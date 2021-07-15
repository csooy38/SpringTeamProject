<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	
	<div class="layout_container">
		<div class="main">
			<jsp:include page="../include/header.jsp" />
			
			<div class="id_field">
				<c:set var="pwd" value="${result_pwd }"/>
				<div class="title">비밀번호 찾기</div>
				<div class="body">
					회원가입시 사용한 비밀번호는 <span>${pwd }</span> 입니다.
				</div>
				<div class="btn_wrap">
					<input type="button" value="로그인하기"
						onclick="location.href='login.do'">	
				</div>			
				
			</div>
			
			<jsp:include page="../include/footer.jsp" />
		</div>
	</div>
	
	
</body>
</html>