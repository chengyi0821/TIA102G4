function removeDataUrlHeader(dataUrl) {
	if (dataUrl.startsWith("data:")) {
		const commaIndex = dataUrl.indexOf(",");
		if (commaIndex !== -1) {
			return dataUrl.substring(commaIndex + 1);
		}
	}
	return dataUrl;
};

function showError(inputId, errorMessage) {
	$(`#${inputId}`).val(errorMessage);
	$(`#${inputId}`).css("color", "red");
	$(`#${inputId}`).on('click', function() {
		$(`#${inputId}`).css("color", "black");
		$(`#${inputId}`).val("");
	});
}

function goToRestNewsPage() {
	window.location.href = 'restNews.html';
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

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
                            <option value="ANNOUNCEMENT" class="label">公告</option>
                            <option value="ADVERTISEMENT" class="label">廣告</option>
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
                    <button type="button" id="cancel" class="label" onclick="goToRestNewsPage()">取消</button>
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

			//=======================================錯誤驗證=======================================
			let hasError = false;
			// 圖片驗證
			if ($("#image").val() == "") {
				alert("请選擇要上傳的圖片");
				hasError = true;
			}
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
			if (hasError) {
				return;
			}
			//=====================================================================================

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
				formData.append('restId', 1);

				const jsonData = {};

				Array.from(formData.entries()).forEach(([key, value]) => {
					jsonData[key] = value;
				});

				const jsonString = JSON.stringify(jsonData);

				const url = `${contextPath}/restNews/restNews.do?action=add`;
				$.ajax({
					url: url,
					type: 'POST',
					contentType: 'application/json',
					data: jsonString,
					success: function() {
						alert('新增成功');
						goToRestNewsPage();

					},
					error: function(xhr, error) {
						if (xhr.status === 400) { // 驗證錯誤會返回 400 狀態碼
							const errorMessage = xhr.responseText || "請填寫正確的資料";
							alert(errorMessage);
						} else {
							// 其他狀態碼的處理
							alert("發生未知錯誤，請稍後再試");
						}
					}
				});
			};
			// 讀取圖片檔
			reader.readAsDataURL(file);
		});
	});
});