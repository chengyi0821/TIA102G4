<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 會員註冊</title>
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
        input, button {
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        }
        input[readonly] {
            background-color: #f0f0f0;
        }
        input:focus {
            border-color: #ffd1dc;  /* 設定點擊欄位輸入時的邊框顏色為淺粉色 */
            box-shadow: 0 0 5px #ffd1dc;  /* 設定點擊欄位輸入時的陰影顏色為淺粉色 */
        }
        .radio-container {
            display: flex;
            align-items: center;
        }
        .radio-container label {
            margin-right: 20px;
        }
        .checkbox-container {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        .checkbox-container input {
            margin-right: 5px;
        }
        .checkbox-container label {
            font-size: 14px;
        }
        .form-group label[for="sticker"] + input {
            flex: none;
        }
        .photo-note {
            display: block;
            font-size: 12px;
            color: #d9534f;  /* 改為指定顏色 */
            text-align: right;
            margin-left: 130px;
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
        button {
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
            color: #4D3900;
        }
        button:hover {
            background-color: #fdc001;
            color: #fff;
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background-color: #fdf3f3;
            margin: auto;
            padding: 30px;
            border: 5px solid #fdc001;
            width: 80%;
            max-width: 600px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            color: #4D3900;
            font-size: 20px;
            font-weight: bold;
        }
        .close {
            color: #fdc001;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: #d9534f;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Chugether* 會員註冊</h2>
    <form id="registerForm" action="/memberRegister" method="POST" enctype="multipart/form-data">
        <div class="form-group">
            <label for="account">會員帳號：</label>
            <input type="text" id="account" name="account" placeholder="請輸入Email帳號" required>
        </div>
        
        <div class="form-group">
            <label for="password">會員密碼：</label>
            <input type="password" id="password" name="password" placeholder="請輸入密碼" required>
        </div>
        
        <div class="form-group">
            <label for="confirmPassword">確認密碼：</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="請確認密碼" required>
        </div>
        
        <div class="form-group">
            <label for="name">會員姓名：</label>
            <input type="text" id="name" name="name" placeholder="請輸入姓名" required>
        </div>
        
        <div class="form-group">
            <label>會員性別:</label>
            <div class="radio-container">
                <label><input type="radio" name="gender" value="true" required> 男</label>
                <label><input type="radio" name="gender" value="false" required> 女</label>
            </div>
        </div>
        
        <div class="form-group">
            <label for="mobileNo">手機號碼：</label>
            <input type="text" id="mobileNo" name="mobileNo" placeholder="請輸入手機號碼" required>
        </div>
        
        <div class="form-group">
            <label for="email">電子郵件：</label>
            <input type="email" id="email" name="email" placeholder="需與會員帳號相同" readonly>
        </div>

        <div class="form-group">
            <label for="sticker">會員照片：</label>
            <input type="file" id="sticker" name="sticker" accept="image/*" required>
        </div>
        <span class="photo-note">（請上傳小於 2MB 的圖片）</span>
        
        <div class="checkbox-container">
            <input type="checkbox" id="showPassword"> <label for="showPassword">顯示密碼</label>
        </div>
        
        <div class="button-container">
            <button type="submit">註冊</button>
        </div>
    </form>
    <div id="registerResult"></div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p id="modalMessage"></p>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('input[name="account"]').on('input', function() {
                $('input[name="email"]').val($(this).val());
            });

            $('#showPassword').on('change', function() {
                var passwordField = $('#password');
                var confirmPasswordField = $('#confirmPassword');
                if ($(this).is(':checked')) {
                    passwordField.attr('type', 'text');
                    confirmPasswordField.attr('type', 'text');
                } else {
                    passwordField.attr('type', 'password');
                    confirmPasswordField.attr('type', 'password');
                }
            });

            $('#registerForm').submit(function(event) {
                event.preventDefault();

                var account = $('input[name="account"]').val();
                var password = $('input[name="password"]').val();
                var confirmPassword = $('input[name="confirmPassword"]').val();

                var accountPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
                var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,15}$/;

                if (!accountPattern.test(account)) {
                    showModal('無效的電子郵件格式。');
                    return;
                }

                if (!passwordPattern.test(password)) {
                    showModal('密碼必須為 6-15 個字符長，並包含字母和數字。');
                    return;
                }

                if (password !== confirmPassword) {
                    showModal('密碼不匹配。');
                    return;
                }

                var formData = new FormData(this);
                $.ajax({
                    url: '/TIA102G4/memberRegister',
                    type: 'POST',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function(response) {
                        if (response.success) {
                            showModal('註冊成功!', function() {
                                window.location.href = 'memberLogin.jsp';
                            });
                        } else {
                            showModal('註冊失敗: ' + response.message);
                        }
                    }
                });
            });

            function showModal(message, callback) {
                $('#modalMessage').text(message);
                $('#myModal').css('display', 'flex').hide().fadeIn();
                $('.close').click(function() {
                    $('#myModal').fadeOut();
                    if (callback) callback();
                });
                $(window).click(function(event) {
                    if ($(event.target).is('#myModal')) {
                        $('#myModal').fadeOut();
                        if (callback) callback();
                    }
                });
            }
        });
    </script>
</body>
</html>


