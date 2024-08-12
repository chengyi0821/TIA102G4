$(document).ready(function() {

	// 表单提交事件
	$('#filter-form').on('submit', function(event) {
		event.preventDefault();
		const searchQuery = $('#search-input').val();
		const feedbackType = $('select#feedback-type').val();
		const feedbackTime = $('input#end-Date').val();
		const replyStatus = $('select#reply-status').val();
		console.log(feedbackType);
		currentPage = 1;  // 重置頁碼
		// 如果所有输入都是空的，不進行查询
        if (!searchQuery && !feedbackType && !feedbackTime && !replyStatus) {
            return window.location.href = 'member-reply.html';
        }
		loadCompositeQuery(feedbackType, feedbackTime, searchQuery, replyStatus, contextPath);
	});

	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	const dataList = $('#data-list');
	function loadCompositeQuery(feedbackType, feedbackTime, searchQuery, replyStatus, contextPath) {

		//=======================================複合查詢=======================================
		$('#data-list').empty();
		$('div#pagination').remove();

		$.ajax({
			url: `${contextPath}/cs/csMember.do?action=compositeQuery`,
			type: 'POST',
			data: {
				feedbackTime: feedbackTime || "",
				feedbackType: feedbackType || "",
				searchQuery: searchQuery || "",
				replyStatus: replyStatus || "",
			},
			success: function(data) {
				if (data && Array.isArray(data.List)) {
					data.List.forEach(cs => {
						let feedbackType;
						switch (cs.feedbackType) {
							case "ACCOUNT_ISSUE":
								feedbackType = "帳號問題"
								break;
							case "FOOD_SAFETY_ISSUE":
								feedbackType = "食安問題"
								break;
							case "ORDER_ISSUE":
								feedbackType = "訂單問題"
								break;
							case "SYSTEM_ISSUE":
								feedbackType = "系統問題"
								break;
							case "OTHER_ISSUES":
								feedbackType = "其他"
								break;
						}
						// 設定內容文字的最大長度
						let maxContentLength = 20;
						let feedbackContentText = cs.feedbackContent.length > maxContentLength
							? cs.feedbackContent.substring(0, maxContentLength) + "..."
							: cs.feedbackContent;

						//如果1.已回覆 2.未回覆
						var replyStatus;
						if (cs.replyStatus) {
							replyStatus = "已回覆";
						} else {
							replyStatus = "未回覆";
						}

						dataList.append(`
                        <tr>
                            <td>${cs.csId}</td>
                            <td>${cs.memberName}</td>
                            <td>${feedbackType}</td>
                            <td>${feedbackContentText}</td>
                            <td>${replyStatus}</td>
                            <td>${cs.feedbackTime}</td>
                            <td>
								<button value="${cs.csId}" type="button" id="update-button"><img id="edit" src="image/edit.png" alt="圖示"></button>
								<button value="${cs.csId}" type="button" id="remove-button"><img id="delete" src="image/delete.png" alt="圖示"></button>
							</td>
                        </tr>
                    `);
					})
				}
			}
		})
	}
});