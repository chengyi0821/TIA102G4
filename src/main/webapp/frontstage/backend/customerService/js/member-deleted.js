$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	$('#data-table').on('click', '#remove-button', function() {
		var idValue = $(this).val();
		if (confirm("確定要移除嗎?")) {
			$.ajax({
				url: `${contextPath}/cs/csMember.do?action=deleted`,
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify({
					csId: idValue,        // 將id轉換為字串
					deletedAdmin: true    // 傳送deleted屬性
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