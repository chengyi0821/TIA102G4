<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>編輯訂單</title>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link href="../css/style.css" rel="stylesheet">
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
   .submit-button{
   background-color:#ffc107;
   border:1px solid #ffc107;
   border-radius:20px;
   width:100px;
   height:35px;
   font-size:17px;  
   }
   
   .submit-button:hover{
   background-color:#F195B2;
   border:1px solid #F195B2;
   }
  
   </style>
</head>
<body>
	<jsp:include page="header_member.jsp"></jsp:include>
     <!-- 提示消息 -->
    <div id="successMessage" class="success-message">訂單更新成功</div>
    

    <form class="form-container" action="<%=request.getContextPath() %>/myorder/membermyorder.do" method="post" onsubmit="showSuccessMessage();">
     <h2>編輯訂單</h2>
        <input type="hidden" name="action" value="updateOrder">
        <input type="hidden" name="orderId" value="${order.orderId}">

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
    	<input type="date" id="reserDate" name="reserDate" value="${order.reserDate}">
    	<span id="dateErrorMsg" style="color: red; display: none;">請輸入訂位日期</span><br>
		</div>

		<div class="form-group">
    	<label for="reserTime">訂位時間:<span class="red">*</span></label>
    	<input type="time" id="reserTime" name="reserTime" value="${order.formattedReserTime}">
    	<span id="timeErrorMsg" style="color: red; display: none;">請輸入訂位時間</span><br>
		</div>

		<div class="form-group">
    	<label for="reserPeopleNumber">人數:<span class="red">*</span></label>
    	<input type="number" id="reserPeopleNumber" name="reserPeopleNumber" 
           value="${order.reserPeopleNumber}" min="1" max="${order.event.maxSeat}">
    	<span id="numErrorMsg" style="color: red; display: none;">請輸入訂位人數，不得超過 ${order.event.maxSeat} 人</span><br>
		</div>

	
		<div class="form-group">
        <label for="reserNote">備註:</label>
        <textarea id="reserNote" name="reserNote" rows="4">${order.reserNote}</textarea><br>
		</div>
		
		 <div class="form-group">
       <button type="submit" id="submitButton" class="submit-button" disabled>更新訂單</button>
        </div>
    </form>
    
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
   
    var reserDateInput = document.getElementById('reserDate');
    var reserTimeInput = document.getElementById('reserTime');
    var reserPeopleNumberInput = document.getElementById('reserPeopleNumber');
    var submitButton = document.getElementById('submitButton');

    var dateErrorMsg = document.getElementById('dateErrorMsg');
    var timeErrorMsg = document.getElementById('timeErrorMsg');
    var numErrorMsg = document.getElementById('numErrorMsg');

    function validateForm() {
        var isValid = true;

        
        if (!reserDateInput.value) {
            dateErrorMsg.style.display = 'inline';
            isValid = false;
        } else {
            dateErrorMsg.style.display = 'none';
        }

      
        if (!reserTimeInput.value) {
            timeErrorMsg.style.display = 'inline';
            isValid = false;
        } else {
            timeErrorMsg.style.display = 'none';
        }

     
        if (!reserPeopleNumberInput.value) {
            numErrorMsg.style.display = 'inline';
            isValid = false;
        } else {
            numErrorMsg.style.display = 'none';
        }

    
        submitButton.disabled = !isValid;
    }


    document.addEventListener('DOMContentLoaded', function() {
        validateForm();
    });


    reserDateInput.addEventListener('input', validateForm);
    reserTimeInput.addEventListener('input', validateForm);
    reserPeopleNumberInput.addEventListener('input', validateForm);
	</script>


	<script>
    function getMinDate() {
        var today = new Date();
        today.setDate(today.getDate() + 3); 

   
        var day = ("0" + today.getDate()).slice(-2);
        var month = ("0" + (today.getMonth() + 1)).slice(-2);
        var year = today.getFullYear();

        return year + "-" + month + "-" + day;
    }


    document.addEventListener('DOMContentLoaded', function() {
        var reserDateInput = document.getElementById('reserDate');
        reserDateInput.setAttribute('min', getMinDate());
    });
</script>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var reserPeopleNumberInput = document.getElementById('reserPeopleNumber');
        var numErrorMsg = document.getElementById('numErrorMsg');
        var submitButton = document.getElementById('submitButton');
        var maxSeat = ${order.event.maxSeat}; 

        reserPeopleNumberInput.setAttribute('max', maxSeat);

    
        reserPeopleNumberInput.addEventListener('input', function() {
            var currentValue = parseInt(reserPeopleNumberInput.value, 10);

            if (currentValue > maxSeat) {
                numErrorMsg.style.display = 'inline'; 
                submitButton.disabled = true; 
            } else {
                numErrorMsg.style.display = 'none'; 
                submitButton.disabled = false; 
            }
        });
    });
</script>


</body>
</html>