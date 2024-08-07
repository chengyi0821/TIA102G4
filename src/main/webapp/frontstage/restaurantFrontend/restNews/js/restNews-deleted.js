$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	$('#data-table').on('click', '#remove-button', function() {
		var idValue = $(this).val();
		if (confirm("確定要移除嗎?")) {
			$.ajax({
				url: `${contextPath}/restNews/restNews.do?action=delete`,
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
					newsId: idValue,        // 將 id 轉換為字串
					deleted: true       // 傳送 deleted 屬性
				}),
				success: function() {
					alert("移除成功");
					window.location.href = 'restNews.html';
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
