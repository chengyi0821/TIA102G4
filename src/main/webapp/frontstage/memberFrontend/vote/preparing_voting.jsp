<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tia102g4.rest.model.*"%>
<%@ page import="java.util.List"%>
<%
List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("restaurantList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投票</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/css/style.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/css/orderlist.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/css/preparing.css"
	rel="stylesheet" />
<style>

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
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->
	<div id="main-content1"></div>
		<div id="main-content2">
			<h3>可以選擇的餐廳如下</h3>
			
			<form action="<%=request.getContextPath() %>/event/event.do" method="post">
				<div>
					<input type="text" name="restName" >
					<input type="hidden" name="action" value="compositeQuery">
					<input type="submit" value="查詢餐廳">
				</div>
			</form>
			<form
				action="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/option.do"
				method="post">
				<input type="hidden" name="action" value="choice">
				<table>
					<tr>
						<th>餐廳名稱</th>
						<th>餐廳簡介</th>
						<th>餐廳地址</th>
						<th>餐廳電話</th>
						<th>選取</th>
					</tr>
					<c:forEach var="rest" items="${restaurantList }">
						<tr>
							<td>${rest.restName}</td>
							<td>${rest.description}</td>
							<td>${rest.location}</td>
							<td>${rest.phone}</td>
							<td><label> 
									<input type="checkbox" name="restchoice" 
      								value="option:${rest.restId}"
       						${param['restchoice'].contains('option:'.concat(rest.restId)) ? 'checked' : ''}/>
							</label></td>
							
						</tr>
					</c:forEach>
				</table>
				<br /> <input type="submit" value="選這些餐廳" id="btn_choose">
			</form>

		</div>
		<div class="table-container"></div>

		<div id="main-content3" style="width: 400px;"></div>
		<footer id="footer">
			<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
			<h5 class="footerh5">常見問題</h5>
			<h5 class="footerh5">網站地圖</h5>
			<h5 class="footerh5">使用者條款</h5>
			<h5 class="footerh5">隱私權條款</h5>
			<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
		</footer>
		<script src="<%=request.getContextPath() %>/frontstage/memberFrontend/memberLogout/memberLogout.js"></script>
		<script>
			const contextPath = "<%=request.getContextPath()%>
			";
		</script>
		<script
			src="<%=request.getContextPath()%>/frontstage/memberFrontend/vote/js/restaurant_selector.js"></script>
		
</body>
</html>