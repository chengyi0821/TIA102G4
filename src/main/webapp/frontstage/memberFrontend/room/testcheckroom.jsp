<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>訂單管理</title>
    <!-- 載入 jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link href="../css/style.css" rel="stylesheet">
    <link href="css/room.css" rel="stylesheet">
    <script src="js/main.js"></script>
    <style>
        .table-container {
            margin: 50px auto;
            width: 80%;
            max-width: 800px;
            border: 2px solid black;
            border-radius: 8px;
            padding: 20px;
            background-color: #FFFFAA;
        }

        .table-container table {
            width: 100%;
            border-collapse: collapse;
        }

        .table-container th,
        .table-container td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .table-container th {
            background-color: #FFFFAA;
            font-size: 18px;
        }

        .table-container td {
            font-size: 16px;
        }

        .confirm-box {
            margin-top: 20px;
            padding: 15px;
            border: 1px solid red;
            background-color: #fff0f0;
            color: red;
            text-align: center;
            font-size: 16px;
        }

        .button-container {
            margin-top: 30px;
            text-align: center;
        }

        .button-container button {
            background-color: #C57F00;
            color: white;
            border: none;
            padding: 10px 20px;
            margin: 0 10px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 5px;
        }

        .button-container button:hover {
            background-color: #b36d00;
        }

        .container-fluid.position-relative.nav-bar.p-0 li {
            color: black !important;
        }
    </style>
</head>

<body>
    <!-- Topbar Start -->
    <div class="container-fluid bg-primary py-3 d-none d-md-block">
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
                    <div class="d-inline-flex align-items-center">
                        <a href="page1.html" class="navbar-brand mx-5 d-none d-lg-block">
                            <img class="logo" src="../image/logo.png">
                            <h1 class="m-0 display-4 text-primary">Chugether</h1>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar End -->

    <!-- Navbar Start -->
    <div class="container-fluid position-relative nav-bar p-0">
        <div class="container-lg position-relative p-0 px-lg-3" style="z-index: 9;">
            <nav class="navbar navbar-expand-lg bg-white navbar-light shadow p-lg-0">
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav ml-auto py-0">
                        <div class="orderblock">
                            <a href="#" class="nav-item nav-link">&nbsp&nbsp&nbsp首頁 </a>
                            <ul class="orderlist">
                                <li><a style="color: black;" href="#">會員登入</a></li>
                                <li><a style="color: black;" href="#">最新消息</a></li>
                            </ul>
                        </div>
                        <div class="orderblock">
                            <a href="#" class="nav-item nav-link">會員專區</a>
                            <ul class="orderlist">
                                <li><a style="color: black;" href="#">會員資料</a></li>
                                <li><a style="color: black;" href="#">查看收藏</a></li>
                            </ul>
                        </div>
                        <div class="orderblock">
                            <a href="#" class="nav-item nav-link">店家介紹</a>
                            <ul class="orderlist">
                                <li><a style="color: black;" href="#">查看店家</a></li>
                                <li><a style="color: black;" href="#">查看評價</a></li>
                                <li><a style="color: black;" href="#">營業資訊</a></li>
                                <li><a style="color: black;" href="#">類別搜尋</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="navbar-nav mr-auto py-0">
                        <div class="orderblock">
                            <a href="#" class="nav-item nav-link active">揪團系統</a>
                            <ul class="orderlist">
                                <li><a style="color: black;" href="#">發起揪團</a></li>
                                <li><a style="color: black;" href="#">參與揪團</a></li>
                            </ul>
                        </div>
                        <div class="orderblock">
                            <a href="#" class="nav-item nav-link">訂單管理</a>
                            <ul class="orderlist">
                                <li><a style="color: black;" href="#">編輯訂單</a></li>
                                <li><a style="color: black;" href="#">取消訂單</a></li>
                                <li><a style="color: black;" href="#">餐後評論</a></li>
                            </ul>
                        </div>
                        <div class="orderblock">
                            <a href="#" class="nav-item nav-link">聯絡客服</a>
                            <ul class="orderlist">
                                <li><a style="color: black;" href="#">客服信箱</a></li>
                                <li><a style="color: black;" href="#">Q&A</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Navbar End -->

    <!-- 主體內容 -->
    <div class="table-container">
        <table>
            <tr>
                <th>主鳩人</th>
                <td>王XX</td>
            </tr>
            <tr>
                <th>餐廳</th>
                <td>王品</td> <!-- 固定餐廳名稱 -->
            </tr>
            <tr>
                <th>日期</th>
                <td>2023/04/14</td> <!-- 固定日期 -->
            </tr>
            <tr>
                <th>時間</th>
                <td>11:30</td> <!-- 固定時間 -->
            </tr>
            <tr>
                <th>手機</th>
                <td>0900XXXXXX</td>
            </tr>
            <tr>
                <th>人數</th>
                <td>8</td> <!-- 固定人數 -->
            </tr>
        </table>

        <div class="confirm-box">
            內容:確定後進行位置確認,如不想參加可取消揪團(需要登入,刪除請到訂單管理)!!
        </div>

        <div class="button-container">
            <button onclick="confirmAction()">確定</button>
            <button onclick="cancelAction()">取消</button>
        </div>
    </div>

    <!-- Footer Start -->
    <footer id="footer">
        <h5 class="footerh5_1">Chugether 揪來鳩去有限公司</h5>
        <h5 class="footerh5">常見問題</h5>
        <h5 class="footerh5">網站地圖</h5>
        <h5 class="footerh5">使用者條款</h5>
        <h5 class="footerh5">隱私權條款</h5>
        <h5 class="footerh5_2">Copyright © 2024 Chugether</h5>
    </footer>
    <!-- Footer End -->
    <script src="js/main.js"></script>
</body>

</html>
