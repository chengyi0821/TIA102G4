function showError(inputId, errorMessage) {
	$(`#${inputId}`).val(errorMessage);
	$(`#${inputId}`).css("color", "red");
	$(`#${inputId}`).on('click', function() {
		$(`#${inputId}`).css("color", "black");
		$(`#${inputId}`).val("");
	});
}

function goToRestNewsPage() {
	window.location.href = '../restNews/restNews.html';
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	$('#loginForm').on('submit', function(event) {
		event.preventDefault(); // 阻止表單的預設提交動作

		var formData = {
			email: $('#account').val(),
			password: $('#password').val()
		};

		$.ajax({
			url: `${contextPath}/rest/rest.do?action=restaurantLogin`,
			type: 'POST',
			data: JSON.stringify(formData),
			contentType: 'application/json', 
			success: function(response) {
				alert('登入成功');
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
	});
});