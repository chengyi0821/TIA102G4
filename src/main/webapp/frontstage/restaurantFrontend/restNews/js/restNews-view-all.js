$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;  // 當前頁碼
	const dataList = $('#data-list');

	//=======================================創建loadAnnouncements=======================================
	
	// 分頁按鈕事件
	//上一頁
	$('#prev-page').on('click', function() {
		if (currentPage > 1) {
			currentPage--;
			loadCustomerServices(currentPage, contextPath);
		} else {
			$('#prev-page').prop('disabled', false);
		}
	});
	//下一頁
	$('#next-page').on('click', function() {
		currentPage++;
		loadCustomerServices(currentPage, contextPath);
	});
	//最後一頁
	$('#last-page').on('click', function() {
		$.ajax({
			url: `${contextPath}/restNews/restNews.do?action=getAll`,
			type: 'GET',
			success: function(data) {
				currentPage = data.totalPageQty;
				loadCustomerServices(currentPage, contextPath);
			},
			error: function(error) {
				console.error('取得公告時出錯:', error);
			}
		});
	});
	//=======================================查詢全部=======================================
	function loadCustomerServices(page, contextPath) {
		$.ajax({
			url: `${contextPath}/restNews/restNews.do?action=getAll`,
			type: 'GET',
			data: {
				page: page,
			},
			success: function(data) {
				dataList.empty();
				if (data && Array.isArray(data.List)) {
					data.List.forEach(news => {
						
						// 設定內容文字的最大長度
						let maxContentLength = 20;
						let contentText = news.content.length > maxContentLength
							? news.content.substring(0, maxContentLength) + "..."
							: news.content;
						// 設定主旨文字的最大長度
						let maxheadingLength = 5;
						let headingText = news.heading.length > maxheadingLength
							? news.heading.substring(0, maxheadingLength) + "..."
							: news.heading;
							
						// 設定類型 1.帳號問題 2.食安問題 3.訂單問題 4.系統問題 5.其他
						let type;
						switch(news.type){
							case "ANNOUNCEMENT" :
								type = "公告"
								break;
							case "ADVERTISEMENT" :
								type = "廣告"
								break;
						}

						dataList.append(`
                        <tr>
                            <td>${news.newsId}</td>
                            <td>${headingText}</td>
                            <td>${type}</td>
                            <td>${contentText}</td>
                            <td>${news.startDate}</td>
                            <td>${news.endDate}</td>
                            <td>
								<button value="${news.newsId}" type="button" id="update-button"><img id="edit" src="image/edit.png" alt="圖示"></button>
								<button value="${news.newsId}" type="button" id="remove-button"><img id="delete" src="image/delete.png" alt="圖示"></button>
							</td>
                        </tr>
                    `);
					});
					$('#current-page').text(data.currentPage);  // 更新當前頁碼顯示
					if (data.currentPage == data.totalPageQty) {
						$('#next-page').prop('disabled', true);
					} else {
						$('#next-page').prop('disabled', false);
					}
					if (data.currentPage == 1) {
						$('#prev-page').prop('disabled', true);
					} else {
						$('#prev-page').prop('disabled', false);
					}
				} else {
					console.error('數據格式錯誤：沒有 csList 數組');
				}
			},
			error: function(error) {
				console.error('取得客服反饋時出錯:', error);
			}
		});
	}
	let page = 1;  // 默認設置為第1頁
	loadCustomerServices(page, contextPath); //加載會員信件數據
});
