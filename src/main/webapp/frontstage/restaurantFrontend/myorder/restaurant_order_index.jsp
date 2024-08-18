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
							<img class="logo" src="../image/logo.png">
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
							<li><a id="logout" style="color: black;" href="#">登出會員</a></li>
						</ul>
					  </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">店家資訊</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/restaurantAboutUs/restaurantAboutUs.html">關於我們</a></li>
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/restaurantFrontend/restNews/restNews.html">最新消息</a></li>
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
						
						</ul>
					   </div>
					  
			</nav>
		</div>
	</div>
	<!-- Navbar End -->

     <div class="bodycontent">
	<div class="left_content"></div>
	<div class="main_content">
        
    <br>
    <br>
    <div class="search_quickly">
        <button  class="btn_search"  type="text">快速查詢 <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-right" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708"/>
          </svg></button >
    <button class="btn_search" ><a href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll" class="allorder">所有訂單</a></button>
   <button class="btn_search"><a href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest" class="allorder">預約訂單</a></button> 
    <button class="btn_search"><a class="allorder" href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus2Rest" role="button">被取消訂單</a></button>
    <button class="btn_search"><a class="allorder" href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus3Rest" role="button">已完成訂單</a></button>
        </div>

        <br>
        <div class="search_div">
          <form class="search_btn" role="search" action="<%=request.getContextPath() %>/myorder/restmyorder.do" method="post">
            <input class="form-control me-2" type="search" name="orderId" placeholder="請輸入訂單編號" aria-label="Search">
            <input class="form-control me-3" type="search" name="memberName" placeholder="請輸入訂單人姓名" aria-label="Search">
            <input class="form-control me-4" type="date" name="reserDate">
            <button class="search_icon" type="text"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
              </svg></button>
              <input type="hidden" name="action" value="compositeQuery">
          </form>
        </div> 
        <br>  
        <br> 
        <div class="form">
          
        <table class="table_allorder"">
            <tr>
                <th>訂單編號</th>
                <th>餐廳名稱</th>
                <th>訂單姓名</th>
                <th>訂單電話</th>
                <th>訂單日期</th>
                <th>訂位日期</th>
                <th>訂位時間</th>
                <th>訂位人數</th>
                <th>訂單備註</th>
    
            </tr>
        
        </table>
   
 
    

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
		<script src="js/main.js"></script>
		
		<script>
document.addEventListener('DOMContentLoaded', function() {
    var form = document.querySelector('.search_btn');
    var button = form.querySelector('button[type="text"]');

    button.addEventListener('click', function(event) {
        var orderId = form.querySelector('input[name="orderId"]').value.trim();
        var memberName = form.querySelector('input[name="memberName"]').value.trim();
        var reserDate = form.querySelector('input[name="reserDate"]').value.trim();
        
      
        if (!orderId && !memberName && !reserDate) {
            event.preventDefault(); 
            window.location.href = '${pageContext.request.contextPath}/myorder/restmyorder.do?action=getAll';
        }
    });
});
</script>
 <div id="messages"></div>
  

	<script src="../restaurantLogout/restaurantLogout.js"></script>

</body>

</html>