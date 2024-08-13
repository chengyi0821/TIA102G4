// 返回信件頁面
function goToCSPage() {
	window.location.href = 'member-reply.html';
}

function getFeedbackTypeDescription(type) {
	switch (type) {
		case "ACCOUNT_ISSUE":
			return "帳號問題";
		case "FOOD_SAFETY_ISSUE":
			return "食安問題";
		case "ORDER_ISSUE":
			return "訂單問題";
		case "SYSTEM_ISSUE":
			return "系統問題";
		case "OTHER_ISSUES":
			return "其他";
	}
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

	//=======================================將單筆資料顯示出來=======================================

	$('#data-table').on('click', '#update-button', function() {
		let csId = $(this).val();
		// 刪除指定的元素
		$('#filter-form').remove();
		$('#data-table').remove();
		$('#data-list').remove();
		$('#pagination').remove();
		$('#announcement-title').remove();

		$.ajax({
			url: `${contextPath}/cs/csMember.do?action=compositeQuery`,
			type: 'POST',
			success: function(data) {
				if (Array.isArray(data.List)) {
					let customerService = data.List.find(cs => cs.csId === Number(csId));

					if (customerService) {
						var memberName = customerService.memberName;
						var feedbackType = customerService.feedbackType;
						var feedbackContent = customerService.feedbackContent;
					}
					var feedbackType = getFeedbackTypeDescription(customerService.feedbackType);
					var replyHeading = getFeedbackTypeDescription(customerService.replyHeading);

					// 顯示新增公告的表單
					$('#createAndEdit').append(`
				        <div id="annoCreateEdit">
				            <form id="announcement-form" enctype="multipart/form-data">
				                <div id="annoCreate-title">${customerService.replyStatus ? '信件瀏覽' : '信件回覆'}</div>
				                <div id="customer-service">
				                    <div id="form-content">
				                        <label for="start-date" class="label">郵件編號:</label>
				                        <label for="start-date" id="replyAll">${csId}</label><br>
				                        <label for="end-date" class="label">寄件者:</label>
				                        <label for="start-date" id="replyAll">${memberName}</label><br>
				                        <label for="heading" class="label">信件主旨:</label>
				                        <label for="start-date" id="replyAll">${feedbackType}</label><br>
				                        <label for="content" class="labelContent">信件內容:</label>
				                        <label for="start-date" id="replyContent">${feedbackContent}</label><br>
				                    </div>
				                    ${customerService.replyStatus ? `<label for="start-date" class="labelheading">客服人員:</label>` : ``}	
				                    ${customerService.replyStatus ? `<label for="start-date" id="replyAll">${customerService.adminName}</label><br>` : ``}	
				                    	<label for="start-date" class="labelheading">主旨:</label>
				                    ${customerService.replyStatus ? `<label for="start-date" id="replyAll">${replyHeading}</label><br>` : `<select id="reply-type" name="replyType">
										<option value="" style="color: gray;">主旨:</option>
										<option value="ACCOUNT_ISSUE">帳號問題</option>
										<option value="FOOD_SAFETY_ISSUE">食安問題</option>
										<option value="ORDER_ISSUE">訂單問題</option>
										<option value="SYSTEM_ISSUE">系統問題</option>
										<option value="OTHER_ISSUES">其他</option>
									</select>`}
				                    
				                    <div id="anno-image">
				                        <label for="content" class="replyContent">回覆內容:</label>
				                        	${customerService.replyStatus ? `<label for="start-date" id="replyContent1">${customerService.replyContent}</label><br>` : `<textarea id="replycontent" name="replycontent"></textarea><br>`}
				                    </div>
				                </div>
				                <div id="buttons-container">
				                    ${customerService.replyStatus ? `<button type="button" id="submit-announcement1" class="label" onclick="goToCSPage()">確定</button>` : `<button type="button" id="submit-announcement" class="label">確定</button>`}
				                    <button type="button" id="cancel" class="label" onclick="goToCSPage()">取消</button>
				                </div>
				            </form>
				        </div>
				    `);

					//=======================================回覆=======================================
					$('#submit-announcement').on('click', function() {
						$.ajax({
							url: `${contextPath}/cs/csMember.do?action=add`,
							type: 'POST',
							contentType: 'application/json',
							data: JSON.stringify({
								csId: csId,
								replyHeading: $('select#reply-type').val(),
								replyContent: $('textarea#replycontent').val(),
								adminId: "1"
							}),
							error: function(error) {
								console.log('add failed:', error);
							}
						})
						alert("回覆成功");
						goToCSPage();
					});
				}
			}
		});
	});
});