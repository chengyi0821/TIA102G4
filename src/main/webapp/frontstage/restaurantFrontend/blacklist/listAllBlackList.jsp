<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/frontstage/backend/myorder/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath() %>/frontstage/backend/myorder/css/style_blacklist.css">
<title>訂單管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="../img/favicon.ico" rel="icon">

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

<title>Orders</title>

<style>
.search-box {
	display: flex;
	align-items: center;
}

.error-message {
	color: red;
	margin-left: 10px;
	visibility: hidden;
}

.error-message.visible {
	visibility: visible;
}

 
   .orderIdError {
    margin-left: 10%;
    margin-right: 15%;
    margin-top: 10px;
    margin-bottom: 10px;
    padding-top: 15px;
    padding-bottom: 7px;
    border-radius: 0.2cm;
    background-color: #ffc107; 
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2); 
    color: black;
    text-align: center;
    font-weight: 600;
    font-size: 17px; 
    height: 50px;
    display: none; 
}

.orderIdError.show {
    display: block;
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
							<li><a style="color: black;" href="#">最新消息</a></li>
							<li><a style="color: black;" href="#">菜單瀏覽</a></li>
							<li><a style="color: black;" href="#">營業資訊</a></li>
							<li><a style="color: black;" href="#">顧客評價</a></li>
						</ul>
	
	
					   </div>
					  <div class="orderblock"> <a  href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/myorder/restaurant_order_index.jsp" class="nav-item nav-link active">訂單系統</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll">訂單管理</a></li>
							<li><a style="color: black;"  href="<%=request.getContextPath() %>/blacklist/blacklist.do?action=getAll">黑名單</a></li>
						</ul>
					  </div> 
					</div>
				
					<div class="navbar-nav mr-auto py-0">
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">聯絡客服</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="#">意見反應</a></li>
							<li><a style="color: black;" href="#">系統公告</a></li>
						</ul>
					   </div>
					  
			</nav>
		</div>
	</div>
	<!-- Navbar End -->
	
	
	<div class="bodycontent">
		<div class="left_content"></div>
		<div class="main_content" style="background-color: #F3F6FF;">

			<div class="contentmain1">
				<br>
				<form class="search-box" role="search"
					action="<%=request.getContextPath() %>/myorder/myorder.do"
					method="post" onsubmit="return validateForm()">
					<input type="search" class="input-search" name="orderId"
						placeholder="請輸訂單編號" aria-label="Search">
					<button class="search_blacklist">
						<svg xmlns="http://www.w3.org/2000/svg" width="35" height="35"
							fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                     <path
								d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                   </svg>
					</button>

					<input type="hidden" name="action" value="getOrderId1">
				</form>
				<div class="error-message">請輸入訂單編號</div>

				<div class="blist">

					<!--  ==================================================加入黑名單成功提示=======================================================               -->
						<c:if test="${not empty orderIdError}">
						<script>
			        	document.addEventListener("DOMContentLoaded", function() {
			            var messageBox = document.querySelector(".orderIdError");
			            if (messageBox) {
			                messageBox.classList.add("show");
			            }
        				});
    				 	</script>
						</c:if>

						<div class="orderIdError"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-exclamation-circle" viewBox="0 0 16 16">
  						<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
  						<path d="M7.002 11a1 1 0 1 1 2 0 1 1 0 0 1-2 0M7.1 4.995a.905.905 0 1 1 1.8 0l-.35 3.507a.552.552 0 0 1-1.1 0z"/>
						</svg>&nbsp${orderIdError} </div>

						
						
	
						<c:if test="${not empty message}">
						<script>
			        	document.addEventListener("DOMContentLoaded", function() {
			            var messageBox = document.querySelector(".already_add_blist");
			            if (messageBox) {
			                messageBox.classList.add("show");
			            }
        				});
    				 	</script>
						</c:if>

						<div class="already_add_blist">
						<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-person-check" viewBox="0 0 16 16"> <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m1.679-4.493-1.335 2.226a.75.75 0 0 1-1.174.144l-.774-.773a.5.5 0 0 1 .708-.708l.547.548 1.17-1.951a.5.5 0 1 1 .858.514M11 5a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4" />
						<path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z" /> </svg>
						&nbsp${message} </div>

						<c:if test="${not empty success}">
							<script>
			        	document.addEventListener("DOMContentLoaded", function() {
			            var successBox = document.querySelector(".add_success");
			            const audio = document.querySelector('#notificationAudio');
			            if (successBox) {
			            	audio.play();
			                successBox.classList.add("show");
			            }
	       				 });
	    				</script>
						</c:if>


						<div class="add_success">
						<svg xmlns="http://www.w3.org/2000/svg" width="27" height="27" fill="currentColor" class="bi bi-check2-circle"
							viewBox="0 0 16 16"> <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0" />
  						<path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z" />
						</svg> ${success}
						<audio id="notificationAudio"
							src="<%=request.getContextPath() %>/frontstage/backend/myorder/audio/notification.mp3"></audio>
					</div>


					<!-- =========================================訂單查詢結果========================================			 -->

					<c:if test="${not empty myOrder}">
						<ul class="blist_detail">
							<li>訂單編號: ${myOrder.orderId}</li>
							<li>用戶編號: ${myOrder.member.memberId}</li>
							<li>用戶姓名: ${myOrder.event.name}</li>
							<li>用戶電話: ${myOrder.member.mobileNo}</li>

							<form method="post"
								action="<%=request.getContextPath() %>/blacklist/blacklist.do">
								<input type="hidden" id="orderId" name="orderId"
									value="${myOrder.orderId}"> <input type="hidden"
									name="memberId" value="${myOrder.member.memberId}"> <input
									type="hidden" name="restaurantId"
									value="${myOrder.restaurant.restId}"> <input
									type="hidden" name="action" value="add">
								<button type="submit" class="btn btn-primary" id="delete_blist">加入黑名單</button>
							</form>
						</ul>
					</c:if>
					<br>
				<div><img src="<%=request.getContextPath() %>/frontstage/backend/myorder/image/blacklist.png" alt=""
								style="width: 400px; height: 600px;"></div>
				</div>
			</div>

			<div class="contentmain2">

				<br>
				<form class="search-box" role="search"
					action="<%=request.getContextPath() %>/blacklist/blacklist.do"
					method="post">
					<input type="search" class="input-search" name="blackListId"
						placeholder="請輸入黑名單編號" aria-label="Search">
					<button class="search_blacklist">
						<svg xmlns="http://www.w3.org/2000/svg" width="35" height="35"
							fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                     <path
								d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                   </svg>
					</button>
					<input type="hidden" name="action" value="compositeQuery">
				</form>
				<div class="delete_sussces">
					<svg xmlns="http://www.w3.org/2000/svg" width="27" height="27"
						fill="currentColor" class="bi bi-check2-circle"
						viewBox="0 0 16 16">
  			  <path
							d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0" />
  		      <path
							d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z" />
			  </svg>
					刪除成功
					<audio id="notificationAudio"
						src="<%=request.getContextPath() %>/audio/notification.mp3"></audio>
				</div>
				<div class="blist">

					<c:forEach var="blacklist" items="${blackListList}">
						<ul class="blist_detail">
							<li>黑名單編號: ${blacklist.blackListId}</li>
							<li>用戶編號: ${blacklist.member.memberId}</li>
							<li>用戶姓名: ${blacklist.member.name}</li>
							<li>黑名單新增時間：${blacklist.addBlistTime}</li>

							<form method="post"
								action="<%=request.getContextPath() %>/blacklist/blacklist.do"
								onsubmit="showNotification()">
								<button class="btn btn-primary" id="delete_blist" type="submit"
									value="delete">刪除黑名單</button>
								<input type="hidden" name="blackListId"
									value="${blacklist.blackListId}"> <input type="hidden"
									name="action" value="delete">
							</form>

						</ul>

					</c:forEach>


					<c:if test="${empty blackListList}">
						<div class="no_blacklist">沒有黑名單記錄</div>
					</c:if>

				</div>

				<br>

			</div>
		</div>


		<div class="right_content"></div>
	</div>


	<footer id="footer">
			<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
			<h5 class="footerh5">常見問題</h5>
			<h5 class="footerh5">網站地圖</h5>
			<h5 class="footerh5">使用者條款</h5>
			<h5 class="footerh5">隱私權條款</h5>
			<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
		</footer>
	<!-- ============================刪除成功的提示訊息 start===================================================== -->
	<script>
function showNotification() {
	const notification = document.querySelector('.delete_sussces');
	const audio = document.querySelector('#notificationAudio');
	notification.style.display = 'block';
	audio.play();
	setTimeout(() => {
		notification.style.display = 'none';
	}, 2000);
	return true;
}
</script>

	<script>
function showNotification0() {
	const notification = document.querySelector('.delete_sussces');
	const audio = document.querySelector('#notificationAudio');
	notification.style.display = 'block';
	audio.play();
	setTimeout(() => {
		notification.style.display = 'none';
	}, 2000);
	return true;
}
</script>

	<script>
        function validateForm() {
            var orderId = document.querySelector("input[name='orderId']").value;
            var errorMessage = document.querySelector(".error-message");

            if (orderId === "") {
                errorMessage.classList.add("visible");
                return false; 
            }

            errorMessage.classList.remove("visible");
            return true; 
        }
    </script>
	<!-- ============================刪除成功的提示訊息 End===================================================== -->


</body>
</html>