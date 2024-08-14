<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tia102g4.rest.model.*" %>
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
			<c:forEach var="rest" items="${restaurantList }">
			<tr>
				<td>${rest.restId}</td>
				<td>${rest.restName}</td>
				<td>${rest.description}</td>
				<td>${rest.location}</td>
				<td>${rest.phone}</td>
			</tr>
			
			
			</c:forEach>
			<br />
			<ul class="item_list">
				<li data-restaurant-id="1">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">小龍餐廳</span><br> <span
					class="item_description">提供道地中餐美食。</span>

				</li>
				<li data-restaurant-id="2">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">大衛西餐廳</span><br> <span
					class="item_description">提供正宗西餐體驗。</span>

				</li>
				<li data-restaurant-id="3">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">美麗日本料理</span><br> <span
					class="item_description">提供新鮮美味的日本料理。</span>

				</li>
				<li data-restaurant-id="4">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">曉明韓式料理</span><br> <span
					class="item_description">提供正宗韓式美食。</span>

				</li>
				<li data-restaurant-id="5">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">大山泰式料理</span><br> <span
					class="item_description">提供道地泰式風味。</span>

				</li>
				<li data-restaurant-id="6">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">小柔印度料理</span><br> <span
					class="item_description">提供正宗印度料理。</span>

				</li>
				<li data-restaurant-id="7">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">大志素食餐廳</span><br> <span
					class="item_description">提供健康美味的素食餐點。</span>

				</li>
				<li data-restaurant-id="8">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">美玉燒烤</span><br> <span
					class="item_description">提供美味的燒烤料理。</span>

				</li>
				<li data-restaurant-id="9">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">小芳火鍋</span><br> <span
					class="item_description">提供豐富多樣的火鍋選擇。</span>

				</li>
				<li data-restaurant-id="10">

					<div class="img_block">
						<img src="https://via.placeholder.com/300x250" />
					</div> <span class="item_name">大華咖啡廳</span><br> <span
					class="item_description">提供香濃美味的咖啡。</span>

				</li>
			</ul>

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
			const contextPath = "<%=request.getContextPath()%>";
		</script>
		<script
			src="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/js/restaurant_selector.js"></script>
</body>
</html>