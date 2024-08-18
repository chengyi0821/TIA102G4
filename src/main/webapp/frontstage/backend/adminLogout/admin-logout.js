$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	$("a#logout").click(function() {
		$.ajax({
			url: `${contextPath}/admin/logout.do?action=adminLogout`,
			type: 'POST',
			success: function() {
				// 登出成功後重定向到登錄頁面
				window.location.href = `${contextPath}/frontstage/backend/admin/adminlogin.jsp`;
			},
			error: function(xhr, status, error) {
				console.error('登出錯誤:', error);
			}
		});
	});
});
