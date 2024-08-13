<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 編輯管理員</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f8f8;
            margin: 0;
            flex-direction: column;
            font-family: 'Arial', sans-serif;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #4D3900;
        }

        form {
            display: flex;
            flex-direction: column;
            width: 500px;
            padding: 40px;
            border-radius: 15px;
            background-color: #fff3e0;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
            justify-content: flex-start;
        }

        label {
            margin-right: 10px;
            font-weight: bold;
            font-size: 18px;
            width: 150px;
            text-align: left;
            color: #4D3900;
        }

        input, select, button {
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            width: 100%;
        }

        button {
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            color: #4D3900;
            margin-top: 20px;
        }

        button:hover {
            background-color: #fdc001;
            color: #fff;
        }

        .button-container {
            display: flex;
            justify-content: center;
        }

        /* 灰色背景色和只讀樣式 */
        input[readonly] {
            background-color: #e0e0e0;
            color: #999;
        }
    </style>
</head>
<body>
    <h2>編輯管理員</h2>

    <form action="${pageContext.request.contextPath}/adminList?action=update" method="POST">
        <input type="hidden" name="adminId" value="${admin.adminId}">

        <div class="form-group">
            <label for="name">名稱：</label>
            <input type="text" id="name" name="name" value="${admin.name}" required>
        </div>
        
        <div class="form-group">
            <label for="account">帳號：</label>
            <input type="text" id="account" name="account" value="${admin.account}" readonly>
        </div>
        
        <div class="form-group">
            <label for="password">密碼：</label>
            <input type="password" id="password" name="password" value="${admin.password}" required>
        </div>
        
        <div class="form-group">
            <label for="permission">權限：</label>
            <select id="permission" name="permission">
                <option value="1" ${admin.permission == 1 ? 'selected' : ''}>管理員</option>
                <option value="2" ${admin.permission == 2 ? 'selected' : ''}>一般</option>
            </select>
        </div>

        <div class="button-container">
            <button type="submit">更新</button>
        </div>
    </form>
</body>
</html>
