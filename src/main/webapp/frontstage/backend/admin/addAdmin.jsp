<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - 新增管理人員</title>
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
            max-width: 600px; /* 調整寬度為三分之二 */
            background-color: #fff3e0;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        h1 {
            text-align: center;
            color: #4D3900;
            margin-bottom: 20px;
            font-size: 24px; /* 調整標題大小 */
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

        input[type="text"], input[type="password"], select {
            flex: 2;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
            color: #4D3900;
        }

        input[type="password"] {
            background-color: #e0e0e0; /* 灰色背景 */
            color: #999;
        }

        .button-container {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

        button {
            padding: 10px 20px; /* 縮小按鈕 */
            background-color: #4D3900;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            min-width: 100px;
        }

        button:hover {
            background-color: #fdc001;
            color: #4D3900;
        }

        /* 彈窗樣式 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: #fdf3f3;
            margin: auto;
            padding: 20px;
            border: 5px solid #fdc001;
            width: 80%;
            max-width: 400px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            color: #4D3900;
            font-size: 18px;
            font-weight: bold;
        }

        .close {
            color: #fdc001;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

    </style>
   
</head>
<body>
    <div class="container">
        <h1>Chugether* 新增後台人員</h1><br/>
        <form action="${pageContext.request.contextPath}/adminList?action=add" method="post" onsubmit="return validateForm();">
            <div class="form-group">
                <label for="name">後台名稱：</label>
                <input type="text" id="name" name="name" placeholder="請輸入名稱" required>
            </div>
            <div class="form-group">
                <label for="account">後台帳號：</label>
                <input type="text" id="account" name="account" placeholder="請輸入帳號" required>
            </div>
            <div class="form-group">
                <label for="password">後台密碼：</label>
                <input type="password" id="password" name="password"  placeholder="預設為Chugether" readonly>
            </div>
            <div class="form-group">
                <label for="permission">後台權限：</label>
                <select id="permission" name="permission">
                    <option value="1">管理員</option>
                    <option value="2">一般</option>
                </select>
            </div>
            <div class="button-container">
                <button type="submit">新增</button>
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/adminList'">返回</button>
            </div>
        </form>
    </div>
    
     <script>
        function validateForm() {
            var name = document.getElementById("name").value;
            var account = document.getElementById("account").value;

            // 名稱驗證
            if (name.length > 15) {
                showModal("名稱長度需在15字以內");
                return false;
            }

            // 帳號格式驗證
            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(account)) {
                showModal("請輸入有效的電子郵件地址");
                return false;
            }

            return true;
        }

        function showModal(message) {
            var modal = document.createElement("div");
            modal.classList.add("modal");

            var modalContent = document.createElement("div");
            modalContent.classList.add("modal-content");

            var closeButton = document.createElement("span");
            closeButton.classList.add("close");
            closeButton.innerHTML = "&times;";
            closeButton.onclick = function () {
                document.body.removeChild(modal);
            };

            var text = document.createElement("p");
            text.textContent = message;

            modalContent.appendChild(closeButton);
            modalContent.appendChild(text);
            modal.appendChild(modalContent);

            document.body.appendChild(modal);
            modal.style.display = "flex";
        }
    </script>
</body>
</html>
