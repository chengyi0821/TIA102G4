<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

body{
	height: 800px;
}
.left_content1, .right_content1{
    /* border: 1px solid yellow; */
    width: 130px;
    height:800px;
}

.main_content_favorite{
    width: 1400px;
    height:800px;
    /* box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);  */
    /* border:2px solid #ffc107; */
    background-color:#fffbeb;
    border-radius: 0.5cm;
   
    
}

.favorite{
	/* border: 1px solid red; */
	width: 1400px;
	height: 800px;
	overflow-x: auto;
	white-space: nowrap;
	display: flex;
	flex-wrap: nowrap;
	 
}

.favorite_detail{
	display: flex;
	flex-wrap: nowrap;
	gap: 100px;
	margin-left: 90px;

}

.square{
	/* border:1px solid red; */
	width: 340px;
	height: 480px;
	border-radius: 10px;
	background-color: #fbeccd;
	box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
	margin-top: 80px;

}

.small_square{
	border: 1px solid white;
	width: 300px;
	height: 370px;
	background-color: white;
	border-radius: 10px;
	margin-left: 18px;
	margin-top: 18px;
}
.fav_restname{
	/* border: 1px solid red; */
	width: 300px;
	height: 10px;
	font-size: 30px;
	padding-top: 60px;
	font-weight: 900;
}

.favorite_btn{
	/* border: 2px solid white;
	border-radius: 10px; */
	border-right: none;
	border-left: none;
	height: 50px;
	width: 300px;
	margin-top: 20px;
	margin-left: 17px;
	display: flex;
	flex-wrap: nowrap;
}

.go_rest, .remove_fav{
	border: 2px solid white;
	border-right: none;
	border-left: none;
	border-radius: 20px;
	border-radius: 10px;
	height: 45px;
	width: 110px;
	margin-left: 20px;
	margin-right: 20px;
	/* background-color: white; */
	font-size: 20px;
	padding-top: 12px;
	
}

.go_rest:hover, .remove_fav:hover{
	background-color: white;
	color: #F195B2;
}

  /* 提示消息样式 */
        .success-message1 {
            display: none;
            position: fixed;
            top: 40%;
            left: 50%;
            width:350px;
            height:150px;
            transform: translate(-50%, -50%);
            background-color: #ffc107;
            color: white;
            font-size:25px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            padding-top:60px;
        }
    
   		.fav_pic {
   		
            border-radius: 20px;
            height: 40%;
            width: 60%;
            padding-top: 20px;
            position: fixed;
            top: -100%;
            left: 50%;
            transform: translateX(-50%);
            transition: top 0.5s ease-in-out;
            z-index: 1000;
        }
        .fav_pic.show {
            top: 20%;
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
							<img class="logo" src="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/image/logo.png"">
							<h1 class="m-0 display-4 text-primary">Chugether</h1>
						</a>
					</div>
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
		<div class="container-lg position-relative p-0 px-lg-3" style="z-index: 9;">
			<nav class="navbar navbar-expand-lg bg-white navbar-light shadow p-lg-0">
				
				<div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
					<div class="navbar-nav ml-auto py-0">
					  <div class="orderblock">  <a href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberHome/memberHome.html" class="nav-item nav-link">&nbsp&nbsp&nbsp首頁 </a> 
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/member/memberlogin.jsp">會員登入</a></li>
							<li><a id="logout" style="color: black;" href="#">登出會員</a></li>
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberNews/memberNews.html">最新消息</a></li>
						</ul>
					  </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link  active">會員專區</a>
						<ul class="orderlist">
							
							<li><a style="color: black;" href="<%=request.getContextPath() %>/favorite/favorite.do?action=getFav">查看收藏</a></li>
						</ul>
	
	
					   </div>
					
					</div>
				
					<div class="navbar-nav mr-auto py-0">
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">揪團系統</a>
						<ul class="orderlist">
							
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/room/inviteroom.jsp">參與揪團</a></li>
						</ul>
					   </div>
					   <div class="orderblock"> <a href="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/member_order_index.jsp" class="nav-item nav-link">訂單管理</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/myorder/membermyorder.do?action=getOrderStatus1Mem">編輯訂單</a></li>
							
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
    <div class="bodycontent" style="display: flex; flex-wrap: nowrap;" >
    <div id="successMessage1" class="success-message1">移除成功</div>
    <div class="left_content1"></div>
	<br>
    <div class="main_content_favorite" style="background-color:#F3F6FF ;">
	<div class="favorite">
			<c:if test="${empty favorites}">
			<div  class="fav_pic" id="favPic" style="dispaly:flex; flex-wrap: nowrap;"><img src="<%=request.getContextPath() %>/frontstage/backend/myorder/image/favorite_like3.png" alt=""
			style="width: 950px; height: 550px; margin-top:80px; margin-left:40px;"></div>
			</c:if>
		<c:forEach var="favorite" items="${favorites}">
		<div class="favorite_detail">
			<div class="square">
				
				<div class="small_square">
					<div class="fav_restname">${favorite.restaurant.restName}</div>
					<div>  <img src="<%=request.getContextPath() %>/frontstage/memberFrontend/favorite/image/Collection.png" style="width: 300px; height: 300px;"></div>
				</div>
				<div class="favorite_btn">
					<div class="go_rest">查看餐廳</div>
					<div class="remove_fav"> <a style="color:black;" href="<%=request.getContextPath() %>/favorite/favorite.do?action=deleteFav&restId=${favorite.restaurant.restId}" onclick="showSuccessMessage1()">移除收藏</a></div>
				</div>
			</div>
		
		</div> 
		<!-- //favorite_detail的結束標籤 -->
	</c:forEach>


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

  <script>
        function showSuccessMessage1() {
            var message = document.getElementById("successMessage1");
            message.style.display = "block";
            setTimeout(function() {
                message.style.display = "none";
                window.location.href = "${pageContext.request.contextPath}/favorite/favorite.do?action=getFav";
            }, 3000);
        }
    </script>
    
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        var favPic = document.getElementById("favPic");

        // Automatically show the block after 1 second
        setTimeout(function() {
            favPic.classList.add("show");
        }, 1000);
    });
</script>
    <script src="../memberLogout/memberLogout.js"></script>
</body>

</html>
