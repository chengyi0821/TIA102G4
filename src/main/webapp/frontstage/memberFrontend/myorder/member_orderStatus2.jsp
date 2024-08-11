<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/frontstage/backend/myorder/css/style.memberorder.css">
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
.left_content1, .right_content1 {
	width: 430px;
	height: 100%;
}

.main_content1 {
	width: 800px;
	height: 100%;
	background-color: #fffbeb;
	border-radius: 0.5cm;
}

.favorite {
	border: 4px solid #ccc;
	border-top: none;
	border-left: none;
	border-right: none;
	border-radius: 15px;
	width: 70%;
	height: 60px;
	font-size: 20px;
	padding-top: 13%;
	margin-top: 5px;
	margin-left: 60px;
}

.alert {
	margin-top: 10px;
	padding: 10px;
	border-radius: 5px;
}

.alert-success {
	color: white;
	background-color: #ffc107;;
}

.alert-danger {
	color: white;
}

#successMessage, #errorMessage {
	background-color: #ffd967;
	border: 1px solid #ffd967;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.1);
	font-size: 20px;
	font-weight: 500;
	color: black;
}
</style>
</head>

<body style="background-color: #F3F6FF;">

	<jsp:include page="header_member.jsp"></jsp:include>

	<div class="bodycontent">
		<div class="left_content1"></div>
		<div class="main_content1" style="background-color: #F3F6FF;">
			<button class="member_reser1">
				<a style="color: black; text-decoration: none;"
					href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus1Mem">預約訂單</a>
			</button>
			<button class="member_reser2" style="background-color: #ffc107;">
				<a style="color: black; text-decoration: none;"
					href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus2Mem">訂單已取消</a>
			</button>
			<button class="member_reser3">
				<a style="color: black; text-decoration: none;"
					href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus3Mem">訂單已完成</a>
			</button>
			<br>
			<div class="all_reser">

				<!-- 显示成功或错误消息 -->

				<c:if test="${not empty successMessage}">
					<div id="successMessage" class="alert alert-success">
						<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23"
							fill="currentColor" class="bi bi-check2-circle"
							viewBox="0 0 16 16">
  				<path
								d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0" />
  				<path
								d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z" />
				</svg>
						&nbsp${successMessage}
					</div>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<div id="errorMessage" class="alert alert-danger">
						<svg xmlns="http://www.w3.org/2000/svg" width="23" height="23"
							fill="currentColor" class="bi bi-chat-right-heart"
							viewBox="0 0 16 16">
  				<path
								d="M2 1a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h9.586a2 2 0 0 1 1.414.586l2 2V2a1 1 0 0 0-1-1zm12-1a2 2 0 0 1 2 2v12.793a.5.5 0 0 1-.854.353l-2.853-2.853a1 1 0 0 0-.707-.293H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2z" />
  				<path
								d="M8 3.993c1.664-1.711 5.825 1.283 0 5.132-5.825-3.85-1.664-6.843 0-5.132" />
				</svg>
						&nbsp${errorMessage}
					</div>
				</c:if>

				<c:forEach var="myorder" items="${orderList}">
					<div class="reserform">
						<div class="reser_form_feft">
							<div class="vertical">
								訂<br>
								<br>單<br>
								<br>編<br>
								<br>號<br>
								<br>${myorder.orderId}
							</div>
						</div>
						<div class="reser_form_main">
							<div class="event_name">${myorder.event.info}</div>
							<div class="column1_2">
								<div class="column1">
									<div class="member_name1">主揪人</div>
									<div class="member_name2">${myorder.member.name}</div>
									<div class="member_name1">日期</div>
									<div class="member_name2">${myorder.reserDate}</div>
									<div class="member_name1">時間</div>
									<div class="member_name2">${fn:substring(myorder.reserTime, 0, 5)}</div>
									<div class="member_name1">人數</div>
									<div class="member_name2">${myorder.reserPeopleNumber}</div>
								</div>
								<div class="column2">
									<div class="rest_name1">餐廳</div>
									<div class="rest_name2">${myorder.restaurant.restName}</div>
									<div class="rest_name1">備註</div>
									<div class="rest_name2"
										style="height: 93px; padding-top: 25px;">
										${myorder.reserNote}</div>
								</div>
							</div>
						</div>
						<div class="reser_form_right" style="padding-top: 10px;">
							<img src="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/image/woklogo.png" alt=""
								style="width: 165px; height: 80px; padding-left: 10px;">
							<div class="search_rest">
								<a href="" style="color: black; text-decoration: none;">查看餐廳</a>
							</div>
							<div class="edit_cancel">
								<form
									action="<%=request.getContextPath() %>/favorite/favorite.do"
									method="post"
									onsubmit="this.querySelector('button').disabled = true;">
									<input type="hidden" name="action" value="addFav"> <input
										type="hidden" name="restId"
										value="${myorder.restaurant.restId}">
									<button type="submit" class="edit-button favorite">收藏餐廳</button>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="right_content1"></div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<script>
		window.onload = function() {
			setTimeout(function() {
				var successMessage = document.getElementById('successMessage');
				var errorMessage = document.getElementById('errorMessage');

				if (successMessage) {
					successMessage.style.display = 'none';
				}

				if (errorMessage) {
					errorMessage.style.display = 'none';
				}
			}, 3000);
		};
	</script>


</body>

</html>
