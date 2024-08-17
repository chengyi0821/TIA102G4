$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	$("a#logout").click(function() {
		$.ajax({
			url: `${contextPath}/rest/rest.do?action=restaurantLogout`,
			type: 'POST',
			success: function() {
				// 登出成功後重定向到登錄頁面
				window.location.href = `${contextPath}/frontstage/restaurantFrontend/restaurantLogin/restaurantLogin.html`;
			},
			error: function(xhr, status, error) {
				console.error('登出錯誤:', error);
			}
		});
	});
});
