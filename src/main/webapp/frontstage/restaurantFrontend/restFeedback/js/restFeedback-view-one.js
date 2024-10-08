// 返回信件頁面
function goToCSPage() {
	window.location.href = 'restFeedback.html';
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

	$('#restFeedback-table').on('click', '#update-button', function() {
		let csId = $(this).val();
		// 刪除指定的元素
		$('#filter-form').remove();
		$('#restFeedback-table').remove();
		$('#data-list').remove();
		$('#page').remove();
		$('#title').remove();

		$.ajax({
			url: `${contextPath}/cs/restFeedback.do?action=compositeQuery`,
			type: 'POST',
			success: function(data) {
				if (Array.isArray(data.List)) {
					let customerService = data.List.find(cs => cs.csId === Number(csId));

					if (customerService) {
						var restName = customerService.restName;
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
				                        <label for="start-date" id="replyAll">${restName}</label><br>
				                        <label for="heading" class="label">信件主旨:</label>
				                        <label for="start-date" id="replyAll">${feedbackType}</label><br>
				                        <label for="content" class="labelContent">信件內容:</label>
				                        <label for="start-date" id="replyContent">${feedbackContent}</label><br>
				                    </div>
				                    ${customerService.replyStatus ? `<label for="start-date" class="labelheading">客服人員:</label>` : ``}	
				                    ${customerService.replyStatus ? `<label for="start-date" id="replyAll">${customerService.adminName}</label><br>` : ``}	
				                    	<label for="start-date" class="labelheading">主旨:</label>
				                    ${customerService.replyStatus ? `<label for="start-date" id="replyAll">${replyHeading}</label><br>` : `<label for="start-date" id="replyAll">處理中</label><br>`}
				                    
				                    <div id="anno-image">
				                        <label for="content" class="replyContent">回覆內容:</label>
				                        	${customerService.replyStatus ? `<label for="start-date" id="replyContent1">${customerService.replyContent}</label><br>` : `<label for="start-date" id="reply">客服人員處理中，請耐心等待...</label><br>`}
				                    </div>
				                </div>
				                <div id="buttons-container">
				                    <button type="button" id="submit-announcement" class="label" onclick="goToCSPage()">確定</button>
				                    <button type="button" id="cancel" class="label" onclick="goToCSPage()">取消</button>
				                </div>
				            </form>
				        </div>
				    `);
				}
			}
		});
	});
});