<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tia102g4.event.model.*" %>
<%@ page import="com.tia102g4.member.model.*" %>
<%@ page import="java.util.List" %>
<%
	List<Event> eventList =  (List<Event>)request.getSession().getAttribute("eventList");
	List<Member> memberList = (List<Member>)request.getSession().getAttribute("memberList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>跟團資訊</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="<%=request.getContextPath() %>/frontstage/memberFrontend/css/style.css" rel="stylesheet" />
<style>
#main-content2 {
	border: 1px solid black;
}

#main-content2 aside.aside {
	width: 400px;
	height: 300px;
	display: block;
}
#main-content2 aside.aside table{
	width: 100%; 
	border-collapse: collapse;
}

#main-content2 aside.aside table tr td,
#main-content2 main.main table tr td{
	display: block;
	border: 1px solid orange;
	width: 100%;
	text-align: left;
	vertical-align: top; word-wrap: break-word; padding: 10px;
}

#main-content2 main.main {
	width: 500px;
	display: block;
	height: 300px
}

#main-content2 aside.aside, #main-content2 main.main {
	border: 1px solid green;
	display: inline-block;
	vertical-align: top;
	font-size: 30px;
}
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
							<a href="#" class="nav-item nav-link active" >揪團系統</a>
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
		<h2 style="margin-top: 40px">等待室</h2>
		<br>
		<h2 >等待其他成員進入</h2>

		<form action="<%=request.getContextPath()%>/event/event.do"
			method="post">
			<!--/TIA102G4/event/event.do -->
			<aside class="aside">
			<h3>成團資訊</h3>
			<table>
				<c:forEach var="event" items="${eventList}">
					<tr>
						<td>主揪者：${event.memberId}</td>
						<td>活動名稱：${event.name}</td>
						<td>活動日期：${event.date}</td>
						<td>活動時間：${event.time}</td>
						<td>活動資訊：${event.info}</td>
						<td>人數上限：${event.maxSeat}</td>
					</tr>
				</c:forEach>
			</table>
			
			</aside>
			<main class="main">
				<h3>參與成員</h3>
				<table>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>參與者：${member.memberId}</td>
					</tr>
				</c:forEach>
			</table>
			</main>
			<div>
				<input type="hidden" name="action" value="add"> <input
					type="submit" value="選餐廳">
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
	<script src="js/main.js"></script>
</body>
</html>