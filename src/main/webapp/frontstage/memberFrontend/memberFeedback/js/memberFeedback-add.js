function goTorestFeedbackPage() {
	window.location.href = 'memberFeedback.html';
}

function showError(inputId, errorMessage) {
	$(`#${inputId}`).val(errorMessage);
	$(`#${inputId}`).css("color", "red");
	$(`#${inputId}`).on('click', function() {
		$(`#${inputId}`).css("color", "black");
		$(`#${inputId}`).val("");
	});
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

	$('#add-button').click(function() {
		// 刪除指定的元素
		$('#filter-form').remove();
		$('#restFeedback-table').remove();
		$('#data-list').remove();
		$('#page').remove();
		$('#title').remove();

		// 顯示新增公告的表單
		$('#createAndEdit').append(`
        <div id="annoCreateEdit">
            <form id="announcement-form">
                <div id="annoCreate-title">聯絡客服</div>
                        <label for="type" class="heading">主旨:</label>
                        <select id="heading" name="type">
                            <option value="ACCOUNT_ISSUE" class="label">帳號問題</option>
                            <option value="FOOD_SAFETY_ISSUE" class="label">食安問題</option>
                            <option value="ORDER_ISSUE" class="label">訂單問題</option>
                            <option value="SYSTEM_ISSUE" class="label">系統問題</option>
                            <option value="OTHER_ISSUES" class="label">其他</option>
                        </select><br>
                        <label for="feedback-content" class="feedback-content">內容:</label><br>
                        <textarea id="feedback-content" name="content"></textarea><br>
                    <button type="button" id="feedback-submit" class="feedback">確定</button>
                    <button type="button" id="feedback-cancel" class="feedback" onclick="goTorestFeedbackPage()">取消</button>
            </form>
        </div>
    `);

		// 提交表單
		$('#feedback-submit').click(function() {
			let pattern = /^[\u4e00-\u9fa5a-zA-Z0-9_]+$/;
			let hasError = false;

			if ($('#feedback-content').val().length > 500) {
				showError('feedback-content', "公告內容不得超過500字");
				hasError = true;
			}
			if ($('#feedback-content').val() === "") {
				showError('feedback-content', "請填寫公告內容");
				hasError = true;
			}
			if (!pattern.test($('#feedback-content').val())) {
				showError('feedback-content', "只能是中、英文字母、數字和_");
				hasError = true;
			}

			// 如果有錯誤，阻止表單提交
			if (hasError) {
				return;
			}
			console.log($('select#heading').val());
			// 構建JSON對象
			const jsonData = {
				feedbackType: $('#heading').val(),
				feedbackContent: $('#feedback-content').val()
			};

			const url = `${contextPath}/cs/memberFeedback.do?action=add`;

			$.ajax({
				url: url,
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(jsonData),
				success: function() {
					alert("新增成功");
					goTorestFeedbackPage();
				},
				error: function(xhr) {
					if (xhr.status === 400) {
						alert("請填寫正確的資料")
					}
				}
			});
		});
	});
});
