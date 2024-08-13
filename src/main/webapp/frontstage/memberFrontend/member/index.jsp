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
            position: relative;
        }

        .member-info {
            font-size: 18px;
            color: #4D3900;
            line-height: 1.8;
            flex: 1;
            max-width: 500px;
        }

        .member-info label {
            font-weight: bold;
            width: 140px;
            display: inline-block;
            text-align: right;
            margin-right: 20px;
        }

        .member-info input[type="text"],
        .member-info input[type="email"],
        .member-info input[type="date"],
        .member-info input[type="tel"] {
            width: 60%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            color: #4D3900;
            margin-bottom: 15px;
            display: inline-block;
        }

        .member-info input[readonly] {
            background-color: #e0e0e0; /* 灰色背景 */
            color: #999;
        }

        .member-photo {
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

        .member-photo img {
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

        .edit-form {
            display: none;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }

        .edit-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            max-width: 600px;
            width: 100%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
        }

        .form-group {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }

        .form-group label {
            width: 140px;
            text-align: right;
            margin-right: 20px;
        }

        .form-group input,
        .form-group select {
            width: calc(100% - 160px);
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        }

        .form-group input[readonly] {
            background-color: #e0e0e0; /* 灰色背景 */
            color: #999;
        }

        .form-group .gender-options {
            width: calc(100% - 160px);
            display: flex;
            justify-content: space-between;
        }

        .save-button {
            background-color: #4D3900;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .save-button:hover {
            background-color: #fdc001;
            color: #4D3900;
        }

        .cancel-button {
            background-color: #d9534f;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            margin-left: 10px;
            transition: background-color 0.3s ease;
        }

        .cancel-button:hover {
            background-color: #c9302c;
        }

        .logout-button {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: #d9534f;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .logout-button:hover {
            background-color: #c9302c;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="member-info">
            <div class="form-group">
                <label for="memberId">會員編號：</label>
                <input type="text" id="memberId" value="M20240719001" readonly>
            </div>
            <div class="form-group">
                <label for="email">會員帳號：</label>
                <input type="email" id="email" value="chuchuwang@gmail.com" readonly>
            </div>
            <div class="form-group">
                <label for="name">會員名稱：</label>
                <input type="text" id="name" value="王嘻嘻" required>
            </div>
            <div class="form-group">
                <label for="gender">會員性別：</label>
                <div class="gender-options">
                    <input type="radio" id="male" name="gender" value="男" checked> <label for="male">男</label>
                    <input type="radio" id="female" name="gender" value="女"> <label for="female">女</label>
                </div>
            </div>
            <div class="form-group">
                <label for="birthdate">會員生日：</label>
                <input type="date" id="birthdate" value="1992-07-16" required>
            </div>
            <div class="form-group">
                <label for="phone">會員電話：</label>
                <input type="tel" id="phone" value="09212345678" required>
            </div>
        </div>
        <div class="member-photo">
            <img src="path/to/member/photo.jpg" alt="會員照片" id="memberPhoto">
        </div>
        <div class="button-container">
            <button onclick="toggleEditForm()">編輯信息</button>
            <a href="${pageContext.request.contextPath}/logout" class="logout-button">登出</a>
        </div>
    </div>

    <div class="edit-form" id="editForm">
        <div class="edit-container">
            <form action="${pageContext.request.contextPath}/memberUpdate" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="memberId">會員編號：</label>
                    <input type="text" id="memberId" name="memberId" value="1 (系統自增不可修改)" readonly>
                </div>
                <div class="form-group">
                    <label for="email">會員帳號：</label>
                    <input type="email" id="email" name="email" value="chuchuwang@gmail.com" readonly>
                </div>
                <div class="form-group">
                    <label for="name">會員名稱：</label>
                    <input type="text" id="name" name="name" value="王嘻嘻" required>
                </div>
                <div class="form-group">
                    <label for="gender">會員性別：</label>
                    <div class="gender-options">
                        <input type="radio" id="male" name="gender" value="男" checked> <label for="male">男</label>
                        <input type="radio" id="female" name="gender" value="女"> <label for="female">女</label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="birthdate">會員生日：</label>
                    <input type="date" id="birthdate" name="birthdate" value="1992-07-16" required>
                </div>
                <div class="form-group">
                    <label for="phone">會員電話：</label>
                    <input type="tel" id="phone" name="phone" value="09212345678" required>
                </div>
                <div class="form-group">
                    <label for="photo">上傳新照片：</label>
                    <input type="file" id="photo" name="photo">
                </div>
                <div class="button-container">
                    <button type="submit" class="save-button">儲存變更</button>
                    <button type="button" class="cancel-button" onclick="toggleEditForm()">取消</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        function toggleEditForm() {
            const editForm = document.getElementById('editForm');
            editForm.style.display = editForm.style.display === 'flex' ? 'none' : 'flex';
        }

        document.querySelector('.logout-button').addEventListener('click', function(e) {
            e.preventDefault();
            if (confirm('確定要登出嗎？')) {
                window.location.href = this.href;
            }
        });
    </script>
</body>
</html>
