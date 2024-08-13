<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>客服管理</title>
<!-- 載入 jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="../css/style.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/style.css"
	rel="stylesheet" />
<!--     <link rel="stylesheet" -->
<!--     href="/main/style.css"> -->
<title>訂單管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="images/favicon.ico" rel="icon">

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

<!--        Customized Bootstrap Stylesheet -->
<!--        <link href="css/style.css" rel="stylesheet"> -->
<style>
.body_main {
	/* border: 1px solid blue; */
	width: 900px;
	height: 600px;
	margin-top: 30px;
	margin-bottom: 0px;
}

.body_content, .left_content1, .right_content1 {
	height: 80%;
}

body {
	height: 80%;
}

/* ================================================================================================= */
body {
	font-family: 'Arial', sans-serif;
	background-color: #f8f8f8;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	color: #4D3900;
	font-weight: bold;
}

h1 {
	margin: 0;
	font-size: 36px;
}

.container1 {
	flex: 1;
	padding: 40px;
	margin: 0 auto;
	max-width: 80%;
	background-color: #fff3e0;
	border-radius: 15px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border: 5px solid #fdc001;
	height: 90%;
}

.filters {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

.search-container {
	display: flex;
	flex-wrap: wrap;
	align-items: center;
	flex: 1;
}

.search-container input[type="text"], .search-container select,
	.search-container input[type="date"], .search-container button {
	border: 1px solid #ccc;
	border-radius: 5px;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
	height: 40px;
	margin-right: 10px; /* 缩小 margin-right 以减少空间占用 */
	color: #4D3900;
	font-weight: bold;
}

.search-container button {
	background-color: #4D3900; /* 恢复原本的咖啡色背景 */
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-weight: bold;
	margin-top: 10px;
}

.search-container button:hover {
	background-color: #fdc001; /* 鼠标悬停时变色 */
	color: #4D3900; /* 悬停时字体颜色变化 */
}

.button-container button {
	background-color: #4D3900; /* 恢复原本的咖啡色背景 */
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-weight: bold;
}

.button-container button:hover {
	background-color: #fdc001; /* 鼠标悬停时变色 */
	color: #4D3900; /* 悬停时字体颜色变化 */
}

.items-per-page, .button-container {
	text-align: right;
	margin-top: 10px;
}

.items-count {
	font-weight: bold;
	margin-right: 20px;
	color: #d9534f;
}

.items-per-page select {
	border: 1px solid #ccc;
	border-radius: 5px;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
	height: 40px;
	margin-right: 10px;
	color: #4D3900;
	font-weight: bold;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
	/* table-layout: fixed; */
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
	color: #4D3900;
	word-wrap: break-word;
	font-weight: bold;
}

th {
	background-color: #fdc001;
	color: #4D3900;
	cursor: pointer;
	font-weight: bold;
	border-radius: 0px;
}

th:first-child {
	border-top-left-radius: 15px;
	border-bottom-left-radius: 15px;
}

th:last-child {
	border-top-right-radius: 15px;
	border-bottom-right-radius: 15px;
}

th.sortable:hover {
	background-color: #f0c14b;
}

tr:hover {
	background-color: #ffe4e1; /* 淺粉色 */
}

.pagination {
	text-align: center;
	margin-top: 20px;
}

.pagination a {
	color: #4D3900;
	padding: 8px 16px;
	text-decoration: none;
	border: 1px solid #ddd;
	margin: 0 4px;
	border-radius: 5px;
	transition: background-color 0.3s ease;
	font-weight: bold;
}

.pagination a.active {
	background-color: #fdc001;
	color: white;
	border: 1px solid #fdc001;
}

.pagination a:hover:not(.active) {
	background-color: #f0c14b;
}

footer {
	background-color: #4D3900;
	color: #fff;
	padding: 10px;
	text-align: center;
	width: 100%;
	position: relative;
	bottom: 0;
	margin-top: auto;
	font-weight: bold;
}

.pagination .first-page, .pagination .last-page {
	font-size: 18px;
	font-weight: bold;
}

#adminTable {
	width: 300px;
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
							<img class="logo" src="<%=request.getContextPath()%>/imgages/logo.png">
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
							<a href="#" class="nav-item nav-link active">店家介紹</a>
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
							<a href="#" class="nav-item nav-link">揪團系統</a>
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
			</nav>
		</div>
	</div>
	<!-- Navbar End -->
	<div class="bodycontent">
		<div class="left_content1"></div>
		<div class="main_content1">
			<div class="container1">
				<h2>後台人員帳號管理列表</h2>
				<br />
				<div class="filters">
					<div class="search-container">
						<input type="text" id="search" placeholder="搜尋..."
							value="${search}"> <select id="permissionFilter">
							<option value="">所有權限</option>
							<option value="1" ${permissionFilter == '1' ? 'selected' : ''}>管理員</option>
							<option value="2" ${permissionFilter == '2' ? 'selected' : ''}>一般</option>
						</select> select id="statusFilter" name="statusFilter">
						<option value="" ${statusFilter == null ? 'selected' : ''}>所有狀態</option>
						<option value="true" ${statusFilter == 'true' ? 'selected' : ''}>啟用</option>
						<option value="false" ${statusFilter == 'false' ? 'selected' : ''}>停用</option>
						</select> 註冊日期區間： <input type="date" id="startDate" value="${startDate}">～
						<input type="date" id="endDate" value="${endDate}">
						<button id="searchBtn">搜尋</button>
						<button id="resetBtn">重置</button>
					</div>
				</div>
				<div class="filters">
					<div class="button-container">
						<button onclick="window.location.href='addAdmin.jsp'">新增帳號</button>
					</div>
					<div class="items-count">查詢結果：共 ${totalAdmins} 筆</div>
					<div class="items-per-page">
						<select id="itemsPerPage">
							<option value="10" ${itemsPerPage == '10' ? 'selected' : ''}>每頁顯示10筆</option>
							<option value="25" ${itemsPerPage == '25' ? 'selected' : ''}>每頁顯示25筆</option>
							<option value="50" ${itemsPerPage == '50' ? 'selected' : ''}>每頁顯示50筆</option>
						</select>
					</div>
				</div>

				<table id="adminTable" style="width: 100%;">
					<thead>
						<tr>
							<th class="sortable" data-field="admin_id">ID▾</th>
							<th class="sortable" data-field="name">名稱▾</th>
							<th class="sortable" data-field="account">帳號▾</th>
							<th class="sortable" data-field="permission">權限▾</th>
							<th class="sortable" data-field="regis_date">註冊日期▾</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="admin" items="${admins}">
							<tr>
								<td>${admin.adminId}</td>
								<td>${admin.name}</td>
								<td>${admin.account}</td>
								<td>${admin.permission == 1 ? "管理員" : "一般"}</td>
								<td><fmt:formatDate value="${admin.regisDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><a
									href="${pageContext.request.contextPath}/adminList?action=edit&adminId=${admin.adminId}">編輯</a>
									| <a href="javascript:void(0);" class="toggle-status-btn"
									data-admin-id="${admin.adminId}"
									data-current-status="${admin.accStatus}"> ${admin.accStatus ? "停用" : "啟用"}
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="pagination">
					<a
						href="?page=1&itemsPerPage=${itemsPerPage}&permissionFilter=${permissionFilter}&startDate=${startDate}&endDate=${endDate}&search=${search}&sortField=${sortField}&sortOrder=${sortOrder}"
						class="first-page">«</a>
					<c:forEach var="i" begin="1" end="${totalPages}">
						<a
							href="?page=${i}&itemsPerPage=${itemsPerPage}&permissionFilter=${permissionFilter}&startDate=${startDate}&endDate=${endDate}&search=${search}&sortField=${sortField}&sortOrder=${sortOrder}"
							class="${i == currentPage ? 'active' : ''}">${i}</a>
					</c:forEach>
					<a
						href="?page=${totalPages}&itemsPerPage=${itemsPerPage}&permissionFilter=${permissionFilter}&startDate=${startDate}&endDate=${endDate}&search=${search}&sortField=${sortField}&sortOrder=${sortOrder}"
						class="last-page">»</a>
				</div>
			</div>

			<script>
				// 搜尋功能
				document.getElementById("searchBtn").addEventListener(
						"click",
						function() {
							const params = new URLSearchParams(
									window.location.search);
							params.set("search", document
									.getElementById("search").value);
							params.set("permissionFilter", document
									.getElementById("permissionFilter").value);
							params.set("statusFilter", document
									.getElementById("statusFilter").value); // 新增
							params.set("startDate", document
									.getElementById("startDate").value);
							params.set("endDate", document
									.getElementById("endDate").value);
							params.set("itemsPerPage", document
									.getElementById("itemsPerPage").value);
							params.set("page", 1); // 每次搜尋回到第一頁
							window.location.search = params.toString();
						});

				// 重置功能
				document.getElementById("resetBtn").addEventListener("click",
						function() {
							window.location.href = window.location.pathname; // 重新加載頁面清除所有搜索條件
						});

				// 排序功能
				document
						.querySelectorAll('.sortable')
						.forEach(
								function(header) {
									header
											.addEventListener(
													'click',
													function() {
														const params = new URLSearchParams(
																window.location.search);
														const field = this
																.getAttribute('data-field');
														let sortOrder = params
																.get("sortOrder") === "asc" ? "desc"
																: "asc";

														params.set("sortField",
																field);
														params.set("sortOrder",
																sortOrder);
														params.set("page", 1); // 改變排序回到第一頁
														window.location.search = params
																.toString();
													});
								});

				// 頁數顯示功能
				document.getElementById("itemsPerPage").addEventListener(
						"change",
						function() {
							const params = new URLSearchParams(
									window.location.search);
							params.set("itemsPerPage", this.value);
							params.set("page", 1); // 改變顯示筆數回到第一頁
							window.location.search = params.toString();
						});

				// 帳號啟用停用功能
				$(document)
						.ready(
								function() {
									$(".toggle-status-btn")
											.click(
													function() {
														var adminId = $(this)
																.data(
																		"admin-id");
														var currentStatus = $(
																this)
																.data(
																		"current-status");
														var button = $(this);

														if (confirm(currentStatus ? "確定要停用這個帳號嗎？"
																: "確定要啟用這個帳號嗎？")) {
															$
																	.ajax({
																		url : "${pageContext.request.contextPath}/adminList",
																		type : "GET",
																		data : {
																			action : "toggleStatus",
																			adminId : adminId
																		},
																		success : function(
																				response) {
																			if (response.success) {
																				// 更新按鈕文本和狀態
																				var newText = response.newStatus ? "停用"
																						: "啟用";
																				button
																						.text(newText);
																				button
																						.data(
																								"current-status",
																								response.newStatus);
																			} else {
																				alert(response.message);
																			}
																		},
																		error : function() {
																			alert("發生錯誤，請稍後再試");
																		}
																	});
														}
													});
								});
			</script>
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
