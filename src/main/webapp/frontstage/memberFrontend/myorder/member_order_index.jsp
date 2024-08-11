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
	<jsp:include page="header_member.jsp"></jsp:include>
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
				<video src="image/My ReserVation.mp4" autoplay loop muted playsinline
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
