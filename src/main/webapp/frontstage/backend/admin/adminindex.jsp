<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tia102g4.admin.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
Admin entity = (Admin) request.getAttribute("adminId");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我要揪團</title>

<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<link
	href="<%=request.getContextPath()%>/frontstage/backend/css/style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/backend/admin/css/adminIndex.css"
	rel="stylesheet" />

<style>
.container-fluid.position-relative.nav-bar.p-0 li {
	color: black !important;
}
</style>
</head>
<body>

	<!-- Topbar Start -->
	<div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
							<img class="logo"
							src="<%=request.getContextPath()%>/frontstage/backend/image/logo.png" />
						</a>
						<h1 class="m-0 display-4 text-primary">Chugether</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Topbar End -->

	<!-- Navbar Start -->
	<div class="container-fluid position-relative nav-bar p-0">
		<div class="container-lg position-relative p-0 px-lg-3"
			style="z-index: 9;">
			<nav
				class="navbar navbar-expand-lg bg-white navbar-light shadow p-lg-0">

				<div class="collapse navbar-collapse justify-content-between"
					id="navbarCollapse">
					<div class="navbar-nav ml-auto py-0">
						<div class="orderblock">
							<a href="#" class="nav-item nav-link active">後台帳戶</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/admin/adminindex.jsp">後台帳戶</a></li>
								<li><a id="logout" style="color: black;" href="#">後台登出</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">會員管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/admin/memberindex.jsp">會員列表</a></li>
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/restaurant/restaurant.html">店家列表</a></li>
							</ul>


						</div>
						<div class="orderblock">
							<a
								href="<%=request.getContextPath()%>/frontstage/backend/announcement/announcement.html"
								class="nav-item nav-link">公告管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/announcement/announcement.html">系統公告</a></li>
							</ul>
						</div>
					</div>

					<div class="navbar-nav mr-auto py-0">
						<div class="orderblock">
							<a
								href="<%=request.getContextPath()%>/frontstage/backend/admin_order_index.jsp"
								class="nav-item nav-link">訂單管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/myorder/admin_order_index.jsp">訂單列表</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a
								href="<%=request.getContextPath()%>/frontstage/backend/customerService/member-reply.html"
								class="nav-item nav-link">客服管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/customerService/member-reply.html">會員信件</a></li>
								<li><a style="color: black;"
									href="<%=request.getContextPath()%>/frontstage/backend/customerService/restaurant-reply.html">餐廳信件</a></li>

							</ul>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->

	<!-- 主要內容區域 -->
	<div id="main-content1"></div>
	<div id="main-content2">

		<!-- 功能內容開始 -->
		<h2>管理員首頁</h2>
		<p>歡迎, ${loggedInAdmin.name}</p>
		<ul>
			<li>
				<button class="btn"
					onclick="location.href='<%=request.getContextPath()%>/admin/admin.do?action=getAll'">查看所有管理員</button>
			</li>
			<li>
				<button class="btn"
					onclick="location.href='<%=request.getContextPath()%>/frontstage/backend/admin/adminregister.jsp'">新增後台員工</button>
			</li>
		</ul>
		<c:if test="${not empty successMessage}">
			<p style="color: green;">${successMessage}</p>
		</c:if>

	</div>
	<!-- 功能內容結束 -->

	<div class="table-container"></div>
	<div id="main-content3"></div>


	<!-- Footer Start -->
	<footer id="footer">
		<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
		<h5 class="footerh5">常見問題</h5>
		<h5 class="footerh5">網站地圖</h5>
		<h5 class="footerh5">使用者條款</h5>
		<h5 class="footerh5">隱私權條款</h5>
		<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
	</footer>
	<!-- Footer End -->

	<script src="js/main.js"></script>
	<script src="../memberLogout/memberLogout.js"></script>
	<script src="../adminLogout/admin-logout.js"></script>

</body>
</html>
