<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			
			<form name="find_id" method = "post" 
				action = "<%=request.getContextPath()%>/find_pwd_ok.do">
				
				<div class="id_field">
					
					<div class="title">비밀번호 찾기</div>
					<div class="body">
						<div class="label">아이디</div>
						<input type="text" id="mem_id" name="mem_id" required="required">
					</div>
					<input type="submit" id="submit" value="찾기">
				</div>
			
			</form>
			
			<jsp:include page="../include/footer.jsp" />
		</div>
	</div>
	
	
</body>
</html>