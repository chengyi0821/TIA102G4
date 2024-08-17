<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tia102g4.event.model.*"%>
<%@ page import="com.tia102g4.member.model.*"%>
<%@ page import="java.util.List"%>
<%
List<Event> eventList = (List<Event>) request.getSession().getAttribute("eventList");
List<Member> memberList = (List<Member>) request.getSession().getAttribute("memberList");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>訂單管理</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/frontstage/memberFrontend/room/css/room.css"
	rel="stylesheet">
<script src="js/main.js"></script>
<style>
.table-container {
	margin: 50px auto;
	width: 80%;
	max-width: 800px;
	border: 2px solid black;
	border-radius: 8px;
	padding: 20px;
	background-color: #FFFFAA;
}

.table-container table {
	width: 100%;
	border-collapse: collapse;
}

.table-container th, .table-container td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.table-container th {
	background-color: #FFFFAA;
	font-size: 18px;
}

.table-container td {
	font-size: 16px;
}

.confirm-box {
	margin-top: 20px;
	padding: 15px;
	border: 1px solid red;
	background-color: #fff0f0;
	color: red;
	text-align: center;
	font-size: 16px;
}

.button-container {
	margin-top: 30px;
	text-align: center;
}

.button-container button {
	background-color: #C57F00;
	color: white;
	border: none;
	padding: 10px 20px;
	margin: 0 10px;
	cursor: pointer;
	font-size: 16px;
	border-radius: 5px;
}

.button-container button:hover {
	background-color: #b36d00;
}

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
							<a href="#" class="nav-item nav-link active">揪團系統</a>
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
					</div>
				</div>
			</nav>
		</div>
	</div>
	<!-- Navbar End -->

	<!-- 主體內容 -->
	<div class="table-container">
		<h1 class="votetitle">等待投票結果</h1>

		<table>

			<c:forEach var="event" items="${eventList}">
				<tr>
					<th>活動ID:</th>
					<td>${event.eventId}</td>
				</tr>
				<tr>
					<th>活動名稱：</th>
					<td>${event.name}</td>
				</tr>
				<tr>
					<th>活動日期：</th>
					<td>${event.date}</td>
				</tr>
				<tr>
					<th>活動時間：</th>
					<td>${event.time}</td>
				</tr>
				<tr>
					<th>活動資訊：</th>
					<td>${event.info}</td>
				</tr>
			</c:forEach>
			<!-- 下面的票數即時統計要從redis撈資料 -->
			<div class="count"
				style="display: flex; justify-content: space-between;">
				<c:forEach var="rest" items="${voteOptions}">
					<div style="width: 30%;" class="voteResults">
						<div>
							<h5>餐廳名稱：${rest.restName}</h5>
							<span id="option:${rest.restId}Votes">0</span> <span>票</span>
						</div>

					</div>

				</c:forEach>
			</div>
		</table>

		<div class="confirm-box">
			內容:確定後進行位置確認,如不想參加可取消揪團(需要登入,刪除請到訂單管理)!!</div>

		<div class="orderrest" style="display: none;">
			<div id="selectedRestName"></div>
			<div class="button-container">
				<form action="<%=request.getContextPath()%>/myorder/membermyorder.do" method="post">
				<input type="hidden" name="action" value="addOrder"> <input
					type="submit" value="出席" style="display: none;">
				</form>
			
				<form action="<%=request.getContextPath()%>/event/event.do" method="post">
				<input type="hidden" name="action" value="noshow"> <input
					type="submit" value="婉拒" style="display: none;">
				</form>
				
				<button onclick="submitForm('addOrder')">出席</button>
<button onclick="submitForm('noshow')">婉拒</button>
			</div>
		</div>


	</div>

	<!-- Footer Start -->
	<footer id="footer">
		<h5 class="footerh5_1">Chugether 揪來鳩去有限公司</h5>
		<h5 class="footerh5">常見問題</h5>
		<h5 class="footerh5">網站地圖</h5>
		<h5 class="footerh5">使用者條款</h5>
		<h5 class="footerh5">隱私權條款</h5>
		<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
	</footer>
	<!-- Footer End -->
	<script src="<%= request.getContextPath() %>/frontstage/memberFrontend/room/js/main.js"></script>
	<script>
	function submitForm(action) {
	    if (action === 'addOrder') {
	        document.querySelector('form[action*="/myorder/membermyorder.do"]').submit();
	    } else if (action === 'noshow') {
	        document.querySelector('form[action*="/event/event.do"]').submit();
	    }
	}

	</script>
	<script>
    const maxSeat = <%=request.getSession().getAttribute("maxSeat")%>;

function updateVoteCounts() {
    $.ajax({
        url: '<%= request.getContextPath() %>/vote/count.do',
        method: 'GET',
        success: function(data) {
            console.log(data);
            const voteCounts = typeof data === 'object' ? data : JSON.parse(data);
            
            // 初始化總票數
            let totalVotes = 0;

            // 遍歷所有投票選項
            <c:forEach var="rest" items="${voteOptions}">
                var optionKey = 'option:${rest.restId}';
                if(voteCounts.hasOwnProperty(optionKey)){
                    $('#option\\:${rest.restId}Votes').text(voteCounts[optionKey]);
                    console.log('EL內'+voteCounts[optionKey]);
                    
                    // 將當前選項的票數加到總數中
                    totalVotes += parseInt(voteCounts[optionKey], 10);
                }
            </c:forEach>

            // 輸出總票數
            console.log('總票數：', totalVotes);

            if(totalVotes >= maxSeat){
            	$('.orderrest').show();
            	$('.votetitle').text('投票結束');
            	$('.confirm-box').text('以最高票數餐廳為主，按下「出席」後可查看訂單預覽，若是「婉拒」則視同不出席活動');
            }
        }
    });
}

// 每 5 秒執行一次 updateVoteCounts 函數
setInterval(updateVoteCounts, 5000);

// 頁面加載完成後立即執行一次
$(document).ready(updateVoteCounts);
	</script>
</body>

</html>
