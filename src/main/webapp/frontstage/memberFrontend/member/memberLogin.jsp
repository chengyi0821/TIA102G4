<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 登入</title>
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
        .checkbox-container label {
            font-size: 14px;
        }
        input, button {
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        }
        input:focus {
            border-color: #ffd1dc;
            box-shadow: 0 0 5px #ffd1dc;
        }
        button {
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            color: #4D3900;
            width: 100%;
            padding: 15px;
        }
        button:hover {
            background-color: #fdc001;
            color: #fff;
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
        .checkbox-container {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        .checkbox-container input {
            margin-right: 5px;
        }
        .links-container {
            display: flex;
            justify-content: space-between;
            margin-top: 10px;
        }
        a {
            color: pink;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }
        a:hover, a:focus {
            color: #d9534f;
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
    <h2>Chugether* Login　會員登入</h2>

    <form id="loginForm" action="/memberLogin" method="POST">
        <div class="form-group">
            <label for="account">會員帳號：</label>
            <input type="text" id="account" name="account" placeholder="請輸入帳號" required>
        </div>
        
        <div class="form-group">
            <label for="password">會員密碼：</label>
            <input type="password" id="password" name="password" placeholder="請輸入密碼" required>
        </div><br/>
        
        <div class="checkbox-container">
            <input type="checkbox" name="rememberMe"> <label for="rememberMe"> 記住帳號</label>
        </div>
        
        <div class="checkbox-container">
            <input type="checkbox" id="showPasswordCheckbox"> <label for="showPasswordCheckbox"> 顯示密碼</label>
        </div><br/>
        
        <div class="button-container">
            <button type="submit">登入</button>
        </div><br/>
        
        <div class="links-container">
            <a href="memberForgotPassword.jsp">Forgot Password 忘記密碼</a>
            <a href="memberRegister.jsp">Register 會員註冊</a>
        </div>
    </form>

    <div id="loginResult"></div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p id="modalMessage"></p>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('#loginForm').submit(function(event) {
                event.preventDefault();

                var formData = $(this).serialize();
                $.post('/TIA102G4/memberLogin', formData, function(response) {
                    if (response.success) {
                        showModal('登入成功! 歡迎來到Chugether* 一起來揪團吧!', functin() {
                            window.location.href = 'index.jsp';
                        });
                    } else {
                        showModal('登入失敗： ' + response.message, function() {
                            // 更新表單
                            $('input[name="account"]').val(response.account);
                            $('input[name="password"]').val(''); 
                            $('input[name="rememberMe"]').prop('checked', response.rememberMe); 
                        });
                    }
                });
            });

            // 顯示/隱藏密碼
            $('#showPasswordCheckbox').change(function() {
                var passwordInput = $('#password');
                if ($(this).is(':checked')) {
                    passwordInput.attr('type', 'text');
                } else {
                    passwordInput.attr('type', 'password');
                }
            });

            // 自定義模態框的顯示和隱藏
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
