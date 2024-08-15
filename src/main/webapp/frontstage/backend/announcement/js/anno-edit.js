function goToAnnoPage() {
	window.location.href = 'announcement.html';
}

function showError(inputId, errorMessage) {
	$(`#${inputId}`).val(errorMessage);
	$(`#${inputId}`).css("color", "red");
	$(`#${inputId}`).on('click', function() {
		$(`#${inputId}`).css("color", "black");
		$(`#${inputId}`).val("");
	});
}

function errorValidation() {
	//=======================================錯誤驗證=======================================
	let hasError = false;
	// 確保日期格式正確
	if ($('#end-date').val() < $('#start-date').val()) {
		// 如果結束日期小於起始日期，顯示錯誤消息
		alert('結束日期不能小於起始日期！');
		// 可以選擇清除結束日期的值或禁用提交按鈕等
		hasError = true;
	}
	//日期驗證
	if ($('#start-date').val() == "" || $('#end-date').val() == "") {
		alert("請選擇日期");
		hasError = true;
	}
	//公告主旨驗證
	if ($('#heading').val().length > 50 || $('#heading').val() == "公告主旨不得超過50字") {
		showError('heading', "公告主旨不得超過50字");
		hasError = true;
	}
	if ($('#heading').val() == "" || $('#heading').val() == "請填寫公告主旨") {
		showError('heading', "請填寫公告主旨");
		hasError = true;
	}
	//公告內容驗證
	if ($('#content').val().length > 500 || $('#content').val() == "公告內容不得超過500字") {
		showError('content', "公告內容不得超過500字");
		hasError = true;
	}
	if ($('#content').val() == "" || $('#content').val() == "請填寫公告內容") {
		showError('content', "請填寫公告內容");
		hasError = true;
	}
	// 如果有錯誤，阻止表單提交
	return hasError;
	//=====================================================================================
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;  // 當前頁碼

	//=======================================更新一筆資料=======================================
	var imageNotChange;
	$('#data-table').on('click', '#update-button', function() {
		var annoId = $(this).val();

		$.ajax({
			url: `${contextPath}/anno/anno.do?action=compositeQuery`,
			type: 'GET',
			success: function(response) {
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
						                        <button type="button" id="submit-announcement" href="announcement.html">確定</button>
						                        <button type="button" id="cancel" class="label" onclick="goToAnnoPage()">取消</button>
				                        	</div>
				                        </div>
		                            </div>
                        	</form>
                        </div>
                    `);
						// 獲取當前日期
						var today = new Date();
						// 計算下一天的日期
						var tomorrow = new Date(today);
						tomorrow.setDate(today.getDate() + 1);
						// 格式化日期為 YYYY-MM-DD
						var minDate = tomorrow.toISOString().split('T')[0];

						// 設置 input 元素的 min 屬性
						$('#start-date').attr('min', minDate);
						$('#end-date').attr('min', minDate);

						// 設定自定義的文件選擇按鈕
						$('#custom-file-upload').click(function() {
							$('#image').click();
						});
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
							const maxFileSize = 2 * 1024 * 1024 * 1024; // 2GB
							const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];

							if (file) {
								// 文件大小驗證
								if (file.size > maxFileSize) {
									alert('圖片上傳失敗! 圖片不得超過2GB');
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
							if (errorValidation()) {
								return;
							}
							// 將 imageFile 轉換為 Base64 編碼
							if (imageFile) {
								var reader = new FileReader();
								reader.onload = function(event) {
									// 獲取 Base64 編碼的圖像數據
									var base64Image = event.target.result.split(',')[1];
									// 獲取用戶輸入的日期
									var startDateInput = $('#start-date').val();
									var endDateInput = $('#end-date').val();

									// 發送 AJAX 請求
									$.ajax({
										url: `${contextPath}/anno/anno.do?action=update`,
										type: 'POST',
										contentType: 'application/json',
										data: JSON.stringify({
											id: annoId,
											startDate: startDateInput,
											endDate: endDateInput,
											heading: $('#heading').val(),
											content: $('#content').val(),
											type: $('#type').val(),
											image: base64Image,  // 傳遞 Base64 編碼的圖像數據
											deleted: false
										}),
										success: function(response) {
											alert("編輯成功!")
											window.location.href = 'announcement.html';
										},
										error: function(xhr, error) {
											if (xhr.status === 400) { // 驗證錯誤會返回 400 狀態碼
												alert("請填寫正確的資料");
											}
										}
									});
								};
								reader.readAsDataURL(imageFile);  // 將文件讀取為 Data URL
							} else {
								if (errorValidation()) {
									return;
								}
								// 獲取用戶輸入的日期
								var startDateInput = $('#start-date').val();
								var endDateInput = $('#end-date').val();

								// 如果沒有選擇圖片文件，直接發送 AJAX 請求
								$.ajax({
									url: `${contextPath}/anno/anno.do?action=update`,
									type: 'POST',
									contentType: 'application/json',
									data: JSON.stringify({
										id: annoId,
										startDate: startDateInput,
										endDate: endDateInput,
										heading: $('#heading').val(),
										content: $('#content').val(),
										type: $('#type').val(),
										image: imageNotChange,
										deleted: false
									}),
									success: function(response) {
										alert("編輯成功!")
										window.location.href = 'announcement.html';
										console.log('Update successful:', response);
									},
									error: function(xhr, error) {
										if (xhr.status === 400) { // 驗證錯誤會返回 400 狀態碼
											alert("請填寫正確的資料");
										}
									}
								});
							}
						});
					}
				}
			}
		});
	});
	//	function DateToISO(dateString) {
	//		var date = new Date(dateString);
	//		if (isNaN(date.valueOf())) {
	//			$('#date-error').text("請選擇日期");
	//			return;
	//		}
	//		return date.toISOString();
	//	}

});