<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 編輯會員資料</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* 確保垂直居中 */
        }

        .container {
            padding: 40px;
            margin: 0 auto;
            max-width: 600px;
            background-color: #fff3e0;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
        }

        h1 {
            text-align: center;
            color: #4D3900;
            margin-bottom: 20px;
        }

        .form-group {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        label {
            flex: 1;
            font-weight: bold;
            color: #4D3900;
            text-align: left;
            margin-right: 20px;
        }

        input[type="text"], input[type="email"], input[type="date"], input[type="file"], input[type="password"] {
            flex: 2;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            color: #4D3900;
        }

        .button-container {
            text-align: center;
        }

        button {
            padding: 10px 20px;
            background-color: #4D3900;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            min-width: 120px;
        }

        button:hover {
            background-color: #fdc001;
            color: #4D3900;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>編輯會員資料</h1>
        <form action="${pageContext.request.contextPath}/memberUpdate" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">會員名稱：</label>
                <input type="text" id="name" name="name" value="王嘻嘻" required>
            </div>
            <div class="form-group">
                <label for="gender">會員性別：</label>
                <select id="gender" name="gender" required>
                    <option value="男" selected>男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="form-group">
                <label for="birthday">會員生日：</label>
                <input type="date" id="birthday" name="birthday" value="1992-07-16" required>
            </div>
            <div class="form-group">
                <label for="phone">會員電話：</label>
                <input type="text" id="phone" name="phone" value="09212345678" required>
            </div>
            <div class="form-group">
                <label for="email">會員帳號 (信箱)：</label>
                <input type="email" id="email" name="email" value="chuchuwang@gmail.com" required>
            </div>
            <div class="form-group">
                <label for="photo">會員照片：</label>
                <input type="file" id="photo" name="photo">
            </div>
            <div class="form-group">
                <label for="password">新密碼：</label>
                <input type="password" id="password" name="password" placeholder="若無修改請留空">
            </div>
            <div class="button-container">
                <button type="submit">儲存修改</button>
                <button type="button" onclick="window.location.href='memberProfile.jsp'">取消</button>
            </div>
        </form>
    </div>
</body>
</html>
