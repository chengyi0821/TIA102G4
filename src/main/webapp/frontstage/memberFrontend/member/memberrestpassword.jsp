<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="com.tia102g4.member.model.*" %>
<%
    Member entity = (Member) request.getAttribute("member");
%>    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>會員登入</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link href="<%=request.getContextPath() %>/frontstage/memberFrontend/css/style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/frontstage/memberFrontend/member/css/member.css" rel="stylesheet" />
    <script src="js/main.js"></script>
</head>

<body>
    <!-- Topbar Start -->
    <div class="container-fluid bg-primary py-3 d-none d-md-block">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center">
                        <a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
                            <img class="logo" src="<%=request.getContextPath() %>/frontstage/memberFrontend/image/logo.png" />
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
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/memberNews/memberNews.html">最新消息</a></li>
							<li><a id="logout" style="color: black;" href="#">登出會員</a></li>
						</ul>
					  </div>
					   <div class="orderblock"> <a href="#" class="nav-item nav-link">會員專區</a>
						<ul class="orderlist">

							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/favorite/favoriteList.jsp">查看收藏</a></li>
						</ul>
	
	
					   </div>
					
					</div>
				
					<div class="navbar-nav mr-auto py-0">
					   <div class="orderblock"> <a href="#" class="nav-item nav-link active">揪團系統</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/room/inviteroom.jsp">發起揪團</a></li>
						</ul>
					   </div>
					   <div class="orderblock"> <a href="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/my_order_index.jsp" class="nav-item nav-link">訂單管理</a>
						<ul class="orderlist">
							<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/memberFrontend/myorder/member_orderStatus1.jsp">編輯訂單</a></li>
							<li><a style="color: black;" href="#">取消訂單</a></li>
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

    <!-- 主要內容區域 -->
    <div id="main-content1"></div>
    <div id="main-content2">
        <h2>重置密碼</h2>
        <form action="<%=request.getContextPath()%>/member/member.do" method="post">
            <input type="hidden" name="action" value="resetPassword">
            <label for="newPassword">新密码:</label>
            <input type="password" id="newPassword" name="newPassword" placeholder="NewPassword" required><br>
            
            <button type="submit">重置密码</button>
        </form>
        
        
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>
        
        
        <c:if test="${not empty successMessage}">
            <p style="color: green;">${successMessage}</p>
        </c:if>
        <!-- 重置密碼功能內容結束 -->
    </div>
    <div class="table-container"></div>
    <div id="main-content3"></div>

    <!-- Footer Start -->
    <footer id="footer">
        <h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
        <h5 class="footerh5">常見問題</h5>
        <h5 class="footerh5">網站地圖</h5>
        <h5 class="footerh5">使用者條款</h5>
        <h5 class="footerh5">隱私權條款</h5>
        <h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
    </footer>
    <!-- Footer End -->
    <script src="js/main.js"></script>
    <script src="../memberLogout/memberLogout.js"></script>
</body>

</html>
