<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/css/include.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">

function showSearch(){
	if(document.getElementById('search').style.display == 'block'){
		document.getElementById('search').style.display = 'none';
		document.getElementById('search-btn').style.display = 'block';
	}else {
		document.getElementById('search').style.display = 'block';
		document.getElementById('search-btn').style.display = 'none';
	}
}

$(document).mouseup(function(e){
	var content = $("#search");
	
	if(content.has(e.target).length === 0){
		content.hide();
		$("#search-btn").show();
	}
});

</script>
<body>

	<div class="layout_container">
		
		<div class="header_desktop header">
			
			<div class="menu_list_right">
				<a href="<%=request.getContextPath()%>/about.do">ABOUT</a>
				<a href="<%=request.getContextPath()%>/product_list.do">SHOP</a>
				<a href="<%=request.getContextPath()%>/review_list.do">REVIEW</a>
			</div>
			

			<a href="<%=request.getContextPath()%>/main.do" class="site_logo">[LOGO]</a>
			
			<div class="menu_list_left">
				<a id="search-btn" href="javascript:void(0)" onclick="showSearch();">검색</a>
					<div id="search" style="display: none;">
						<form method="post" action="product_search.do">			
								<input type="search" name="k">
								<input type="submit" value="검색"> 
						</form>
					</div>
				<a href="<%=request.getContextPath()%>/login.do">LOGIN</a>
				<a href="<%=request.getContextPath()%>/cart.do">장바구니</a>
			</div>
			
		</div>
		
	</div>

</body>
</html>