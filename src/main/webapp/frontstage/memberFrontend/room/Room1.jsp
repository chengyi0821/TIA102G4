<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.tia102g4.room.model.*" %>
<%
    Room entity = (Room) request.getAttribute("room");
%>    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>我要揪團</title>
	<!-- 載入 jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<link href="../css/style.css" rel="stylesheet">
	<link href="css/room.css" rel="stylesheet">
	<script src="js/main.js"></script>
	
	<script>
		// 檢查驗證碼長度並顯示提示訊息
		function checkInviteCodeLength() {
			const inviteCode = document.getElementById("inviteCode").value;
			const lengthMessage = document.getElementById("lengthMessage");

			if (inviteCode.length !== 7) {
				lengthMessage.textContent = "驗證碼必須為 7 位數字。";
				lengthMessage.style.color = "red";
			} else {
				lengthMessage.textContent = "";  // 清除訊息
			}
		}
	</script>
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
		<div class="invite-code-container">
			<h2>我要跟團</h2>
			<p>輸入邀請碼加入</p>
			<form action="<%=request.getContextPath()%>/event/event.do" method="post">
				<input type="hidden" name="action" value="getInfo">
				<input type="text" id="inviteCode" name="inviteCode" placeholder="請輸入驗證碼" oninput="checkInviteCodeLength()" required>
				<p id="lengthMessage"></p> <!-- 提示訊息顯示區域 -->
				<button type="submit" class="confirm-btn">確認</button>
				<button type="button" class="back-btn" onclick="window.history.back()">返回</button>
			</form>
		</div>
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
</body>

</html>