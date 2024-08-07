function goToRestNewsPage() {
	window.location.href = 'restNews.html';
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	const dataList = $('#data-list');
	//=======================================創建loadAnnouncements=======================================
	// 表单提交事件
	$('#filter-form').on('submit', function(event) {
		event.preventDefault();
		const searchQuery = $('#search-input').val();
		const startDate = $('input#start-Date').val();
		const endDate = $('input#end-Date').val();
		const type = $('select#type1').val();
		if (!searchQuery && !startDate && !endDate && !type) {
			return goToRestNewsPage();
		}
		currentPage = 1;  // 重置頁碼
		loadCompositeQuery(startDate, endDate, searchQuery, contextPath, type);
	});

	// 搜索框输入事件
	$('#Search').on('click', function() {
		const searchQuery = $('#search-input').val();
		const startDate = $('input#start-Date').val();
		const endDate = $('input#end-Date').val();
		const type = $('select#type1').val();
		currentPage = 1;  // 重置頁碼
		loadCompositeQuery(startDate, endDate, searchQuery, contextPath, type);
	});
	//=======================================複合查詢=======================================
	// 加载复合查询公告数据的函数
	function loadCompositeQuery(startDate, endDate, searchQuery, contextPath, type) {
		$('#data-list').empty();  // 清空之前的內容
		$('div#pagination').remove();

		$.ajax({
			url: `${contextPath}/restNews/restNews.do?action=compositeQuery`,
			type: 'POST',
			data: {
				startDate: startDate || "",
				endDate: endDate || "",
				searchQuery: searchQuery || "",
				type: type || "",
			},
			success: function(data) {
				if (data && Array.isArray(data.List)) {
					data.List.forEach(restNews => {
						let type;
						switch (restNews.type) {
							case "ANNOUNCEMENT": type = '公告'; break;
							case "ADVERTISEMENT": type = '廣告'; break;
							default: type = '';
						}
						let maxLength = 20;
						let contentText = restNews.content.length > maxLength
							? restNews.content.substring(0, maxLength) + "..."
							: restNews.content;

						//如果heading超過4個字用三元運算的方式讓它4後面只顯示...	
						let maxHeadingLength = 5;
						let headingText = restNews.heading.length > maxHeadingLength
							? restNews.heading.substring(0, maxHeadingLength) + "..."
							: restNews.heading;

						dataList.append(`
                        <tr>
                            <td>${restNews.newsId}</td>
                            <td>${headingText}</td>
                            <td>${type}</td>
                            <td>${contentText}</td>
                            <td>${restNews.startDate}</td>
                            <td>${restNews.endDate}</td>
                            <td>
								<button value="${restNews.newsId}" type="button" id="update-button"><img id="edit" src="image/edit.png" alt="圖示"></button>
								<button value="${restNews.newsId}" type="button" id="remove-button"><img id="delete" src="image/delete.png" alt="圖示"></button>
							</td>
                        </tr>
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
});