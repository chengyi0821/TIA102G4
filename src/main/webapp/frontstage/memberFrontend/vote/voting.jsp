<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.tia102g4.event.model.*"%>
<%@ page import="com.tia102g4.member.model.*"%>
<%@ page import="com.tia102g4.rest.model.*" %>
<%@ page import="java.util.List"%>
<%
List<Event> eventList = (List<Event>) request.getSession().getAttribute("eventList");
List<Member> memberList = (List<Member>) request.getSession().getAttribute("memberList");
List<Restaurant> restList = (List<Restaurant>) request.getAttribute("restList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>跟團資訊</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/css/style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/css/orderlist.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container-fluid bg-primary py-3 d-none d-md-block">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
					<div class="d-inline-flex align-items-center">
						<a href="../announcement/anno.html"
							class="navbar-brand mx-5 d-none d-lg-block"></a> <img id="logo"
							src="<%=request.getContextPath()%>/frontstage/memberFrontend/image/logo.png" />
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
							<a href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberHome/memberHome.html" class="nav-item nav-link">&nbsp&nbsp&nbsp首頁 </a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/member/memberlogin.jsp">會員登入</a></li>
								<li><a id="logout" style="color: black;" href="#">登出會員</a></li>
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberNews/memberNews.html">最新消息</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">會員專區</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/favorite/favoriteList.jsp">查看收藏</a></li>
							</ul>


						</div>
					</div>

					<div class="navbar-nav mr-auto py-0">
						<div class="orderblock">
							<a href="#" class="nav-item nav-link active">揪團系統</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/room/inviteroom.jsp">發起揪團</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">訂單管理</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/member_orderStatus1.jsp">編輯訂單</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link">聯絡客服</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberFeedback/memberFeedback.html">客服信箱</a></li>

							</ul>
						</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->
	<div id="main-content1"></div>
	<div id="main-content2">
		<h1>請選擇餐廳</h1>
		<form action="option.do" method="post">
			
		<div style="display: flex; justify-content: space-between;">
			<c:forEach var="restaurant" items="${voteOptions}" varStatus="status">
				<div style="width: 30%;" class="option">
					<h3>餐廳名稱：${restaurant.restName}</h3>
					<p>簡介：${restaurant.description}</p>
					<input type="radio" name="selectedOption" value="option:${restaurant.restId}" required style="display:none;">
				</div>
				<c:if test="${status.count % 3 == 0 || status.last}">
		</div>
		<div style="display: flex; justify-content: space-between;">
			</c:if>
			</c:forEach>
		</div>
		
			<input type="hidden" name="action" value="choose">
<!-- 			<input type="submit" value="投票"> -->
			<div id="submitButton" class="submit-button" style="display: none">
				投票
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
	<script>
	document.addEventListener("DOMContentLoaded", function () {
		const options = document.querySelectorAll(".option");
		const submitButton = document.getElementById("submitButton");

		options.forEach((option) => {
			option.addEventListener("click", function () {
				const radio = this.querySelector('input[type="radio"]');

				if (radio.checked) {
					radio.checked = false;
					this.classList.remove("selected");
					submitButton.style.display = "none";
				} else {
					options.forEach((opt) => {
						opt.classList.remove("selected");
						opt.querySelector('input[type="radio"]').checked = false;
					});

					radio.checked = true;
					this.classList.add("selected");
					submitButton.style.display = "block";
				}
			});
		});

		submitButton.addEventListener("click", function () {
			const form = document.querySelector("form");
			if (form) {
				form.submit();
			}
		});
	});
	</script>
	<script src="<%=request.getContextPath() %>/frontstage/memberFrontend/memberLogout/memberLogout.js"></script>
</body>
</html>