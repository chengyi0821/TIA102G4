<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 餐廳主頁</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 确保垂直居中 */
        }

        .container {
            padding: 40px;
            margin: 0 auto;
            max-width: 600px;
            background-color: #fff3e0;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .restaurant-info {
            font-size: 18px;
            color: #4D3900;
            line-height: 1.8;
            flex: 1;
        }

        .restaurant-info label {
            font-weight: bold;
        }

        .restaurant-photo {
            width: 150px;
            height: 150px;
            background-color: #fff;
            border: 5px solid #fdc001;
            border-radius: 10px;
            text-align: center;
            line-height: 150px;
            font-size: 24px;
            color: #4D3900;
            margin-left: 20px;
        }

        .restaurant-photo img {
            max-width: 100%;
            max-height: 100%;
            border-radius: 10px;
        }

        .edit-button {
            display: block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #4D3900;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            font-weight: bold;
            text-decoration: none;
        }

        .edit-button:hover {
            background-color: #fdc001;
            color: #4D3900;
        }

        .logout-button {
            display: block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #d9534f;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            font-weight: bold;
            text-decoration: none;
        }

        .logout-button:hover {
            background-color: #c9302c;
        }

        .button-container {
            text-align: right;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="restaurant-info">
            <div><label>餐廳名稱：</label><c:out value="${restaurant.restName}"/></div>
            <div><label>電話：</label><c:out value="${restaurant.phone}"/></div>
            <div><label>信箱：</label><c:out value="${restaurant.email}"/></div>
            <div><label>餐廳描述：</label><c:out value="${restaurant.description}"/></div>
            <div><label>營業時間：</label><c:out value="${restaurant.openDay}"/></div>
        </div>
        <div class="restaurant-photo">
            <img src="path/to/photo" alt="餐廳照片">
            <div>餐廳照片</div>
        </div>
    </div>
    <div class="button-container">
        <a href="restMainEdit.jsp" class="edit-button">編輯信息</a>
        <a href="${pageContext.request.contextPath}/restLogout" class="logout-button">登出</a>
    </div>
</body>
</html>
