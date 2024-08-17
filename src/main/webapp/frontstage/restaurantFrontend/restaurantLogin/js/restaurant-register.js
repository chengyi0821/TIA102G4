function goToLoginPage() {
	window.location.href = 'restaurantLogin.html';
}
$(document).ready(function() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    $("#restaurantForm").submit(function(event) {
        event.preventDefault(); // 防止表單的默認提交行為

        // 收集營業日的狀態，並組合成一個字串
        let openDay = '';
        $("input[name='openDay']").each(function() {
            openDay += $(this).is(':checked') ? '1' : '0';
        });

        // 讀取圖片文件並轉換為 Base64
        const fileInput = $("#imageInput")[0];
        let imageBase64 = "";
        if (fileInput.files.length > 0) {
            const file = fileInput.files[0];
            const reader = new FileReader();
            reader.onload = function(e) {
                imageBase64 = e.target.result.split(',')[1]; // 獲取 Base64 編碼的圖片數據（去掉前綴）

                // 當圖片文件讀取完成後，提交表單數據
                submitForm();
            };
            reader.readAsDataURL(file); // 將文件讀取為 Data URL (Base64 編碼)
        } else {
            // 如果沒有選擇圖片，直接提交表單數據
            submitForm();
        }

        function submitForm() {
            // 將其他表單數據與 openDay 一起打包成 JSON
            const formData = {
                restName: $("#restName").val(),
                location: $("#location").val(),
                phone: $("#phone").val(),
                email: $("#email").val(),
                password: $("#password").val(),
                openDay: openDay, // 加入營業日字串
                openTime1: $("#openTime1").val() + ":00", 
                closeTime1: $("#closeTime1").val() + ":00",
                openTime2: $("#openTime2").val() + ":00",
                closeTime2: $("#closeTime2").val() + ":00",
                restType: $("#restType").val(),
                description: $("#description").val(),
                image: imageBase64 // 加入 Base64 編碼的圖片
            };

            // 發送 AJAX 請求
            $.ajax({
                url: `${contextPath}/rest/rest.do?action=add`,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(formData), // 將表單數據轉換為 JSON 字符串
                success: function(response) {
                    alert("餐廳資料已成功提交！");
                    goToLoginPage();
                },
                error: function(error) {
                    alert("提交失敗，請重試！");
                }
            });
        }
    });
});