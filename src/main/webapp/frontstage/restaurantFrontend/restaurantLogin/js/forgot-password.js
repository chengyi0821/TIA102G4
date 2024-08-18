$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    $('#forgotpassword').on('click', function(e) {
        e.preventDefault(); 
        $('#main-content2').empty(); // 清空 <div id="main-content2"> 的內容

        // 在 <div id="main-content2"> 中插入新的電子郵件輸入框和提交按鈕
        $('#main-content2').append(`
            <div class="email_form">
                <label for="email">請輸入電子郵件地址:</label>
                <input id="email" name="email" type="email" required>
                <button id="send-reset-link" type="button">發送</button>
            </div>
        `);

        // 綁定點擊事件處理程序到新的提交按鈕
        $('#send-reset-link').on('click', function() {
            var email = $('#email').val();
            
            if (email) {
                $.ajax({
                    url: `${contextPath}/rest/rest.do?action=resetPassword`, 
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        email: email
                    }),
                    success: function(response) {
                        alert('重置密碼郵件已發送。');
                        $('#main-content2').empty(); // 成功後可以選擇刪除新表單
                    },
                    error: function(xhr) {
                        alert(xhr.responseText);
                    }
                });
            } else {
                alert('請輸入電子郵件地址。');
            }
        });
    });
});
