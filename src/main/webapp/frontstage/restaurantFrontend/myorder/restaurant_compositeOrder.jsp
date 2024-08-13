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
<title>訂單管理</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">
<link href="../img/favicon.ico" rel="icon">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link href="lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<style>
.main_content {
	max-width: 100%;
	margin: auto;
	padding: 0 10px;
	overflow-x: auto;
	/
}

.table_allorder {
	width: 100%;
	table-layout: fixed;
	word-wrap: break-word;
	border-collapse: collapse;
}

.table_allorder th, .table_allorder td {
	padding: 8px;
}

.form {
	overflow-x: auto;
}

@media ( max-width : 768px) {
	.pagination {
		font-size: 20px;
	}
	.page_number {
		width: 25px;
		height: 25px;
	}
	.table_allorder th, .table_allorder td {
		font-size: 14px;
	}
}
</style>

</head>

<body style="background-color: #F3F6FF;">
	<jsp:include page="header_restaurant.jsp" flush="true" />

	<div class="bodycontent">
		<div class="left_content"></div>
		<div class="main_content">
			<br>
			<br>
			<div class="search_quickly">
				<button class="btn_search" type="text">
					快速查詢
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
						fill="currentColor" class="bi bi-chevron-right"
						viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
							d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708" />
                </svg>
				</button>
				<button class="btn_search">
					<a style="color: white; text-decoration: none;"
						href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll">所有訂單</a>
				</button>
				<button class="btn_search">
					<a
						href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest"
						class="allorder">預約訂單</a>
				</button>
				<button class="btn_search">
					<a class="allorder"
						href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus2Rest"
						role="button">被取消訂單</a>
				</button>
				<button class="btn_search">
					<a class="allorder"
						href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus3Rest">已完成訂單</a>
				</button>
			</div>

			<br>
			<div class="search_div">
				<form class="search_btn" role="search"
					action="<%=request.getContextPath() %>/myorder/restmyorder.do"
					method="post">
					<input class="form-control me-2" type="search" name="orderId"
						placeholder="請輸入訂單編號" aria-label="Search"> <input
						class="form-control me-3" type="search" name="memberName"
						placeholder="請輸入訂單人姓名" aria-label="Search"> <input
						class="form-control me-4" type="date" name="reserDate">
					<button class="search_icon" type="text">
						<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
							fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                        <path
								d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
                    </svg>
					</button>
					<input type="hidden" name="action" value="compositeQuery">
				</form>
			</div>
			<br>
			<div class="form">

				<table class="table_allorder">
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
					<c:forEach var="myorder" items="${orderList}">
						<tr>
							<td>${myorder.orderId}</td>
							<td>${myorder.restaurant.restName}</td>
							<td>${myorder.event.name}</td>
							<td>${myorder.member.mobileNo}</td>
							<td>${fn:substring(myorder.orderDate, 0, 10)}</td>
							<td>${myorder.reserDate}</td>
							<td>${fn:substring(myorder.reserTime, 0, 5)}</td>
							<td>${myorder.reserPeopleNumber}</td>
							<td>${myorder.reserNote}</td>
						</tr>
					</c:forEach>
				</table>
					<c:if test="${empty orderList}">
						<div class="noOrderList"><img src="<%=request.getContextPath() %>/frontstage/backend/myorder/image/nosearch.png" alt=""
						style="width: 730px; height: 400px; margin-top:80px; margin-left:40px;"></div>
					</c:if>

				<c:if test="${totalPages > 1}">
					<div class="pagination">
						<c:if test="${currentPage > 1}">
							<button class="page_number">
								<a style="color: black;"
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll&page=1"><svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-chevron-double-left"
										viewBox="0 0 16 16">
  							<path fill-rule="evenodd"
											d="M8.354 1.646a.5.5 0 0 1 0 .708L2.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
  							<path fill-rule="evenodd"
											d="M12.354 1.646a.5.5 0 0 1 0 .708L6.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
							</svg></a>
							</button>
							<button class="page_number">
								<a style="color: black;"
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll&page=${currentPage - 1}"><svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-chevron-left"
										viewBox="0 0 16 16">
  							<path fill-rule="evenodd"
											d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0" />
							</svg></a>
							</button>
						</c:if>

						<c:forEach begin="1" end="${totalPages}" var="page"
							varStatus="status">
							<c:choose>
								<c:when
									test="${page >= currentPage - 2 && page <= currentPage + 2}">
									<c:if test="${page == currentPage}">
										<button class="page_number current_page">
											<span class="current">${page}</span>
										</button>
									</c:if>
									<c:if test="${page != currentPage}">
										<button class="page_number">
											<a style="color: black;"
												href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll&page=${page}">${page}</a>
										</button>
									</c:if>
								</c:when>
								<c:when test="${page == currentPage - 3 && currentPage > 4}">

								</c:when>
								<c:when
									test="${page == currentPage + 3 && currentPage < totalPages - 3}">

								</c:when>
							</c:choose>
						</c:forEach>

						<c:if test="${currentPage < totalPages}">
							<button class="page_number">
								<a style="color: black;"
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll&page=${currentPage + 1}"><svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-chevron-right"
										viewBox="0 0 16 16">
  							<path fill-rule="evenodd"
											d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708" />
							</svg></a>
							</button>
							<button class="page_number">
								<a style="color: black;"
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getAll&page=${totalPages}"><svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-chevron-double-right"
										viewBox="0 0 16 16">
  							<path fill-rule="evenodd"
											d="M3.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L9.293 8 3.646 2.354a.5.5 0 0 1 0-.708" />
  							<path fill-rule="evenodd"
											d="M7.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L13.293 8 7.646 2.354a.5.5 0 0 1 0-.708" />
							</svg></a>
							</button>
						</c:if>
					</div>


				</c:if>
			</div>
		</div>
		<div class="right_content"></div>
	</div>

	<jsp:include page="footer.jsp" flush="true" />
	<script src="js/main.js"></script>

	<script>
		document
				.addEventListener(
						'DOMContentLoaded',
						function() {
							var form = document.querySelector('.search_btn');
							var button = form
									.querySelector('button[type="text"]');

							button
									.addEventListener(
											'click',
											function(event) {
												var orderId = form
														.querySelector('input[name="orderId"]').value
														.trim();
												var memberName = form
														.querySelector('input[name="memberName"]').value
														.trim();
												var reserDate = form
														.querySelector('input[name="reserDate"]').value
														.trim();

												if (!orderId && !memberName
														&& !reserDate) {
													event.preventDefault();
													window.location.href = '${pageContext.request.contextPath}/myorder/restmyorder.do?action=getAll';
												}
											});
						});
	</script>
</body>
</html>
