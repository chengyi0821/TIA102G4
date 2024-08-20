<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="com.tia102g4.admin.model.*" %>
<%
    Admin entity = (Admin) request.getAttribute("admin");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>我要揪團</title>
	<!-- 載入 jQuery -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<link href="<%=request.getContextPath() %>/frontstage/backend/css/style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/frontstage/backend/admin/css/adminRegister.css" rel="stylesheet" />
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
							<img class="logo" src="<%=request.getContextPath() %>/frontstage/backend/image/logo.png">
							<h1 class="m-0 display-4 text-primary">Chugether</h1>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Topbar End -->

	<!-- Navbar Start -->
		
	<!-- Navbar End -->

	<div id="main-content1"></div>
	<div id="main-content2">
		<h2>後台註冊</h2>
    <form action="<%=request.getContextPath()%>/admin/admin.do" method="post">
        <input type="hidden" name="action" value="register"/>
        <label for="name">姓名:</label>
        <input type="text" id="name" name="name" required><br>
        
        <label for="account">帳號:</label>
        <input type="text" id="account" name="account" required><br>
        
        <label for="password">密碼:</label>
        <input type="password" id="password" name="password" required><br>
        
        <label for="permission">權限:</label>
        <input type="text" id="permission" name="permission" required><br>
    
        
        <input type="submit" value="註冊" >
        
    </form>
    
    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>
	</div>
	<div class="table-container">

	</div>
	<div id="main-content3">

	</div>

	<!-- Footer Start -->
		<footer id="footer">
			<h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
			<h5 class="footerh5">常見問題</h5>
			<h5 class="footerh5">網站地圖</h5>
			<h5 class="footerh5">使用者條款</h5>
			<h5 class="footerh5">隱私權條款</h5>
			<h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
		</footer>
	<!-- Footer end -->
		<script src="js/main.js"></script>
		<script src="../adminLogout/admin-logout.js"></script>
</body>

</html>