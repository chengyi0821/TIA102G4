function goToAnnoPage() {
	window.location.href = 'announcement.html';
}

function removeDataUrlHeader(dataUrl) {
	if (dataUrl.startsWith("data:")) {
		const commaIndex = dataUrl.indexOf(",");
		if (commaIndex !== -1) {
			return dataUrl.substring(commaIndex + 1);
		}
	}
	return dataUrl;
};

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
				window.location.href = 'announcement.html';
			}
			// 讀取圖片檔
			reader.readAsDataURL(file);
		});
	});
});