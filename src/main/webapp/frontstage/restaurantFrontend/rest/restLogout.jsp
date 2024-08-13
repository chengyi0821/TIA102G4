<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 清除Session
    session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
    <title>登出</title>
    <meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/restLogin.jsp">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            text-align: center;
            background-color: #fff3e0;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 5px solid #fdc001;
            color: #4D3900;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>您已成功登出</h2>
        <p>感謝使用Chugether，您將在3秒後返回登入頁。</p>
    </div>
</body>
</html>
