$(document).ready(function() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

    function loadRestaurantInfo() {
        $.ajax({
            url: `${contextPath}/rest/rest.do?action=findIdByUser`,
            type: 'POST',
            contentType: 'application/json',
            success: function(response) {
                if (response) {
                    $('#restName').text(response.restName);
                    $('#location').text(response.location);
                    $('#phone').text(response.phone);
                    $('#description').text(response.description);

                    // 處理 Base64 圖片數據
                    if (response.image) {
                        $('#imagePreview').attr('src', `data:image/png;base64,${response.image}`).show();
                    } else {
                        $('#imagePreview').hide();
                    }
                } else {
                    console.error('Response is null or undefined');
                }
            },
            error: function(xhr, status, error) {
                console.error('查詢餐廳信息時出錯:', error);
            }
        });
    }

    loadRestaurantInfo();
});
