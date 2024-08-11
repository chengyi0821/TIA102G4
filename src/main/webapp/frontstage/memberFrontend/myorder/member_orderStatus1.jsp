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


</style>
</head>

<body style="background-color: #F3F6FF;">
	<!-- 自定义警示框 -->
	<div id="customAlert" class="custom-alert">
		<p>您確定要取消訂單嗎？</p>
		<div class="custom-alert-buttons">
			<button class="confirm1" onclick="showConfirmationMessage()">確定</button>
			<button class="cancel1" onclick="closeCustomAlert()">取消</button>
		</div>
	</div>

	<div id="confirmationMessage" class="confirmation-message">
		<p class="cancel_message">訂單已取消！</p>
	</div>

	<!--    ================================================================ -->

	<jsp:include page="header_member.jsp"></jsp:include>
	<div class="bodycontent">
		<div class="left_content1"></div>
		<div class="main_content1" style="background-color: #F3F6FF;">
			<button class="member_reser1" style="background-color:#ffc107;"><a style="color: black; text-decoration: none;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus1Mem">預約訂單</a></button>
       		<button class="member_reser2"><a style="color: black; text-decoration: none;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus2Mem">訂單已取消</a></button>
       		<button class="member_reser3"><a style="color: black; text-decoration: none;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus3Mem">訂單已完成</a></button>
			<br>
			<div class="all_reser">
				<c:forEach var="myorder" items="${orderList}">
					<div class="reserform">
						<div class="reser_form_feft">
							<div class="vertical">
								訂<br> <br>單<br> <br>編<br> <br>號<br>
								<br>${myorder.orderId}</div>
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
										style="height: 93px; padding-top: 25px;">${myorder.reserNote}</div>
								</div>
							</div>
						</div>
						<div class="reser_form_right" style="padding-top: 10px;">
							<img src="../img/woklogo.png" alt=""
								style="width: 165px; height: 80px; padding-left: 10px;">
							<div class="search_rest">
								<a href="" style="color: black; text-decoration: none;">查看餐廳</a>
							</div>
							<div class="edit_cancel">
								<div class="edit_order">
                                    <a type="button" class="edit-button" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=loadOrderForEdit&orderId=${myorder.orderId}">編輯</a>
                                </div>

								<div class="cancel_order">
									<a href="#" type="button" class="cancel-button" onclick="showCancelConfirmation(${myorder.orderId})">取消</a>
								</div>


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
   function showCancelConfirmation(orderId) {
       var customAlert = document.getElementById("customAlert");
       var confirmButton = document.querySelector(".confirm1");

       confirmButton.setAttribute("data-order-id", orderId);
       
       customAlert.style.display = "block";
   }

   function closeCustomAlert() {
       var customAlert = document.getElementById("customAlert");
       customAlert.style.display = "none";
   }

   function showConfirmationMessage() {
       var orderId = document.querySelector(".confirm1").getAttribute("data-order-id");

       var form = document.createElement("form");
       form.method = "post";
       form.action = "${pageContext.request.contextPath}/myorder/membermyorder.do";
       
       var actionInput = document.createElement("input");
       actionInput.type = "hidden";
       actionInput.name = "action";
       actionInput.value = "updateOrderStatus2Mem";
       form.appendChild(actionInput);

       var orderIdInput = document.createElement("input");
       orderIdInput.type = "hidden";
       orderIdInput.name = "orderId";
       orderIdInput.value = orderId;
       form.appendChild(orderIdInput);

       document.body.appendChild(form);
       form.submit();

       var confirmationMessage = document.getElementById("confirmationMessage");
       confirmationMessage.style.display = "block";

       setTimeout(function() {
           confirmationMessage.style.display = "none";
       }, 5000);
   }
	 
    </script>


</body>

</html>
