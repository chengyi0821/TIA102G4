$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	$('#food-safety-news').on('click', function() {
		$(".thumbnail").remove();
		$('#title').text('首頁 | 最新消息 | 食安新聞');
		let currentPage = 1;  // 當前頁碼
		const dataList = $('#data-list>tr');
		const dataList1 = $('#data-list');
		let itemCount = 0; // 記錄已添加的項目數量

		// 初次加載公告數據
		loadAnnouncements(currentPage, contextPath);
		function loadAnnouncements(page, contextPath) {
			$.ajax({
				url: `${contextPath}/anno/anno.do?action=getAllForMember`,
				type: 'GET',
				data: {
					page: page,
				},
				success: function(data) {
					dataList.empty();
					if (data && Array.isArray(data.List)) {
						const today = new Date(); // 當前日期
						data.List.forEach(announcement => {
								let type;
								switch (announcement.type) {
									case "SYSTEM": type = '系統公告'; break;
									case "FOOD_SAFETY": type = '食安新聞'; break;
									default: type = '';
								}
								let imageHtml =
									`<img class="thumbnail" src="data:image/jpeg;base64,${announcement.image}" alt="公告圖片"
	                                data-img-src="data:image/jpeg;base64,${announcement.image}"
	                                data-name=""
	                                data-heading="${announcement.heading}"
	                                data-content="${announcement.content}"
	                                data-type="| ${type}"
	                                data-start-date="${announcement.startDate}"
	                                data-end-date="${announcement.endDate}">`;
									if (type == "食安新聞") {
										if (itemCount % 4 === 0) {
											row = $('<tr></tr>'); // 創建新的一行
											dataList1.append(row);
										}

									row.append(`<td>${imageHtml}</td>`); // 將圖片加入當前行
									itemCount++;
								}
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
});