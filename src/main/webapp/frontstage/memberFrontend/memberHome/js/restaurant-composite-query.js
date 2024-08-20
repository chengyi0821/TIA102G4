function goToMemberHome() {
	window.location.href = 'memberHome.html';
}

$(document).ready(function() {
	// 當使用者按下 Enter 鍵時觸發搜尋
	$('#sear-input').on('keypress', function(event) {
		if (event.which === 13) { // 檢查是否按下 Enter 鍵
			$('#sear-button').click(); // 觸發搜尋按鈕的點擊事件
		}
	});

	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	const dataList = $('#restaurant-info');
	//=======================================創建loadAnnouncements=======================================

	$('#sear-button').on('click', function() {
		const searchQuery = $('#sear-input').val().trim(); // 修剪空格
		if (searchQuery === '') {
			goToMemberHome();
			return;
		}
		currentPage = 1;  // 重置頁碼
		loadCompositeQuery(searchQuery, contextPath);
	});
	//=======================================複合查詢=======================================
	// 加载复合查询公告数据的函数
	function loadCompositeQuery(searchQuery, contextPath) {
		$('#restaurant-info').empty();  // 清空之前的內容

		$.ajax({
			url: `${contextPath}/rest/rest.do?action=compositeQuery`,
			type: 'POST',
			data: {
				searchQuery: searchQuery || ""
			},
			success: function(data) {
				if (data && Array.isArray(data.List)) {
					data.List.forEach(restaurant => {
						const openDayStr = getOpenDaysString(restaurant.openDay);
						const times = getFormattedTimes(restaurant);
						const byteArray = new Uint8Array(restaurant.image);

						// 創建 Blob 對象，並指定 MIME 類型
						const blob = new Blob([byteArray], { type: 'image/jpeg' });
						const imageUrl = URL.createObjectURL(blob)
						dataList.append(`
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
                                        ${times.openTime1} - ${times.closeTime1} , 
                                        ${times.openTime2} - ${times.closeTime2} 
                                    </p>
                                </div>
                            </div>
                    `);
					});
				} else {
					console.error('數據格式錯誤：沒有 List 數組');
				}
			},
			error: function(error) {
				console.error('取得公告時出錯:', error);
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