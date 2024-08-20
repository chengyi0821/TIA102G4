<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.tia102g4.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員管理</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/css/style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/css/member.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/css/memberForgotPassword.css"
	rel="stylesheet" />
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
							src="<%=request.getContextPath()%>/frontstage/memberFrontend/image/logo.png" />
							<h1 class="m-0 display-4 text-primary">Chugether</h1>
						</a>
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
							<a
								href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/memberlogin.jsp"
								class="nav-item nav-link">會員登入 </a>
						</div>
						<div class="orderblock">
							<a href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/memberregister.jsp" class="nav-item nav-link">註冊會員</a>
						</div>

					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->

	<div id="main-content1"></div>

	<!-- 這裡是放入會員列表的主要區域 -->
	<div id="main-content2">
		<h2>忘記密碼</h2>
		<form action="<%=request.getContextPath()%>/member/member.do"
			method="post">
			<input type="hidden" name="action" value="forgotPassword" /> <label
				for="name">姓名:</label> <input type="text" id="name" name="name"
				required><br> <label for="email">Email:</label> <input
				type="email" id="email" name="email" required><br> <label
				for="mobile">手機號碼:</label> <input type="text" id="mobile"
				name="mobile" required><br> <input type="submit"
				value="送出">
		</form>

		<c:if test="${not empty errorMessage}">
			<p style="color: red;">${errorMessage}</p>
		</c:if>

		<c:if test="${not empty successMessage}">
			<p style="color: green;">${successMessage}</p>
		</c:if>
	</div>


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

	<script src="<%=request.getContextPath()%>/js/main.js"></script>
	<script src="../memberLogout/memberLogout.js"></script>
</body>
</html>

