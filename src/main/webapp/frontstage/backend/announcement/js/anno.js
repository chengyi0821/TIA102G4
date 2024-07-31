function removeDataUrlHeader(dataUrl) {
	if (dataUrl.startsWith("data:")) {
		const commaIndex = dataUrl.indexOf(",");
		if (commaIndex !== -1) {
			return dataUrl.substring(commaIndex + 1);
		}
	}
	return dataUrl;
};

function goToAnnoPage() {
	window.location.href = 'anno.html';
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;  // 當前頁碼
	const dataList = $('#data-list');

	//=======================================新增=======================================

	$('#add-button').click(function() {
		// 刪除指定的元素
		$('#filter-form').remove();
		$('#data-table').remove();
		$('#data-list').remove();
		$('#pagination').remove();
		$('#announcement-title').remove();

		// 顯示新增公告的表單
		$('#createAndEdit').append(`
        <div id="annoCreateEdit">
            <form id="announcement-form" enctype="multipart/form-data">
                <div id="annoCreate-title">新增公告</div>
                <div id="announcement-form">
                    <div id="form-content">
                        <label for="start-date" class="label">起始日期:</label>
                        <input type="date" id="start-date" name="start-date"><br>
                        <label for="end-date" class="label">結束日期:</label>
                        <input type="date" id="end-date" name="end-date"><br>
                        <label for="type" class="label">類型:</label>
                        <select id="type" name="type">
                            <option value="SYSTEM" class="label">系統公告</option>
                            <option value="FOOD_SAFETY" class="label">食安新聞</option>
                        </select><br>
                        <label for="heading" class="label">公告主旨:</label>
                        <input type="text" id="heading" name="heading"><br>
                        <label for="content" class="labelContent">公告內容:</label>
                        <textarea id="content" name="content"></textarea><br>
                    </div>
                    <div id="anno-image">
                        <label for="image" class="image">圖片:</label>
                        <div id="image-container">
                            <img id="current-image" alt="" style="max-width: 200px; max-height: 246px;">
                            <input type="file" id="image" name="image" style="display: none;">
                            <button type="button" id="custom-file-upload" style="position: absolute; bottom: 10px; right: 10px; z-index: 1;">選擇檔案</button>
                        </div>
                    </div>
                </div>
                <div id="buttons-container">
                    <button type="button" id="submit-announcement" class="label">確定</button>
                    <button type="button" id="cancel" class="label" onclick="goToAnnoPage()">取消</button>
                </div>
            </form>
        </div>
    `);

		// 設定自定義的文件選擇按鈕
		$('#custom-file-upload').click(function() {
			$('#image').click();
		});

		// 顯示上傳的圖片
		$('#image').change(function(event) {
			const file = event.target.files[0];
			if (file) {
				const reader = new FileReader();
				reader.onload = function(e) {
					$('#current-image').attr('src', e.target.result);
				}
				reader.readAsDataURL(file);
			}
		});

		// 提交表單
		$('#submit-announcement').click(function() {
			const fileInput = document.getElementById('image');
			const file = fileInput.files[0];
			let reader = new FileReader();

			reader.onload = function(e) {
				const base64String = removeDataUrlHeader(e.target.result);
				const formData = new FormData();
				formData.append('heading', $('#heading').val());
				formData.append('content', $('#content').val());
				formData.append('startDate', $('#start-date').val());
				formData.append('endDate', $('#end-date').val());
				formData.append('type', $('#type').val());
				formData.append('image', base64String);
				formData.append('deleted', false);

				const jsonData = {};

				Array.from(formData.entries()).forEach(([key, value]) => {
					jsonData[key] = value;
				});

				const jsonString = JSON.stringify(jsonData);

				const url = `${contextPath}/anno/anno.do?action=add`;
				$.ajax({
					url: url,
					type: 'POST',
					contentType: 'application/json',
					data: jsonString,
					success: function() {
						alert('新增公告成功');
					},
					error: function(error) {
						console.error('新增公告時出錯:', error);
					}
				});
			};

			// 檢查圖片是否有上傳
			if (!file) {
				alert("请選擇要上傳的圖片");
				return;
			}
			// 檢查日期是否有填寫
			else if ($('#start-date').val() == "" || $('#end-date').val() == "") {
				alert("請選擇日期");
				return;
			}
			else {
				alert("新增成功!");
				window.location.href = 'anno.html';
			}
			// 讀取圖片檔
			reader.readAsDataURL(file);
		});
	});
	//=======================================更新一筆資料=======================================
	var imageNotChange;
	$('#data-table').on('click', '#update-button', function() {
		var annoId = $(this).val();
		var page = currentPage;

		$.ajax({
			url: `${contextPath}/anno/anno.do?action=compositeQuery`,
			type: 'GET',
			success: function(response) {
				console.log(page);
				console.log('Response:', response);
				if (Array.isArray(response.List)) {
					var announcement = response.List.find(a => a.annoId === Number(annoId));
					console.log(announcement);
					if (announcement) {
						var heading = announcement.heading;
						var type = announcement.type;
						var content = announcement.content;
						var startDate = announcement.startDate;
						var endDate = announcement.endDate;
						var imageBase64 = announcement.image;
						
						// 拼接 Base64 數據
						imageNotChange = imageBase64;
						$('#filter-form').remove();
						$('#data-table').remove();
						$('#data-list').remove();
						$('#pagination').remove();
						$('#announcement-title').remove();

						// 顯示編輯公告的表單
						$('#createAndEdit').append(`
						<div id="annoCreateEdit">
	                        <form id="announcement-form" enctype="multipart/form-data">
	                        	<div id="annoCreate-title">編輯公告</div>
		                            <div id="announcement-form">
			                            <div id="form-content">
			                                <label for="start-date" class="label">起始日期:</label>
			                                <input type="date" id="start-date" name="start-date" value="${startDate}"><br>
			                                <label for="end-date" class="label">結束日期:</label>
			                                <input type="date" id="end-date" name="end-date" value="${endDate}"><br>
			                                <label for="type" class="label">類型:</label>
			                                <select id="type" name="type">
			                                    <option value="SYSTEM" ${type === 1 ? 'selected' : ''}>系統公告</option>
			                                    <option value="FOOD_SAFETY" ${type === 2 ? 'selected' : ''}>食安新聞</option>
			                                </select><br>
			                                <label for="heading" class="label">公告主旨:</label>
			                                <input type="text" id="heading" name="heading" value="${heading}"><br>
			                                <label for="content" class="labelContent">公告內容:</label>
			                                <textarea id="content" name="content">${content}</textarea><br>
			                            </div>
			                                <div id="anno-image">
						                        <label for="image" class="image">圖片:</label>
						                        <div id="image-container">
						                            <img id="current-image" alt="" style="max-width: 200px; max-height: 246px;">
						                            <input type="file" id="image" name="image" style="display: none;">
						                            <button type="button" id="custom-file-upload" style="position: absolute; bottom: 10px; right: 10px; z-index: 1;">編輯</button>
						                    	</div>
				                    		</div>
					                        <div id="buttons-Edit">
						                        <button type="button" id="submit-announcement" href="anno.html">確定</button>
						                        <button type="button" id="cancel" class="label" onclick="goToAnnoPage()">取消</button>
				                        	</div>
				                        </div>
		                            </div>
                        	</form>
                        </div>
                    `);
						//將圖片二進位轉為正常字元之後交給id為current-image                    
						$('#current-image').attr('src', 'data:image/jpeg;base64,' + imageBase64);

						// 處理編輯圖片按鈕的點擊事件
						$('#custom-file-upload').on('click', function() {
							$('#image').click(); // 觸發文件選擇器
						});
						var imageFile = "";
						// 處理文件選擇後的事件
						$('#image').on('change', function() {
							const file = this.files[0];
							const maxFileSize = 4 * 1024 * 1024 * 1024; // 4GB
							const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];

							if (file) {
								// 文件大小驗證
								if (file.size > maxFileSize) {
									alert('圖片上傳失敗! 圖片不得超過4GB');
									$(this).val(''); // 清空文件選擇框
									$('#current-image').attr('src', ''); // 清空當前圖片顯示
									imageFile = null; // 清空 imageFile 
									return;
								}

								// 文件类型验证
								if (!allowedTypes.includes(file.type)) {
									alert('Please select a valid image file (JPEG, PNG, GIF).');
									$(this).val(''); // 清空文件選擇框
									$('#current-image').attr('src', ''); // 清空當前圖片顯示
									imageFile = null; // 清空 imageFile 
									return;
								}

								const reader = new FileReader();
								reader.onload = function(e) {
									$('#current-image').attr('src', e.target.result);
								};
								reader.readAsDataURL(file);
								imageFile = file;
							} else {
								$('#current-image').attr('src', '');
								imageFile = null;
							}
						});
						// 提交表單的處理邏輯
						$('#submit-announcement').on('click', function() {
							// 將 imageFile 轉換為 Base64 編碼
							if (imageFile) {
								var reader = new FileReader();
								reader.onload = function(event) {
									// 獲取 Base64 編碼的圖像數據
									var base64Image = event.target.result.split(',')[1];

									// 獲取用戶輸入的日期
									var startDateInput = $('#start-date').val();
									var endDateInput = $('#end-date').val();
									// 檢查並轉換日期格式為 ISO 8601 格式
									const startDateFormatted = DateToISO(startDateInput);
									const endDateFormatted = DateToISO(endDateInput);
									// 發送 AJAX 請求
									$.ajax({
										url: `${contextPath}/anno/anno.do?action=update`,
										type: 'POST',
										contentType: 'application/json',
										data: JSON.stringify({
											id: annoId,
											startDate: startDateFormatted,
											endDate: endDateFormatted,
											heading: $('#heading').val(),
											content: $('#content').val(),
											type: $('#type').val(),
											image: base64Image,  // 傳遞 Base64 編碼的圖像數據
											deleted: false
										}),
										error: function(error) {
											console.error('Update failed:', error);
										}
									});
								};
								reader.readAsDataURL(imageFile);  // 將文件讀取為 Data URL
							} else {
								// 獲取用戶輸入的日期
								var startDateInput = $('#start-date').val();
								var endDateInput = $('#end-date').val();
								// 檢查並轉換日期格式為 ISO 8601 格式
								const startDateFormatted = DateToISO(startDateInput);
								const endDateFormatted = DateToISO(endDateInput);
								console.log(startDateFormatted);
								console.log(imageNotChange);
								// 如果沒有選擇圖片文件，直接發送 AJAX 請求

								$.ajax({
									url: `${contextPath}/anno/anno.do?action=update`,
									type: 'POST',
									contentType: 'application/json',
									data: JSON.stringify({
										id: annoId,
										startDate: startDateFormatted,
										endDate: endDateFormatted,
										heading: $('#heading').val(),
										content: $('#content').val(),
										type: $('#type').val(),
										image: imageNotChange,
										deleted: false
									}),
									success: function(response) {
										console.log('Update successful:', response);
									},
									error: function(error) {
										console.error('Update failed:', error);
									}
								});
							}
							alert("編輯成功!")
							window.location.href = 'anno.html';
						});
					}
				}
			}
		});
	});
	function DateToISO(dateString) {
		var date = new Date(dateString);
		return date.toISOString();
	}
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
				console.log(data);
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

	//=======================================創建loadAnnouncements=======================================
	// 初次加載公告數據
	loadAnnouncements(currentPage, contextPath);

	// 表单提交事件
	$('#filter-form').on('submit', function(event) {
		event.preventDefault();
		const searchQuery = $('#search-input').val();
		const startDate = $('input#start-Date').val();
		const endDate = $('input#end-Date').val();
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
			url: `${contextPath}/anno/anno.do?action=getAll`,
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
	// 加载公告数据的函数
	function loadAnnouncements(page, contextPath) {
		$.ajax({
			url: `${contextPath}/anno/anno.do?action=getAll`,
			type: 'GET',
			data: {
				page: page,
			},
			success: function(data) {
				dataList.empty();
				if (data && Array.isArray(data.List)) {
					data.List.forEach(announcement => {
						let type;
						switch (announcement.type) {
							case "SYSTEM": type = '系統公告'; break;
							case "FOOD_SAFETY": type = '食安新聞'; break;
							default: type = '';
						}
						//如果content超過31個字用三元運算的方式讓它31後面只顯示...
						let maxContentLength = 20;
						let contentText = announcement.content.length > maxContentLength
							? announcement.content.substring(0, maxContentLength) + "..."
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

	//=======================================刪除一筆=======================================
	$('#data-table').on('click', '#remove-button', function() {
		var idValue = $(this).val();
		if (confirm("確定要移除嗎?")) {
			$.ajax({
				url: `${contextPath}/anno/anno.do?action=delete`,
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
					id: idValue,        // 將 id 轉換為字串
					deleted: true       // 傳送 deleted 屬性
				}),
				success: function() {
					alert("移除成功");
				},
				error: function(error) {
					console.error('移除時出錯:', error);
				}
			});
		} else {
			return;
		}
	});
});
