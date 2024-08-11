<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Chugether</title>
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
.custom-alert, .confirmation-message, .custom-alert1,
	.confirmation-message1 {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background-color: white;
	padding: 20px;
	border: 1px solid black;
	z-index: 1000;
}

.custom-alert-buttons, .custom-alert-buttons1 {
	display: flex;
	justify-content: center;
	gap: 50px;
}



.main_content {
	max-width: 100%;
	margin: auto;
	padding: 0 10px;
	overflow-x: hidden;
}

.table_allorder {
	width: 100%;
	word-wrap: break-word;
	border-collapse: collapse;
}

.table_allorder th, .table_allorder td {
	padding: 0px;
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

.cancel-button, .complete-button {
	background-color: #fffbeb;
}

#notificationMessage {
	display: none;
	color: red;
	font-weight: bold;
	cursor: pointer;
	position: fixed;
	top: 10px;
	right: 10px;
	background-color: #fff;
	border: 1px solid red;
	padding: 10px;
	border-radius: 5px;
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
	<!-- 确认消息框 -->

	<div id="customAlert1" class="custom-alert1">
		<p>餐廳已完成這筆訂單？</p>
		<div class="custom-alert-buttons1">
			<button class="confirm2" onclick="showConfirmationMessage1()">確定</button>
			<button class="cancel2" onclick="closeCustomAlert1()">取消</button>
		</div>
	</div>

	<div id="confirmationMessage1" class="confirmation-message1">
		<p class="cancel_message1">訂單已完成！</p>
	</div>
	<!--    ================================================================ -->
	<jsp:include page="header_restaurant.jsp" flush="true" />

	<div class="bodycontent">
		<div id="notificationMessage"
			style="display: none; color: red; font-weight: bold;"></div>
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
				<button class="btn_search"
					style="background-color: #fdc001; border: #fdc001;">
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
				<div class="pagination-info1">
					<form
						action="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest"
						method="get">
						<input type="hidden" name="action" value="getOrderStatus1Rest">
						跳轉到第 <input class="search_page" type="number" name="page" min="1"
							max="${totalPages}" value="${currentPage}"
							style="width: 50px; height: 25px; text-align: center;"> 頁
						<button type="submit" class="page_submit">check</button>
						<span class="total_page">總共 ${totalPages} 頁</span>
					</form>

				</div>

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
						<th>完成訂單</th>
						<th>取消訂單</th>
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
							<td>
								<button type="button" class="complete-button"
									onclick="showCancelConfirmation1(${myorder.orderId})">
									<svg id="completeOrderIcon" xmlns="http://www.w3.org/2000/svg"
										width="30" height="30" fill="currentColor"
										class="bi bi-check-square" viewBox="0 0 16 16">
  								<path
											d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z" />
  								<path
											d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z" />
								</svg>
								</button>
							</td>


							<td>
								<button type="button" class="cancel-button"
									onclick="showCancelConfirmation(${myorder.orderId})">
									<svg id="cancelOrderIcon" xmlns="http://www.w3.org/2000/svg"
										width="30" height="30" fill="currentColor"
										class="bi bi-x-square" viewBox="0 0 16 16">
                                    <path
											d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z" />
                                    <path
											d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708" />
                                </svg>
								</button>
							</td>
						</tr>
					</c:forEach>
				</table>

				<c:if test="${totalPages > 1}">
					<div class="pagination">
						<c:if test="${currentPage > 1}">
							<button class="page_number">
								<a style="color: black;"
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest&page=1"><svg
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
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest&page=${currentPage - 1}"><svg
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
												href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest&page=${page}">${page}</a>
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
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest&page=${currentPage + 1}"><svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-chevron-right"
										viewBox="0 0 16 16">
  							<path fill-rule="evenodd"
											d="M4.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L10.293 8 4.646 2.354a.5.5 0 0 1 0-.708" />
							</svg></a>
							</button>
							<button class="page_number">
								<a style="color: black;"
									href="<%=request.getContextPath() %>/myorder/restmyorder.do?action=getOrderStatus1Rest&page=${totalPages}"><svg
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
	<!-- ===================================================================================== -->
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
                    window.location.href = '${pageContext.request.contextPath}/myorder/restmyorder.do?action=getOrderStatus1Rest';
                }
            });
        });
    </script>

	<!--     ================================================================================== -->
	<script>
   function showCancelConfirmation(orderId) {
       var customAlert = document.getElementById("customAlert");
       var confirmButton = document.querySelector(".confirm1");

       // 将 orderId 设置到确认按钮的属性中，以便后续提交表单
       confirmButton.setAttribute("data-order-id", orderId);
       
       customAlert.style.display = "block";
   }

   function closeCustomAlert() {
       var customAlert = document.getElementById("customAlert");
       customAlert.style.display = "none";
   }

   function showConfirmationMessage() {
       var orderId = document.querySelector(".confirm1").getAttribute("data-order-id");

       // 创建一个隐藏的表单来提交请求
       var form = document.createElement("form");
       form.method = "post";
       form.action = "${pageContext.request.contextPath}/myorder/restmyorder.do";
       
       var actionInput = document.createElement("input");
       actionInput.type = "hidden";
       actionInput.name = "action";
       actionInput.value = "updateOrderStatus2Rest";
       form.appendChild(actionInput);

       var orderIdInput = document.createElement("input");
       orderIdInput.type = "hidden";
       orderIdInput.name = "orderId";
       orderIdInput.value = orderId;
       form.appendChild(orderIdInput);

       document.body.appendChild(form);
       form.submit();

       // 显示确认消息框
       var confirmationMessage = document.getElementById("confirmationMessage");
       confirmationMessage.style.display = "block";

       // 2秒钟后隐藏确认消息框
       setTimeout(function() {
           confirmationMessage.style.display = "none";
       }, 5000);
   }

        
//         ========================================================================================================================================
	  
	   function showCancelConfirmation1(orderId) {
            var customAlert1 = document.getElementById("customAlert1");
            var confirmButton1 = document.querySelector(".confirm2");

            // 将 orderId 设置到确认按钮的属性中，以便后续提交表单
            confirmButton1.setAttribute("data-order-id1", orderId);
            
            customAlert1.style.display = "block";
        }

        function closeCustomAlert1() {
            var customAlert1 = document.getElementById("customAlert1");
            customAlert1.style.display = "none";
        }

        function showConfirmationMessage1() {
            var orderId = document.querySelector(".confirm2").getAttribute("data-order-id1");

            // 创建一个隐藏的表单来提交请求
            var form = document.createElement("form");
            form.method = "post";
            form.action = "${pageContext.request.contextPath}/myorder/restmyorder.do";
            
            var actionInput = document.createElement("input");
            actionInput.type = "hidden";
            actionInput.name = "action";
            actionInput.value = "updateOrderStatus3Rest";
            form.appendChild(actionInput);

            var orderIdInput = document.createElement("input");
            orderIdInput.type = "hidden";
            orderIdInput.name = "orderId";
            orderIdInput.value = orderId;
            form.appendChild(orderIdInput);

            document.body.appendChild(form);
            form.submit();
            
            // 显示确认消息框
            var confirmationMessage1 = document.getElementById("confirmationMessage1");
            confirmationMessage1.style.display = "block";

            // 2秒钟后隐藏确认消息框
            setTimeout(function() {
                confirmationMessage1.style.display = "none";
            }, 5000);
        }
    </script>

	<!--     ===================================================================== -->


	</script>
</body>
</html>
