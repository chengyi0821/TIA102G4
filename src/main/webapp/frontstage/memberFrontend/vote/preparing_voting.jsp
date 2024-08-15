<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tia102g4.rest.model.*"%>
<%@ page import="java.util.List"%>
<%
List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投票</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/css/style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/css/orderlist.css"
	rel="stylesheet" />
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="../announcement/anno.html"
							class="navbar-brand mx-5 d-none d-lg-block"></a> <img id="logo"
							src="<%=request.getContextPath()%>/frontstage/memberFrontend/image/logo.png" />
						<h1 class="m-0 display-4 text-primary">Chugether</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Topbar End -->

	<!-- Navbar Start -->
	<div class="container-fluid position-relative nav-bar p-0">
		<style>
.container-fluid.position-relative.nav-bar.p-0 li {
	color: black !important;
}
</style>
		<div class="container-lg position-relative p-0 px-lg-3"
			style="z-index: 9;">
			<nav
				class="navbar navbar-expand-lg bg-white navbar-light shadow p-lg-0">

				<div class="collapse navbar-collapse justify-content-between"
					id="navbarCollapse">
					<div class="navbar-nav ml-auto py-0">
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">&nbsp&nbsp&nbsp首頁 </a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">會員登入</a></li>
								<li><a style="color: black;" href="#">最新消息</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">會員專區</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">會員資料</a></li>
								<li><a style="color: black;" href="#">查看收藏</a></li>
							</ul>


						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link ">店家介紹</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">查看店家</a></li>
								<li><a style="color: black;" href="#">查看評價</a></li>
								<li><a style="color: black;" href="#">營業資訊</a></li>
								<li><a style="color: black;" href="#">類別搜尋</a></li>

							</ul>
						</div>
					</div>

					<div class="navbar-nav mr-auto py-0">
						<div class="orderblock">
							<a href="#" class="nav-item nav-link active">揪團系統</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">發起揪團</a></li>
								<li><a style="color: black;" href="#">參與揪團</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">訂單管理</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">編輯訂單</a></li>
								<li><a style="color: black;" href="#">取消訂單</a></li>
								<li><a style="color: black;" href="#">餐後評論</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">聯絡客服</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">客服信箱</a></li>
								<li><a style="color: black;" href="#">Q&A</a></li>


							</ul>
						</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->
	<div id="main-content1"></div>
	<div id="main-content2">
		<div id="main-content2">
			<h3>可以選擇的餐廳如下</h3>
			<form
				action="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/option.do"
				method="post">
				<input type="hidden" name="action" value="choice">
				<table>
					<c:forEach var="rest" items="${restaurantList }">
						<tr>
							<td>${rest.restId}</td>
							<td>${rest.restName}</td>
							<td>${rest.description}</td>
							<td>${rest.location}</td>
							<td>${rest.phone}</td>
							<td><label> <input type="checkbox" name="restchoice"
									value="option:${rest.restId}">
							</label></td>
						</tr>
					</c:forEach>
				</table>
				<br /> <input type="submit" value="選這些餐廳">
			</form>

			

		</div>
		<div class="table-container"></div>

		<div id="main-content3" style="width: 400px;"></div>
		<footer id="footer">
			<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
			<h5 class="footerh5">常見問題</h5>
			<h5 class="footerh5">網站地圖</h5>
			<h5 class="footerh5">使用者條款</h5>
			<h5 class="footerh5">隱私權條款</h5>
			<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
		</footer>
		<script>
			const contextPath = "<%=request.getContextPath()%>
			";
		</script>
		<script
			src="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/js/restaurant_selector.js"></script>
		<script type="text/javascript">document.addEventListener('DOMContentLoaded', function() {
		    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
		    const maxAllowed = 3;

		    checkboxes.forEach(function(checkbox) {
		        checkbox.addEventListener('change', function() {
		            const checkedCount = document.querySelectorAll('input[type="checkbox"]:checked').length;
		            
		            if (checkedCount >= maxAllowed) {
		                checkboxes.forEach(function(cb) {
		                    if (!cb.checked) {
		                        cb.disabled = true;
		                    }
		                });
		            } else {
		                checkboxes.forEach(function(cb) {
		                    cb.disabled = false;
		                });
		            }
		        });

		        // 新增點擊事件監聽器
		        checkbox.addEventListener('click', function(event) {
		            if (this.disabled) {
		                event.preventDefault(); // 阻止默認行為
		                alert('最多選擇三家餐廳，如要新增請取消不需要的選項');
		            }
		        });
		    });
		});</script>
</body>
</html>