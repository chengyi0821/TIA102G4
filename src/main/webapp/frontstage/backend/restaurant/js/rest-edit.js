function goToAnnoPage() {
	window.location.href = 'restaurant.html';
}

function showError(inputId, errorMessage) {
	$(`#${inputId}`).val(errorMessage);
	$(`#${inputId}`).css("color", "red");
	$(`#${inputId}`).on('click', function() {
		$(`#${inputId}`).css("color", "black");
		$(`#${inputId}`).val("");
	});
}

function errorValidation() {
	let hasError = false;

	if ($('#end-date').val() < $('#start-date').val()) {
		alert('結束日期不能小於起始日期！');
		hasError = true;
	}
	if ($('#start-date').val() == "" || $('#end-date').val() == "") {
		alert("請選擇日期");
		hasError = true;
	}
	if ($('#heading').val().length > 50 || $('#heading').val() == "公告主旨不得超過50字") {
		showError('heading', "公告主旨不得超過50字");
		hasError = true;
	}
	if ($('#heading').val() == "" || $('#heading').val() == "請填寫公告主旨") {
		showError('heading', "請填寫公告主旨");
		hasError = true;
	}
	if ($('#content').val().length > 500 || $('#content').val() == "公告內容不得超過500字") {
		showError('content', "公告內容不得超過500字");
		hasError = true;
	}
	if ($('#content').val() == "" || $('#content').val() == "請填寫公告內容") {
		showError('content', "請填寫公告內容");
		hasError = true;
	}
	return hasError;
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let currentPage = 1;

	$('#data-table').on('click', '#update-button', function() {
		var restId = $(this).val();
		$('#filter-form').remove();
		$('#data-table').remove();
		$('#data-list').remove();
		$('#pagination').remove();
		$('#announcement-title').remove();
		$.ajax({
			url: `${contextPath}/rest/rest.do?action=compositeQuery`,
			type: 'GET',
			success: function(response) {
				if (Array.isArray(response.List)) {
					var rest = response.List.find(a => a.restId === Number(restId));
					console.log(rest);
					if (rest) {
						var restName = rest.restName;
						var email = rest.email;
						var password = rest.password;
						var phone = rest.phone;
						var location = rest.location;

						$('#createAndEdit').append(`
							<div id="annoCreateEdit">
								<form id="announcement-form" enctype="multipart/form-data">
									<div id="annoCreate-title">編輯店家</div>
									<div id="announcement-form">
											<label for="start-date" class="label">店家編號</label>
											<label type="text" id="restId">${restId}</label><br>
											<label for="end-date" class="label">店家名稱:</label>
											<input type="text" id="restName" class="rest" name="heading" value="${restName}"><br>
											<label for="heading" class="label">信箱</label>
											<input type="text" id="email" class="rest" name="heading" value="${email}"><br>
											<label for="heading" class="label">密碼</label>
											<input type="text" id="password" class="rest" name="heading" value="${password}"><br>
											<label for="heading" class="label">電話號碼</label>
											<input type="text" id="phone" class="rest" name="heading" value="${phone}"><br>
											<label for="heading" class="label">地址</label>
											<input type="text" id="location" class="rest" name="heading" value="${location}"><br>
										<div id="buttons-Edit">
											<button type="button" id="submit-announcement" href="announcement.html">確定</button>
											<button type="button" id="cancel" class="label" onclick="goToAnnoPage()">取消</button>
										</div>
									</div>
								</form>
							</div>
						`);

						$('#submit-announcement').on('click', function() {
							$.ajax({
								url: `${contextPath}/rest/rest.do?action=update`,
								type: 'POST',
								contentType: 'application/json',
								data: JSON.stringify({
									restId: restId,
									restName: $('#restName').val(),
									email: $('#email').val(),
									password: $('#password').val(),
									phone: $('#phone').val(),
									location: $('#location').val()
								}),
								success: function(response) {
									alert("編輯成功!")
									window.location.href = 'restaurant.html';
								},
								error: function(xhr, error) {
									if (xhr.status === 400) {
										alert("請填寫正確的資料");
									}
								}
							});
						});
					}
				}
			}
		});
	});
});