<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.tia102g4.member.model.*"%>
<%
Member entity = (Member) request.getAttribute("member");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>會員登入</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/css/style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/css/member.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/css/memberLogin.css"
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
							<a
								href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/memberlogin.jsp"
								class="nav-item nav-link active">會員登入 </a>
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

	<!-- 主要內容區域 -->
	<div id="main-content1"></div>
	<div id="main-content2">
		<!-- 會員登入功能內容開始 -->
		<div id="login-container">
			<h2 id="login-title">會員登入</h2>
			<form id="login-form"
				action="<%=request.getContextPath()%>/member/member.do"
				method="post">
				<input type="hidden" name="action" value="login" />

				<div class="form-group">
					<label for="email">信箱:</label> <input type="email" id="email"
						name="email" required>
				</div>

				<div class="form-group">
					<label for="password">密碼:</label> <input type="password"
						id="password" name="password" required>
				</div>

				<input id="login-button" type="submit" value="登入">

				<c:if test="${not empty errorMessage}">
					<p id="error-message">${errorMessage}</p>
				</c:if>

				<div id="links">
					<a
						href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/memberregister.jsp"
						id="regismember">註冊會員</a> | <a
						href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/memberforgotpassword.jsp"
						id="forgotPassword">忘記密碼?</a>
				</div>
			</form>
		</div>
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
	<script src="../memberLogout/memberLogout.js"></script>
</body>

</html>
