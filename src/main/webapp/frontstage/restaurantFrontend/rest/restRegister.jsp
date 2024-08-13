<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 餐廳註冊</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 20px;
            height: 100vh;
            overflow-y: auto;
        }

        .container {
            padding: 40px;
            margin: 0 auto;
            max-width: 800px; /* 增加表單寬度 */
            background-color: #fff3e0;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
        }

        h1 {
            text-align: center;
            color: #4D3900;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        label {
            flex: 1;
            font-weight: bold;
            color: #4D3900;
            margin-right: 10px;
        }

        input[type="text"], input[type="password"], input[type="email"], input[type="tel"], input[type="time"], textarea, select {
            flex: 2;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            color: #4D3900;
        }

        input[type="file"] {
            flex: 2;
            padding: 5px;
            margin-left: 10px;
            color: #4D3900;
        }

        input[readonly] {
            background-color: #e9ecef; /* 灰底 */
        }

        .checkbox-group {
            display: flex;
            flex-wrap: wrap;
            margin-bottom: 20px;
        }

        .checkbox-group label {
            flex: 1;
            margin-right: 10px;
            color: #4D3900;
            font-weight: bold; /* 設置為粗體 */
        }

        .checkbox-group input[type="checkbox"] {
            margin-right: 5px;
        }

        button {
            width: 100%;
            padding: 15px;
            background-color: #4D3900;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
        }

        button:hover {
            background-color: #fdc001;
            color: #4D3900;
        }

        textarea {
            resize: vertical;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>餐廳註冊</h1>
        <form id="registerForm" action="${pageContext.request.contextPath}/restRegister" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="account">餐廳帳號：</label>
                <input type="email" id="account" name="account" required>
            </div>

            <div class="form-group">
                <label for="password">餐廳密碼：</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="confirmPassword">確認密碼：</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>

            <div class="form-group">
                <label for="rest_name">餐廳名稱：</label>
                <input type="text" id="rest_name" name="rest_name" required>
            </div>

            <div class="form-group">
                <label for="type_id">餐廳類別：</label>
                <select id="type_id" name="type_id" required>
                    <option value="">請選擇餐廳類別</option>
                    <option value="1">中式料理</option>
                    <option value="2">日式料理</option>
                    <option value="3">韓式料理</option>
                    <option value="4">美式料理</option>
                    <option value="5">義式料理</option>
                    <option value="6">法式料理</option>
                    <option value="7">泰式料理</option>
                </select>
            </div>

            <div class="form-group">
                <label for="phone">餐廳電話：</label>
                <input type="tel" id="phone" name="phone" required>
            </div>

            <div class="form-group">
                <label for="email">聯絡信箱：</label>
                <input type="email" id="email" name="email" readonly required>
            </div>

            <div class="form-group">
                <label for="location">餐廳位置：</label>
                <input type="text" id="location" name="location">
            </div>

            <div class="form-group">
                <label for="description">餐廳描述：</label>
                <textarea id="description" name="description" rows="4">${param.description != null ? param.description : ''}</textarea>
            </div>

            <div class="checkbox-group">
                <label>營業日：</label>
                <label><input type="checkbox" name="open_days" value="Monday"> 星期一</label>
                <label><input type="checkbox" name="open_days" value="Tuesday"> 星期二</label>
                <label><input type="checkbox" name="open_days" value="Wednesday"> 星期三</label>
                <label><input type="checkbox" name="open_days" value="Thursday"> 星期四</label>
                <label><input type="checkbox" name="open_days" value="Friday"> 星期五</label>
                <label><input type="checkbox" name="open_days" value="Saturday"> 星期六</label>
                <label><input type="checkbox" name="open_days" value="Sunday"> 星期日</label>
            </div>

            <div class="form-group">
                <label for="open_time1">營業時段：</label>
                <input type="time" id="open_time1" name="open_time1">
            </div>

            <div class="form-group">
                <label for="close_time1">打烊時段：</label>
                <input type="time" id="close_time1" name="close_time1">
            </div>

            <div class="form-group">
                <label for="sticker">餐廳照片：</label>
                <input type="file" id="sticker" name="sticker" accept="image/*" required>
                <small>請上傳小於 2MB 的圖片</small>
            </div>

            <button type="submit">註冊</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@ckeditor/ckeditor5-build-classic/build/ckeditor.js"></script>
    <script>
        ClassicEditor
            .create(document.querySelector('#description'))
            .catch(error => {
                console.error(error);
            });

        // 帳號與信箱同步
        document.getElementById('account').addEventListener('input', function() {
            document.getElementById('email').value = this.value;
        });

        // 表單驗證
        document.getElementById('registerForm').addEventListener('submit', function(event) {
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirmPassword').value;
            var fileInput = document.getElementById('sticker');
            var fileSize = fileInput.files[0].size;

            // 密碼驗證
            var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,15}$/;
            if (!passwordPattern.test(password)) {
                alert('密碼必須為 6-15 個字符長，並包含字母和數字。');
                event.preventDefault();
                return;
            }

            // 確認密碼
            if (password !== confirmPassword) {
                alert('兩次輸入的密碼不相符，請重新輸入。');
                event.preventDefault();
                return;
            }

            // 圖片大小驗證
            if (fileSize > 2 * 1024 * 1024) { // 2MB
                alert('圖片大小不能超過 2MB，請選擇其他圖片。');
                event.preventDefault();
            }
        });
    </script>
</body>
</html>
