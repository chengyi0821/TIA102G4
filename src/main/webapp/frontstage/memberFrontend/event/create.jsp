<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="com.tia102g4.event.model.*" %>
<%
	Event entity = (Event) request.getAttribute("event");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>建立揪團</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="<%=request.getContextPath() %>/frontstage/memberFrontend/css/style.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/frontstage/memberFrontend/event/css/orderlist.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/frontstage/memberFrontend/event/css/asidemain.css" rel="stylesheet" />

</head>
<body>

	<div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="../announcement/anno.html"
							class="navbar-brand mx-5 d-none d-lg-block"></a> <img id="logo"
							src="<%=request.getContextPath() %>/frontstage/memberFrontend/image/logo.png" />
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

				<div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
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
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/event/create.jsp">發起揪團</a></li>
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/event/gate.jsp">參與揪團</a></li>
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
						</div>
						</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->
	<div id="main-content1"></div>
	<div id="main-content2">
		<h2 style="margin-top: 40px">發起揪團</h2>
		<form action="<%=request.getContextPath()%>/event/event.do"
			method="post">
			<!--/TIA102G4/event/event.do -->
			<aside class="aside">
				<label for="date" style="margin:10px;">訂餐日:(3天後~30天前)</label>
				<br> 
				<input type="date" id="dateRange"
					name="date" max="2024-09-04" min="2024-08-04" required style="margin:10px;"/> 
					<br> 
					<label style="margin:10px;"
					for="time">用餐時段:(11:00~21:00)</label> <input type="time" name="time" required min="11:00" max="21:00">
			</aside>
			<main class="main">
				<label for="name" style="margin:10px;">活動名稱:</label> <br> <input type="text"
					id="name" name="name" required style="margin:10px;"/> <br> <label for="info" style="margin:10px;">活動詳細資訊:</label>
				<br>
				<textarea id="info" name="info" rows="4" cols="50" required style="margin:10px;"></textarea>
				<br> <label for="maxseat" style="margin:10px;">最大參與人數(2~20人)</label><br> <input
					type="number" name="maxseat" min=2 max=20 style="margin:10px;"> <br>
			</main>
			<div>
				<input type="hidden" name="action" value="add"> <input
					type="submit" value="創立揪團">
			</div>

		</form>
	</div>

	<div class="table-container"></div>

	<div id="main-content3"></div>
	<footer id="footer">
		<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
		<h5 class="footerh5">常見問題</h5>
		<h5 class="footerh5">網站地圖</h5>
		<h5 class="footerh5">使用者條款</h5>
		<h5 class="footerh5">隱私權條款</h5>
		<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
	</footer>
	<script src="<%=request.getContextPath() %>/frontstage/memberFrontend/event/js/daterange.js"></script>
	
</body>
</html>