<%@ page language="java" contentType="text/html; charset=UTF-8
	"
	pageEncoding="UTF-8"%>

<%@ page import="com.tia102g4.member.model.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
Member entity = (Member) request.getAttribute("member");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>我要揪團</title>

<!-- 載入 jQuery -->

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<link href="<%=request.getContextPath() %>/frontstage/memberFrontend/css/style.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/frontstage/memberFrontend/member/css/member.css" rel="stylesheet" />

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
							<img class="logo" src="<%=request.getContextPath() %>/frontstage/memberFrontend/image/logo.png" /> 
						</a>
						<h1 class="m-0
display-4 text-primary">Chugether</h1>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- Topbar End -->

	<!-- Navbar Start -->

	<!-- Navbar Start -->
	<div class="container-fluid position-relative nav-bar p-0">
		<div class="container-lg position-relative p-0 px-lg-3" style="z-index: 9;">
			<nav class="navbar navbar-expand-lg bg-white navbar-light shadow p-lg-0">
				
				<div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
					<div class="navbar-nav ml-auto py-0">
					  <div class="orderblock">  <a href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberHome/memberHome.html" class="nav-item nav-link">&nbsp&nbsp&nbsp首頁 </a> 
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/member/memberlogin.jsp">會員登入</a></li>
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberNews/memberNews.html">最新消息</a></li>
							<li><a id="logout" style="color: black;" href="#">登出會員</a></li>
						</ul>
					  </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">會員專區</a>
						<ul class="orderlist">

							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/favorite/favoriteList.jsp">查看收藏</a></li>
						</ul>
	
	
					   </div>
					
					</div>
				
					<div class="navbar-nav mr-auto py-0">
					   <div class="orderblock"> <a href="#" class="nav-item nav-link active">揪團系統</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/room/inviteroom.jsp">發起揪團</a></li>
						</ul>
					   </div>
					   <div class="orderblock"> <a href="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/my_order_index.jsp" class="nav-item nav-link">訂單管理</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/member_orderStatus1.jsp">編輯訂單</a></li>
							<li><a style="color: black;" href="#">取消訂單</a></li>
						</ul>
					</div>
					<div class="orderblock"> <a href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberFeedback/memberFeedback.html" class="nav-item nav-link">聯絡客服</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberFeedback/memberFeedback.html">客服信箱</a></li>
							
						
							
						</ul>
				</div>
			</nav>
		</div>
	</div>

	<!-- Navbar End -->

	<!-- 主要內容區域 -->

	<div id="main-content1"></div>
	<div id="main-content2">

		<!-- 功能內容開始 -->

		<h1>員工列表</h1>
		<form action="<%=request.getContextPath()%>/member/member.do" method="post">
		<table style="width: 100%; text-align: center;">
			
				<tr>
					<th>會員編號</th>

					<th>註冊日期</th>
					<th>會員姓名</th>
					<th>帳號</th>

					<th>密碼</th>
					<th>信箱</th>
					<th>性別</th>

					<th>手機號碼</th>
					<th>頭貼</th>
					<th>狀態</th>
				</tr>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.memberId}</td>

						<td>${member.regisDate}</td>
						<td>${member.name}</td>

						<td>${member.account}</td>
						<td>${member.password}</td>

						<td>${member.email}</td>
						<td>${member.gender}</td>

						<td>${member.mobileNo}</td>
						<td>${member.sticker}</td>

						<td>${member.accStatus}</td>
					</tr>
				</c:forEach>
				
		</table>
	</div>
	<div class="table-container"></div>
	<div id="main-content3"></div>
    <a href="<%=request.getContextPath()%>/frontstage/memberFrontend/member/memberindex.jsp">回首頁</a>
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


</body>
</html>
