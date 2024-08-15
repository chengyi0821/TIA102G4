$('#system-anno').on('click', function() {
	window.location.href = 'memberNews.html';
});

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;  // 當前頁碼
	const dataList = $('#data-list>tr');
	const dataList1 = $('#data-list');
	let itemCount = 0; // 記錄已添加的項目數量

	// 初次加載公告數據
	loadAnnouncements(currentPage, contextPath);

	// 模態窗口相關
	const modal = $('#myModal');
	const modalImg = $('#modal-img');
	const modalDetails = $('#modal-details');
	const close = $('.close');
	const navbarCollapse = $('#navbarCollapse');

	// 關閉模態窗口
	close.on('click', function() {
		modal.hide();
		navbarCollapse.removeClass('hidden'); // 顯示導航欄
	});

	// 點擊模態窗口外部關閉
	$(window).on('click', function(event) {
		if ($(event.target).is(modal)) {
			modal.hide();
			navbarCollapse.removeClass('hidden'); // 顯示導航欄
		}
	});

	// 點擊圖片打開模態窗口
	$(document).on('click', '.thumbnail', function() {
		const src = $(this).data('imgSrc');
		const name = $(this).data('name')
		const heading = $(this).data('heading');
		const content = $(this).data('content');
		const type = $(this).data('type');
		const startDate = $(this).data('startDate');
		const endDate = $(this).data('endDate');

		// 隱藏導航欄
		navbarCollapse.addClass('hidden');

		// 更新模態窗口內容
		modalImg.attr('src', src);
		$('#modal-name').text(name);
		$('#modal-heading').text(heading);
		$('#modal-content').text(content);
		$('#modal-type').text(type);
		$('#modal-start-date').text(startDate);
		$('#modal-end-date').text(endDate);
		modalDetails.show(); // 顯示詳細資訊區域

		// 顯示模態窗口
		modal.show();
	});

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
                                data-heading="${announcement.heading}"
                                data-content="${announcement.content}"
                                data-type="| ${type}"
                                data-start-date="${announcement.startDate}"
                                data-end-date="${announcement.endDate}">`;
						if (type == "系統公告") {
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
