function goToRestPage() {
	window.location.href = 'restaurant.html';
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;  // 當前頁碼
	const dataList = $('#data-list');

	//=======================================創建loadAnnouncements=======================================
	// 初次加載公告數據
	loadAnnouncements(currentPage, contextPath);

	// 移除按钮事件
	$('#remove-button').on('click', function() {
		alert('移除公告');
	});

	// 分頁按鈕事件
	//上一頁
	$('#prev-page').on('click', function() {
		if (currentPage > 1) {
			currentPage--;
			loadAnnouncements(currentPage, contextPath);
		} else {
			$('#prev-page').prop('disabled', false);
		}
	});
	//下一頁
	$('#next-page').on('click', function() {
		currentPage++;
		loadAnnouncements(currentPage, contextPath);
	});
	//最後一頁
	$('#last-page').on('click', function() {
		$.ajax({
			url: `${contextPath}/rest/rest.do?action=getAll`,
			type: 'GET',
			success: function(data) {
				currentPage = data.totalPageQty;
				loadAnnouncements(currentPage, contextPath);
			},
			error: function(error) {
				console.error('取得公告時出錯:', error);
			}
		});
	});
	//=======================================查詢全部=======================================
	// 加载公告數據
	function loadAnnouncements(page, contextPath) {
		$.ajax({
			url: `${contextPath}/rest/rest.do?action=getAll`,
			type: 'GET',
			data: {
				page: page,
			},
			success: function(data) {
				dataList.empty();
				if (data && Array.isArray(data.List)) {
					data.List.forEach(restaurant => {

						dataList.append(`
						<tr>
							<td>${restaurant.restId}</td>
							<td>${restaurant.restName}</td>
							<td>${restaurant.email}</td>
							<td>${restaurant.phone}</td>
							<td>${restaurant.registTime}</td>
							<td>
								<button value="${restaurant.restId}" type="button" id="update-button"><img id="edit" src="image/edit.png" alt="圖示"></button>
								<button value="${restaurant.restId}" type="button" id="remove-button"><img id="delete" src="image/delete.png" alt="圖示"></button>
							</td>
						</tr>
										`);
					});
					$('#current-page').text(data.currentPage);  // 更新當前頁碼顯示
					if (data.currentPage == data.totalPageQty) {//如果頁數跟最後一頁相等關閉下一頁按鈕
						$('#next-page').prop('disabled', true);
					} else {
						$('#next-page').prop('disabled', false);
					}
					if (data.currentPage == 1) {//頁數跟1相等關閉上一頁按鈕
						$('#prev-page').prop('disabled', true);
					} else {
						$('#prev-page').prop('disabled', false);
					}
				} else {
					console.error('數據格式錯誤：沒有 List 數組');
				}
			},
			error: function(error) {
				console.error('取得公告時出錯:', error);
			}
		});
	}
});