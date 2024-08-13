<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 重設密碼</title>
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
            width: 100%;
        }

        button {
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
            color: #4D3900;
        }

        button:hover {
            background-color: #fdc001;
            color: #fff;
        }

        .button-container {
            display: flex;
            justify-content: center;
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
    <h2>Chugether - 重設密碼</h2>
    <form id="resetPasswordForm" action="/C080605/resetPassword" method="POST">
        <div class="form-group">
            <label for="newPassword">新密碼：</label>
            <input type="password" id="newPassword" name="newPassword" placeholder="請輸入新密碼" required>
        </div>
        
        <div class="form-group">
            <label for="confirmPassword">確認新密碼：</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="請確認新密碼" required>
        </div>

        <div class="button-container">
            <button type="submit">提交</button>
        </div>
    </form>

    <div id="resetPasswordResult"></div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p id="modalMessage"></p>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('#resetPasswordForm').submit(function(event) {
                event.preventDefault();

                var formData = $(this).serialize();
                $.post('/C080605/resetPassword', formData, function(response) {
                    if (response.success) {
                        showModal('密碼重設成功，請重新登入！', function() {
                            window.location.href = 'login.jsp';
                        });
                    } else {
                        showModal('錯誤: ' + response.message);
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
