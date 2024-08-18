<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.tia102g4.admin.model.*" %>
<%
    Admin entity = (Admin) request.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理員管理</title>
    <!-- 載入 jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link href="<%=request.getContextPath() %>/frontstage/backend/css/style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/frontstage/backend/admin/css/admin.css" rel="stylesheet" />
</head>
<body>
    <!-- Topbar 開始 -->
    <div class="container-fluid bg-primary py-3 d-none d-md-block">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center">
                        <a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
                            <img class="logo" src="<%=request.getContextPath() %>/frontstage/backend/image/logo.png" alt="Logo">
                            <h1 class="m-0 display-4 text-primary">Chugether</h1>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar 結束 -->

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
							<a href="#" class="nav-item nav-link">後台帳戶</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/backend/admin/adminindex.jsp">後台帳戶</a></li>
								<li><a id="logout" style="color: black;" href="#">後台登出</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="#" class="nav-item nav-link active">會員管理</a>
							<ul class="orderlist">
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/backend/admin/adminlistallmembers.jsp">會員列表</a></li>
								<li><a style="color: black;" href="<%=request.getContextPath() %>/frontstage/backend/restaurant/restaurant.html">店家列表</a></li>
							</ul>


						</div>
						<div class="orderblock">
							<a href="<%=request.getContextPath() %>/frontstage/backend/announcement/announcement.html"
								class="nav-item nav-link">公告管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath() %>/frontstage/backend/announcement/announcement.html">系統公告</a></li>
							</ul>
						</div>
					</div>

					<div class="navbar-nav mr-auto py-0">
						<div class="orderblock">
							<a
								href="<%=request.getContextPath() %>/frontstage/backend/admin_order_index.jsp"
								class="nav-item nav-link">訂單管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath() %>/frontstage/backend/myorder/admin_order_index.jsp">訂單列表</a></li>
							</ul>
						</div>
						<div class="orderblock">
							<a href="<%=request.getContextPath() %>/frontstage/backend/customerService/member-reply.html"
								class="nav-item nav-link">客服管理</a>
							<ul class="orderlist">
								<li><a style="color: black;"
									href="<%=request.getContextPath() %>/frontstage/backend/customerService/member-reply.html">會員信件</a></li>
								<li><a style="color: black;"
									href="<%=request.getContextPath() %>/frontstage/backend/customerService/restaurant-reply.html">餐廳信件</a></li>

							</ul>
						</div>
					</div>
				</div>
			</nav>
		</div>
	</div>
    <!-- Navbar 結束 -->

    <div id="main-content1"></div>
    
    <!-- 這裡是放入管理員列表的主要區域 -->
    <div id="main-content2">  
        <!-- 功能內容開始 -->
        <form action="<%=request.getContextPath()%>/admin/admin.do" method="post">
            <h1>管理員資料</h1>
            <c:if test="${adminPageQty > 0}">
                <b><font color="red">第 ${currentPage}/${adminPageQty} 頁</font></b>
            </c:if>
            <table style="width:100%; text-align:center;">
                <tr>
                    <th>管理員編號</th>
                    <th>名稱</th>
                    <th>帳號</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="admin" items="${adminList}">
                    <tr>
                        <td>${admin.adminId}</td>
                        <td>${admin.name}</td>
                        <td>${admin.account}</td>
                        <td>
                            <form action="<%=request.getContextPath() %>/admin/admin.do?action=delete" method="post" style="display:inline">
                                <input type="hidden" name="adminId" value="${admin.adminId}">
                                <input type="submit" value="刪除" onclick="return confirm('確定要刪除這個管理員嗎？');">
                            </form>
                             <a href="<%=request.getContextPath()%>/frontstage/backend/admin/adminupdate.jsp">修改後台員工</a>   
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${currentPage > 1}">
                <a href="<%=request.getContextPath() %>/admin/admin.do?action=getAll&page=1">至第一頁</a>&nbsp;
            </c:if>
            <c:if test="${currentPage - 1 > 0}">
                <a href="<%=request.getContextPath() %>/admin/admin.do?action=getAll&page=${currentPage - 1}">上一頁</a>&nbsp;
            </c:if>
            <c:if test="${currentPage + 1 <= adminPageQty}">
                <a href="<%=request.getContextPath() %>/admin/admin.do?action=getAll&page=${currentPage + 1}">下一頁</a>&nbsp;
            </c:if>
            <c:if test="${currentPage < adminPageQty}">
                <a href="<%=request.getContextPath()%>/admin/admin.do?action=getAll&page=${adminPageQty}">至最後一頁</a>&nbsp;
            </c:if>
            <br>
            <a href="<%=request.getContextPath()%>/frontstage/backend/admin/adminindex.jsp">回首頁</a>
        </form>
    </div>

    <div class="table-container"></div>
    <div id="main-content3"></div>

    <!-- Footer 開始 -->
    <footer id="footer">
        <h5 class="footerh5_1">chugether 揪來鳩去有限公司</h5>
        <h5 class="footerh5">常見問題</h5>
        <h5 class="footerh5">網站地圖</h5>
        <h5 class="footerh5">使用者條款</h5>
        <h5 class="footerh5">隱私權條款</h5>
        <h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
    </footer>
    <!-- Footer 結束 -->

    <script src="<%=request.getContextPath()%>/js/main.js"></script>
    <script src="../adminLogout/admin-logout.js"></script>
</body>
</html>
