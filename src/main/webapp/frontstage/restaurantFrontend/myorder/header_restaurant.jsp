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
   href="<%=request.getContextPath() %>/frontstage/backend/myorder/css/style.css">
    <title>訂單管理</title>
       <meta content="width=device-width, initial-scale=1.0" name="viewport">
       <meta content="Free HTML Templates" name="keywords">
       <meta content="Free HTML Templates" name="description">
   
       <!-- Favicon -->
       <link href="img/favicon.ico" rel="icon">
   
       <!-- Google Web Fonts -->
       <link rel="preconnect" href="https://fonts.gstatic.com">
       <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
   
       <!-- Font Awesome -->
       <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
   
       <!-- Libraries Stylesheet -->
       <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
       <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
   
       <!-- Customized Bootstrap Stylesheet -->
       <link href="css/style.css" rel="stylesheet">
<style>


</style>
</head>

<body style="background-color: #F3F6FF;;">
	  <!-- Topbar Start -->
	  <div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
							<img class="logo" src="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/image/logo.png">
							<h1 class="m-0 display-4 text-primary">Chugether</h1>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- Topbar End -->
	 
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
					  <div class="orderblock">  <a href="#" class="nav-item nav-link">會員中心</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">修改資料</a></li>
							<li><a style="color: black;" href="#">登出會員</a></li>
						</ul>
					  </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">店家資訊</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">關於我們</a></li>
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/restNews/restNews.html">最新消息</a></li>
							<li><a style="color: black;" href="#">菜單瀏覽</a></li>
							<li><a style="color: black;" href="#">營業資訊</a></li>
							<li><a style="color: black;" href="#">顧客評價</a></li>
						</ul>
	
	
					   </div>
					  <div class="orderblock"> <a  href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/myorder/restaurant_order_index.jsp" class="nav-item nav-link active">訂單系統</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/myorder/restaurant_order_index.jsp">訂單管理</a></li>
							<li><a style="color: black;" href="<%=request.getContextPath() %>/blacklist/blacklist.do?action=getAll">黑名單</a></li>
						</ul>
					  </div> 
					</div>
				
					<div class="navbar-nav mr-auto py-0">
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">聯絡客服</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/restFeedback/restFeedback.html">意見反應</a></li>
							<li><a style="color: black;" href="#">系統公告</a></li>
						</ul>
					   </div>
					  
			</nav>
		</div>
	</div>
	<!-- Navbar End -->

</body>

</html>