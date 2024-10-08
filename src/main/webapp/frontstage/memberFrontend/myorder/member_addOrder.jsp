<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>編輯訂單</title>
     <link rel="stylesheet"
 	href="<%=request.getContextPath() %>/frontstage/backend/myorder/css/style.css">
	  <link rel="stylesheet"
 	href="<%=request.getContextPath() %>/frontstage/backend/myorder/css/style.memberorder.css">
</head>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>編輯訂單</title>
    <link href="../css/style.css" rel="stylesheet">
   <style>
   

  
   </style>
</head>
<body>
	<jsp:include page="header_member.jsp"></jsp:include>
     <!-- 提示消息 -->
    <div id="successMessage" class="success-message">訂單送出成功</div>
    
    
    

   
    
  	<div class="form-container">
     <h2>訂單資訊</h2>

	
		<div class="form-group">
        <label for="memberName">主揪人:</label>
        <input style="background-color:	#F0F0F0;" type="text" id="memberName" name="memberName" value="${order.member.name}" readonly><br>
        </div>

		<div class="form-group">
        <label for="restName">餐廳:</label>
        <input style="background-color:	#F0F0F0;" type="text" id="restName" name="restName" value="${order.restaurant.restName}" readonly><br>
		</div>
		
		<div class="form-group">
        <label for="phone">電話:</label>
        <input style="background-color:	#F0F0F0;" type="text" id="phone" name="phone" value="${order.member.mobileNo}" readonly><br>
		</div>
		
		<div class="form-group">
        <label for="orderDate">訂單日期:</label>
        <input style="background-color:	#F0F0F0;" type="text" id="orderDate" name="orderDate" value="${fn:substring(order.orderDate, 0, 16)}" readonly><br>
		</div>

		<div class="form-group">
        <label for="reserDate">訂位日期:<span class="red">*</span></label>
        <input type="date" id="reserDate" name="reserDate" value="${order.reserDate}"><br>
		</div>
		
		<div class="form-group">
        <label for="reserTime">訂位時間:<span class="red">*</span></label>
        <input type="time" id="reserTime" name="reserTime" value="${order.formattedReserTime}"><br>
		</div>
		
		<div class="form-group">
        <label for="reserPeopleNumber">人數:<span class="red">*</span><c:if test="${not empty errorMessage}">
        <span class="error-message">${errorMessage}</span>
    	</c:if></label>
        <input type="number" id="reserPeopleNumber" name="reserPeopleNumber" value="${order.reserPeopleNumber}" min="1"><br>
		</div>
	
		<div class="form-group">
        <label for="reserNote">備註:</label>
        <textarea id="reserNote" name="reserNote" rows="4">${order.reserNote}</textarea><br>
		</div>
		
		
          <form action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post">
            <input type="hidden" name="action" value="addOrder">
<%--             <input type="hidden" name="memberId" value="${order.memberId}"> --%>
<%--             <input type="hidden" name="memberName" value="${order.memberName}"> --%>
            <input type="hidden" name="phone" value="${order.phone}">
            <input type="hidden" name="reserDate" value="${order.reserDate}">
            <input type="hidden" name="reserTime" value="${order.reserTime}">
            <input type="hidden" name="reserPeopleNumber" value="${order.reserPeopleNumber}">
            <input type="hidden" name="reserNote" value="${order.reserNote}">
            <button type="submit" class="submit-button">送出訂單</button>
        </form>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
     <script>
        function showSuccessMessage() {
            var message = document.getElementById("successMessage");
            message.style.display = "block";
            setTimeout(function() {
                message.style.display = "none";
                window.location.href = "${pageContext.request.contextPath}/myorder/membermyorder.do?action=getOrderStatus1Mem";
            }, 3000);
        }
    </script>
    
    <script>
    document.getElementById('reserPeopleNumber').addEventListener('input', function (e) {
        if (e.target.value < 0) {
            e.target.value = 1;  
        }
    });
	</script>

   <script>
        var errorMessageDiv = document.querySelector('.error-message');
        if (errorMessageDiv && errorMessageDiv.textContent.trim() !== '') {
            errorMessageDiv.style.display = 'block';
        }
    </script>

<script>
    var errorMessage = "${errorMsg}";
    if (errorMessage && errorMessage.trim() !== '') {
        document.getElementById("errorMsg").style.display = "block";
    }
</script>

</body>
</html>