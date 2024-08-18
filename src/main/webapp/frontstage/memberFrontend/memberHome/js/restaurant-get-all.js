$(document).ready(function() {
    const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    let currentPage = 1;  // 當前頁碼
    const dataList = $('#data-list');

    // 初次加載餐廳數據
    loadRestaurants(currentPage, contextPath);

    $('#remove-button').on('click', function() {
        alert('移除公告');
    });

    // 分頁按鈕事件
    $('#prev-page').on('click', function() {
        if (currentPage > 1) {
            currentPage--;
            loadRestaurants(currentPage, contextPath);
        }
    });

    $('#next-page').on('click', function() {
        currentPage++;
        loadRestaurants(currentPage, contextPath);
    });

    $('#last-page').on('click', function() {
        $.ajax({
            url: `${contextPath}/rest/rest.do?action=getAll`,
            type: 'GET',
            success: function(data) {
                currentPage = data.totalPageQty;
                loadRestaurants(currentPage, contextPath);
            },
            error: function(error) {
                console.error('取得餐廳數據時出錯:', error);
            }
        });
    });

    function loadRestaurants(page, contextPath) {
        $.ajax({
            url: `${contextPath}/rest/rest.do?action=getAll`,
            type: 'GET',
            data: {
                page: page
            },
            success: function(response) {
                $('#restaurant-info').empty();

                if (response && Array.isArray(response.List)) {
                    for (const restaurant of response.List) {
                        const openDayStr = getOpenDaysString(restaurant.openDay);
                        const times = getFormattedTimes(restaurant);
                        const byteArray = new Uint8Array(restaurant.image);

                        // 創建 Blob 對象，並指定 MIME 類型
                        const blob = new Blob([byteArray], { type: 'image/jpeg' });

                        // 生成一個 Object URL
                        const imageUrl = URL.createObjectURL(blob);
                        $('#restaurant-info').append(`
                            <div class="restaurant-card">
                                <img src="${imageUrl}" alt="${restaurant.restName}" class="restaurant-image" />
                                <div class="restaurant-details">
                                    <h3>${restaurant.restName}</h3>
                                    <p><strong>類型:</strong> ${restaurant.restType.typeName}</p>
                                    <p><strong>描述:</strong> ${restaurant.description}</p>
                                    <p><strong>位置:</strong> ${restaurant.location}</p>
                                    <p><strong>郵件:</strong> ${restaurant.email}</p>
                                    <p><strong>電話:</strong> ${restaurant.phone}</p>
                                    <p><strong>營業日:</strong> ${openDayStr}</p>
                                    <p><strong>營業時間:</strong>
                                        ${times.openTime1} - ${times.closeTime1} (上午), 
                                        ${times.openTime2} - ${times.closeTime2} (下午)
                                    </p>
                                </div>
                            </div>
                        `);
                    }
                    // 顯示當前頁數和總頁數
                    $('#current-page').text(currentPage);  // 更新當前頁碼顯示
					if (restaurant.currentPage) {//如果頁數跟最後一頁相等關閉下一頁按鈕
						$('#next-page').prop('disabled', true);
					} else {
						$('#next-page').prop('disabled', false);
					}
					if (restaurant.currentPage == 1) {//頁數跟1相等關閉上一頁按鈕
						$('#prev-page').prop('disabled', true);
					} else {
						$('#prev-page').prop('disabled', false);
					}
                } else {
                    console.error('數據格式錯誤：沒有 List 數組');
                }
            },
            error: function(error) {
                console.error('取得餐廳數據時出錯:', error);
            }
        });
    }

    function getOpenDaysString(openDay) {
        if (!openDay) return '未設定';

        const days = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"];
        return days.map((day, index) => openDay[index] === '1' ? day : `${day} (公休)`).join(', ');
    }

    function getFormattedTimes(restaurant) {
        return {
            openTime1: restaurant.openTime1 || '未設定',
            closeTime1: restaurant.closeTime1 || '未設定',
            openTime2: restaurant.openTime2 || '未設定',
            closeTime2: restaurant.closeTime2 || '未設定'
        };
    }
});
