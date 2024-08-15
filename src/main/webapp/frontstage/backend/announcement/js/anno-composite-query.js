function goToAnnoPage() {
	window.location.href = 'announcement.html';
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
		if(!searchQuery && !startDate && !endDate){
			return goToAnnoPage();
		}
		currentPage = 1;  // 重置頁碼
		loadCompositeQuery(startDate, endDate, searchQuery, contextPath);
	});

	// 搜索框输入事件
	$('#Search').on('click', function() {
		const searchQuery = $('#search-input').val();
		const startDate = $('input#start-Date').val();
		const endDate = $('input#end-Date').val();
		currentPage = 1;  // 重置頁碼
		loadCompositeQuery(startDate, endDate, searchQuery, contextPath);
	});
	//=======================================複合查詢=======================================
	// 加载复合查询公告数据的函数
	function loadCompositeQuery(startDate, endDate, searchQuery, contextPath) {
		$('#data-list').empty();  // 清空之前的內容
		$('div#pagination').remove();

		$.ajax({
			url: `${contextPath}/anno/anno.do?action=compositeQuery`,
			type: 'POST',
			data: {
				startDate: startDate || "",
				endDate: endDate || "",
				searchQuery: searchQuery || ""
			},
			success: function(data) {
				if (data && Array.isArray(data.List)) {
					data.List.forEach(announcement => {
						let type;
						switch (announcement.type) {
							case "SYSTEM": type = '系統公告'; break;
							case "FOOD_SAFETY": type = '食安新聞'; break;
							default: type = '';
						}
						let maxLength = 20;
						let contentText = announcement.content.length > maxLength
							? announcement.content.substring(0, maxLength) + "..."
							: announcement.content;

						//如果heading超過4個字用三元運算的方式讓它4後面只顯示...	
						let maxHeadingLength = 7;
						let headingText = announcement.heading.length > maxHeadingLength
							? announcement.heading.substring(0, maxHeadingLength) + "..."
							: announcement.heading;

						dataList.append(`
                        <tr>
                            <td>${announcement.annoId}</td>
                            <td>${headingText}</td>
                            <td>${type}</td>
                            <td>${contentText}</td>
                            <td>${announcement.startDate}</td>
                            <td>${announcement.endDate}</td>
                            <td>
								<button value="${announcement.annoId}" type="button" id="update-button"><img id="edit" src="image/edit.png" alt="圖示"></button>
								<button value="${announcement.annoId}" type="button" id="remove-button"><img id="delete" src="image/delete.png" alt="圖示"></button>
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