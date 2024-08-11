<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>客服管理</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="../css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="
    <form class="form-container" action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post" onsubmit="showSuccessMessage();">
     <h2>編輯訂單</h2>/frontstage/backend/myorder/css/style.css">
<title>訂單管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="css/style.css" rel="stylesheet">
<style>
.bodycontent {
	height: 600px;
}

.main_content1 {
	width: 800px;
	height: 600px;
	/* box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);  */
	/* border:2px solid #ffc107; */
	background-color: #fffbeb;
	border-radius: 0.5cm;
}

.member_reser1 {
	border: 4px solid #ffc107;
	border-left: none;
	border-right: none;
	height: 55px;
	width: 250px;
	border-radius: 10px;
	font-size: 25px;
	text-align: center;
	margin-top: 20px;
	margin-left: 0px;
	background-color: #F3F6FF;
	margin-right: 10px;
	margin-bottom: 20px;
}

.member_reser2 {
	border: 4px solid #ffc107;
	border-left: none;
	border-right: none;
	height: 55px;
	width: 250px;
	border-radius: 10px;
	font-size: 25px;
	text-align: center;
	margin-top: 20px;
	margin-left: 0px;
	background-color: #F3F6FF;
	margin-left: 10px;
	margin-right: 10px;
	margin-bottom: 20px;
}

.member_reser3 {
	border: 4px solid #ffc107;
	border-left: none;
	border-right: none;
	height: 55px;
	width: 250px;
	border-radius: 10px;
	font-size: 25px;
	text-align: center;
	margin-top: 20px;
	margin-left: 0px;
	background-color: #F3F6FF;
	margin-left: 10px;
	margin-bottom: 20px;
}

.member_reser1:hover, .member_reser2:hover, .member_reser3:hover {
	background-color: #ffc107;
}

/* ==============================reserfrom======================================= */
.reserform {
	/* border:1px solid #ffd967; */
	width: 800px;
	height: 600px;
	margin-left: 0px;
	margin-right: auto;
	margin-top: 15px;
	display: flex;
	flex-wrap: nowrap;
	/* background-color:#fddd7d; */
	/* box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); */
	border-radius: 10px;
}
</style>
</head>
<body style="background-color: #F3F6FF;">
	<!-- Topbar Start -->
	<div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
							<img class="logo" src="image/logo.png">
							<h1 class="m-0 display-4 text-primary">Chugether</h1>
						</a>
					</div>
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
								<li><a style="color: black;" href="
    <form class="form-container" action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post" onsubmit="showSuccessMessage();">
     <h2>編輯訂單</h2>/favorite/favorite.do?action=getFav">查看收藏</a></li>
							</ul>


						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">店家介紹</a>
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
							<a href="
    						<form class="form-container" action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post" onsubmit="showSuccessMessage();">
     						<h2>編輯訂單</h2>/myorder/membermyorder.do?action=addOrder" class="nav-item nav-link">揪團系統</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="#">發起揪團</a></li>
								<li><a style="color: black;" href="#">參與揪團</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="
    						<form class="form-container" action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post" onsubmit="showSuccessMessage();">
     						<h2>編輯訂單</h2>/frontstage/memberFrontend/myorder/member_order_index.jsp" class="nav-item nav-link  active">訂單管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="
   				 				<form class="form-container" action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post" onsubmit="showSuccessMessage();">
     							<h2>編輯訂單</h2>/myorder/membermyorder.do?action=getOrderStatus1Mem">編輯訂單</a></li>
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
	<div class="bodycontent">
		<div class="left_content1"></div>
		<div class="main_content1" style="background-color: #F3F6FF;">

			<button class="member_reser1">
				<a style="color: black; text-decoration: none;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus1Mem">預約訂單</a>
			</button>
			<button class="member_reser2">
				<a style="color: black; text-decoration: none;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus2Mem">訂單已取消</a>
			</button>
			<button class="member_reser3">
				<a style="color: black; text-decoration: none;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus3Mem">訂單已完成</a>
			</button>

			<br>

			<div class="reserform">
				<video src="img/My ReserVation.mp4" autoplay loop muted playsinline
					style="width: 800px; height: 500px;"></video>

			</div>
		</div>



	</div>
	<div class="right_content1"></div>
	</div>
	<footer id="footer">
		<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
		<h5 class="footerh5">常見問題</h5>
		<h5 class="footerh5">網站地圖</h5>
		<h5 class="footerh5">使用者條款</h5>
		<h5 class="footerh5">隱私權條款</h5>
		<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
	</footer>
</body>

</html>
