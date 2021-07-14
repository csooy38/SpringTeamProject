<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html><head>
<title>Sidebar 02</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
	rel="stylesheet">
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/bootstarp.main.css">
<link rel="stylesheet" href="resources/css/include.css">
<link rel="stylesheet" href="resources/css/memberStyle.css">
<link rel="stylesheet" href="resources/css/product.css">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<div class="wrapper d-flex align-items-stretch">
		<jsp:include page="../include/admin_sidebar.jsp" />

		<!-- Page Content  -->
		<div id="content" class="p-4 p-md-5 pt-5">

			<div align="center">
				<hr width="50%" color="red">
				<h3>회원 목록</h3>
				<hr width="50%" color="red">
				<br> <br>

				<table border="1" cellspacing="0" width="500">
					<tr>
						<th>회원번호</th>
						<th>회원성명</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>가입일자</th>
						<th>적립금</th>
					</tr>

					<c:set var="list" value="${List }" />
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="dto">
							<tr>
								<td>${dto.getMem_no() }</td>
								<td>${dto.getMem_name() }</td>
								<td>${dto.getMem_phone() }</td>
								<td>${dto.getMem_addr() }</td>
								<td>${dto.getMem_regdate().substring(0,10) }</td>
								<td>${dto.getMem_point() }</td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${empty list }">
						<tr>
							<td colspan="4" align="center">
								<h3>검색된 게시물이 없습니다.....</h3>
							</td>
						</tr>
					</c:if>

					<tr>
				</table>
				<br> <br>

				<div class="pagination">
					<c:if test="${page.getPage() > page.getBlock() }">
						<a href="admin_list.do?page=1">◀◀</a>
						<a href="admin_list.do?page=${Paging.getStartBlock() - 1 }">◀</a>
					</c:if>

					<c:forEach begin="${page.getStartBlock() }"
						end="${page.getEndBlock() }" var="i">
						<c:if test="${i == page.getPage() }">
							<b><a href="product_list.do?page=${i }">${i }</a></b>
						</c:if>

						<c:if test="${i != page.getPage() }">
							<a href="admin_list.do?page=${i }">${i }</a>
						</c:if>
					</c:forEach>

					<c:if test="${page.getEndBlock() < page.getAllPage() }">
						<a href="admin_list.do?page=${page.getEndBlock() +1 }">▶</a>
						<a href="admin_list.do?page=${page.getAllPage() }">▶▶</a>
					</c:if>
					<br>
				</div>

				<form method="post"
					action="<%=request.getContextPath()%>/member_search.do">
					<input type="hidden" name="page" value="${Paging.getPage() }">
					<select name="field">
						<option value="name">회원이름</option>
						<option value="addr">주소</option>
					</select> <input type="text" name="keyword"> <input type="submit"
						value="검색">
				</form>
		<jsp:include page="../include/footer.jsp" />
			</div>
		</div>
	</div>

</body></html></html>