<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/login.css?after">
<title>아이디 찾기</title>
</head>
<body>
	
	<div class="layout_container">
		
		<jsp:include page="../include/header.jsp" />
		
		<div class="main">
			
			<div class="login_wrap">
				<c:set var="id" value="${result_id }"/>
				<div class="title">아이디 찾기</div>
				<div class="find_body">
					회원가입시 사용한 아이디는 <span>${id }</span> 입니다.
				</div>
				
				<div class="btn_wrap">
					<input type="button" value="비밀번호 찾기" class="login_btn"
						onclick="location.href='find_pwd.do'">	
					<input type="button" value="로그인하기" class="login_btn"
						onclick="location.href='login.do'">	
					<input type="button" value="회원가입" class="login_btn"
						onclick="location.href='join.do'">	
				</div>
				
			</div>
			
			
		</div>
		
		<jsp:include page="../include/footer.jsp" />
		
	</div>
	
</body>
</html>