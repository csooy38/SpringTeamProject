<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/memberStyle.css">

<title>Member</title>
</head>
<body>
	<div class="layout_container">
		<div class="main">
			<jsp:include page="../include/header.jsp" />
			<c:set var="mem" value="${Cont }" />
			
			<div class="member_container">
			<jsp:include page="../include/member_header.jsp"/>
				
				<!-- member_content -->
				<div class="member_content">
					<jsp:include page="../include/member_navmenu.jsp" />
					
					<div class="member_like">
						<h3>찜 리스트</h3>
						<c:if test="${!empty ProductLikeInfo}">
						<ul id="allList">
						<c:forEach items="${ProductLikeInfo }" var="dto">
							<li id="box">
								<a href="<%=request.getContextPath() %>/product_cont.do?no=${dto.getPro_no()}">
									<img src="resources/img/upload/${dto.getPro_img() }">
								</a>
								<ul id="stepList">
									<li class="product-info">
										<a class="pro-tag" href="#">${dto.getPro_tag() }</a>
									</li>
									<li>
										<a class="pro-name" href="<%=request.getContextPath() %>/product_cont.do?no=${dto.getPro_no()}">${dto.getPro_name() }</a>
									</li>
									<li>
										<span class="pro-price"><fmt:formatNumber value="${dto.getPro_output_price() }" /> 원</span>
									</li>
									<li>
										<a href="<%=request.getContextPath() %>/productLike_delete.do?no=${dto.getPro_no()}&id=${session_id}" id="heart"><i class="fas fa-heart"></i></a>
									</li>
								</ul>
							</li>
						</c:forEach>
						</ul>
						</c:if>
					</div> <!-- member_like END -->
				</div> <!-- member_content END-->
			</div> <!-- member_container END-->
			<jsp:include page="../include/footer.jsp" />
		</div>
	</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</html>