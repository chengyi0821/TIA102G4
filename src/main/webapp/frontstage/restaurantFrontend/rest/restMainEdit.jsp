<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            min-height: 100vh;
            color: #4D3900;
            font-weight: bold;
        }

        .container {
            padding: 40px;
            margin: 0 auto;
            max-width: 800px;
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
            max-width: 500px;
        }

        .restaurant-info label {
            font-weight: bold;
            width: 140px;
            display: inline-block;
            text-align: right;
            margin-right: 20px;
        }

        .restaurant-photo {
            width: 200px;
            height: 200px;
            background-color: #fff;
            border: 5px solid #fdc001;
            border-radius: 10px;
            text-align: center;
            font-size: 24px;
            color: #4D3900;
            margin-left: 20px;
            position: relative;
        }

        .restaurant-photo img {
            max-width: 100%;
            max-height: 100%;
            border-radius: 10px;
        }

        .button-container {
            margin-top: 20px;
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        .button-container button, .button-container a {
            padding: 10px 20px;
            background-color: #4D3900;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            font-weight: bold;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .button-container button:hover, .button-container a:hover {
            background-color: #fdc001;
            color: #4D3900;
        }

        .logout-button {
            background-color: #d9534f;
            color: #fff;
        }

        .logout-button:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="restaurant-info">
            <div><label>餐廳帳號：</label><c:out value="${sessionScope.admin.account}"/></div>
            <div><label>餐廳名稱：</label><c:out value="${sessionScope.admin.restName}"/></div>
            <div><label>餐廳類別：</label><c:out value="${sessionScope.admin.typeId}"/></div>
            <div><label>餐廳電話：</label><c:out value="${sessionScope.admin.phone}"/></div>
            <div><label>聯絡信箱：</label><c:out value="${sessionScope.admin.email}"/></div>
            <div><label>餐廳位置：</label><c:out value="${sessionScope.admin.location}"/></div>
            <div><label>餐廳描述：</label><c:out value="${sessionScope.admin.description}"/></div>
            <div><label>營業時間：</label><c:out value="${sessionScope.admin.openDay}"/></div>
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
