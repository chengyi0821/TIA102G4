<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 會員主頁</title>
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
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .member-info {
            font-size: 18px;
            color: #4D3900;
            line-height: 1.8;
            flex: 1;
        }

        .member-info label {
            font-weight: bold;
        }

        .member-photo {
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

        .member-photo img {
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

        .edit-container {
            text-align: right;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="member-info">
            <div><label>會員編號：</label>M20240719001</div>
            <div><label>會員名稱：</label>王嘻嘻</div>
            <div><label>會員性別：</label>男</div>
            <div><label>會員生日：</label>1992/07/16</div>
            <div><label>會員電話：</label>09212345678</div>
            <div><label>會員帳號 (信箱)：</label>chuchuwang@gmail.com</div>
        </div>
        <div class="member-photo">
            <img src="path/to/member/photo.jpg" alt="會員照片">
            <div>會員照片</div>
        </div>
    </div>
    <div class="edit-container">
        <a href="editMember.jsp" class="edit-button">編輯頁面 / 修改密碼</a>
    </div>
</body>
</html>
