<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="com.tia102g4.admin.model.*" %>
<%
    Admin entity = (Admin) request.getAttribute("admin");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>我要揪團</title>
	<!-- 載入 jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<link href="<%=request.getContextPath() %>/frontstage/backend/css/style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/frontstage/backend/admin/css/admin.css" rel="stylesheet" />
	<script src="js/main.js"></script>
	
</head>

<body>
	  <!-- Topbar Start -->
	  <div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
							<img class="logo" src="../image/logo.png">
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
		<style>
            .container-fluid.position-relative.nav-bar.p-0 li {
                color: black !important;
            }
			</style>
		<div class="container-lg position-relative p-0 px-lg-3" style="z-index: 9;">
			<nav class="navbar navbar-expand-lg bg-white navbar-light shadow p-lg-0">
				
				<div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
					<div class="navbar-nav ml-auto py-0">
					  <div class="orderblock">  <a href="#" class="nav-item nav-link">&nbsp&nbsp&nbsp首頁 </a> 
						<ul class="orderlist">
							<li><a style="color: black;" href="#">會員登入</a></li>
							<li><a style="color: black;" href="#">最新消息</a></li>
							<li><a id="logout" style="color: black;" href="#">登出會員</a></li>
						</ul>
					  </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">會員專區</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">會員資料</a></li>
							<li><a style="color: black;" href="#">查看收藏</a></li>
						</ul>
	
	
					   </div>
					  <div class="orderblock"> <a href="#" class="nav-item nav-link">店家介紹</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">查看店家</a></li>
							<li><a style="color: black;" href="#">查看評價</a></li>
							<li><a style="color: black;" href="#">營業資訊</a></li>
							<li><a style="color: black;" href="#">類別搜尋</a></li>
							
						</ul>
					  </div> 
					</div>
				
					<div class="navbar-nav mr-auto py-0">
					   <div class="orderblock"> <a href="#" class="nav-item nav-link active">揪團系統</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">發起揪團</a></li>
							<li><a style="color: black;" href="#">參與揪團</a></li>
						</ul>
					   </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">訂單管理</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">編輯訂單</a></li>
							<li><a style="color: black;" href="#">取消訂單</a></li>
							<li><a style="color: black;" href="#">餐後評論</a></li>
						</ul>
					</div>
					<div class="orderblock"> <a href="#" class="nav-item nav-link">聯絡客服</a>
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
		<h2>後台註冊</h2>
    <form action="<%=request.getContextPath()%>/admin/admin.do" method="post">
        <input type="hidden" name="action" value="register"/>
        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="account">帳號:</label>
        <input type="text" id="account" name="account" required><br>
        
        <label for="password">密碼:</label>
        <input type="password" id="password" name="password" required><br>
        
        <label for="permission">權限:</label>
        <input type="text" id="permission" name="permission" required><br>
    
        
        <input type="submit" value="註冊" >
        
    </form>
    
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
	</div>
	<div class="table-container">

	</div>
	<div id="main-content3">

	</div>

	<!-- Footer Start -->
		<footer id="footer">
			<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
			<h5 class="footerh5">常見問題</h5>
			<h5 class="footerh5">網站地圖</h5>
			<h5 class="footerh5">使用者條款</h5>
			<h5 class="footerh5">隱私權條款</h5>
			<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
		</footer>
	<!-- Footer end -->
		<script src="js/main.js"></script>
		<script src="../memberLogout/memberLogout.js"></script>
</body>

</html>