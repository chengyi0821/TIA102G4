<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chugether - Admin Main</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            color: #4D3900;
            font-weight: bold;
        }

        header {
            background-color: #4D3900;
            color: #fff;
            padding: 20px;
            text-align: center;
            font-weight: bold;
        }

        h1 {
            margin: 0;
            font-size: 36px;
        }

        .container {
            flex: 1;
            padding: 40px;
            margin: 0 auto;
            max-width: 900px;
            background-color: #fff3e0;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
        }

        .profile-info {
            margin-bottom: 30px;
        }

        .profile-info h2 {
            margin-top: 0;
        }

        .profile-info p {
            margin: 10px 0;
        }

        .buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        button {
            background-color: #4D3900;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-weight: bold;
        }

        button:hover {
            background-color: #fdc001;
            color: #4D3900;
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
            background-color: rgb(0,0,0); 
            background-color: rgba(0,0,0,0.4); 
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto; 
            padding: 20px;
            border: 1px solid #888;
            width: 80%; 
            max-width: 500px;
            border-radius: 10px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <header>
        <h1>Chugether - Admin Main</h1>
    </header>

    <div class="container">
        <div class="profile-info">
            <h2>個人資料</h2>
            <p>ID: ${admin.adminId}</p>
            <p>名稱: ${admin.name}</p>
            <p>帳號: ${admin.account}</p>
            <p>權限: ${admin.permission == 1 ? "管理員" : "一般"}</p>
        </div>

        <div class="buttons">
            <button id="editBtn">編輯個人資料</button>
            <button onclick="window.location.href='adminDashboard.jsp'">查看帳號列表</button>
            <button onclick="window.location.href='logoutServlet'">登出</button>
        </div>
    </div>

    <!-- Edit Modal -->
    <div id="editModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>編輯個人資料</h2>
            <form id="editForm">
                <label>ID: </label><input type="text" value="${admin.adminId}" readonly><br><br>
                <label>帳號: </label><input type="text" value="${admin.account}" readonly><br><br>
                <label>名稱: </label><input type="text" name="name" value="${admin.name}"><br><br>
                <label>權限: </label>
                <select name="permission">
                    <option value="1" ${admin.permission == 1 ? 'selected' : ''}>管理員</option>
                    <option value="2" ${admin.permission == 2 ? 'selected' : ''}>一般</option>
                </select><br><br>
                <button type="submit">保存修改</button>
            </form>
        </div>
    </div>

    <script>
        // 打开编辑弹窗
        var modal = document.getElementById("editModal");
        var btn = document.getElementById("editBtn");
        var span = document.getElementsByClassName("close")[0];

        btn.onclick = function() {
            modal.style.display = "block";
        }

        span.onclick = function() {
            modal.style.display = "none";
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        // 保存修改（提交表单）
        document.getElementById("editForm").addEventListener("submit", function(event) {
            event.preventDefault();

            var formData = $(this).serialize();

            $.ajax({
                url: "updateAdminServlet",
                type: "POST",
                data: formData,
                success: function(response) {
                    alert("資料更新成功！");
                    modal.style.display = "none";
                    location.reload();  // 重新加载页面以显示更新后的信息
                },
                error: function() {
                    alert("發生錯誤，請稍後再試。");
                }
            });
        });
    </script>
</body>
</html>
</html>