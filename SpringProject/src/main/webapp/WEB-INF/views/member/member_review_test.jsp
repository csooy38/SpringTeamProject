<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/memberReviewStyle.css">
<title>구매 후기 작성</title>
</head>
<body>
	<div class="member_review">
		<form method="post" name="review_form" enctype="multipart/form-data" action="<%=request.getContextPath() %>/member_review_wrtie_ok.do" >
			<c:set var="dto" value="${ReviewWrite }" />
			<input type="hidden" name="review_star" value="">
			<input type="hidden" name="order_no" value="${dto.getOrder_no() }">
			<h3 class="review_header">후기작성</h3>
			<ul class="review_info">
				<li>상품 식별이 가능하도록 찍은 사진만 가능합니다.</li>
				<li>이메일, 연락처 등의 개인 정보/광고/비속어가 포함된 후기는 블라인드 처리됩니다.</li>
				<li>내용은 최소 20자 이상 작성합니다.</li>
				<li>후기 등록 후 삭제는 불가합니다.</li>
			</ul>
			<div class="review_content">
				<ul class="review_star">
					<li id="reviewTag">별점</li>
					<li>
						<a href="#none" id="star1" class="off"></a>
						<a href="#none" id="star2" class="off"></a>
						<a href="#none" id="star3" class="off"></a>
						<a href="#none" id="star4" class="off"></a>
						<a href="#none" id="star5" class="off"></a>
					</li>
				</ul>
				<div class="review_img">
					<label for="review_file" id="qna_file_name">사진선택</label>
					<input type="file" id="review_file" name="review_imgs" accept=".jpg, .jpeg, .png">
					<p class="fileGuide"> * 첨부가능 파일종류 : jpg, png, jpeg (최대 10MB) </p>
				</div>
				<div class="review_text">
					<ul>
						<li id="reviewTag">제목</li>
						<li><input id="review_title" type="text" name="review_title" placeholder="제목을 입력해주세요."></li>
					</ul>
					<ul>
						<li id="reviewTag">내용</li>
						<li><textarea id="review_cont" name="review_cont" placeholder="최소 20자 이상 입력해주세요"></textarea></li>
					</ul>
				</div>
			</div> <!-- review_content END"> -->
			<div class="review_btn">
				<input type="submit" value="등록" id="reviewBTN">
			</div>
		</form>
	</div> <!-- member_review END -->
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member_reviewStar.js"></script>
</html>