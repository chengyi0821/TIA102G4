$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
	let originalImageBase64 = ""; // 保存原有图片的 Base64 数据

	$('#imageInput').on('change', function(event) {
		const file = event.target.files[0];
		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				$('#imagePreview').attr('src', e.target.result).show();
			};
			reader.readAsDataURL(file);
		} else {
			$('#imagePreview').hide();
		}
	});

	function loadRestaurantInfo() {
		$.ajax({
			url: `${contextPath}/rest/rest.do?action=findIdByUser`,
			type: 'POST',
			contentType: 'application/json',
			success: function(response) {
				console.log(response); // 打印出回應以確認數據格式
				if (response) {
					$('#restName').val(response.restName);
					$('#location').val(response.location);
					$('#phone').val(response.phone);
					$('#email').val(response.email);
					$('#password').val(response.password); // 密碼通常不會預填
					$('#description').val(response.description);

					// 設置營業日的選中狀態，假設 openDay 是類似 "0101111" 這樣的字串
					const openDayStr = response.openDay || "0000000"; // 如果沒有值，預設為 "0000000"
					$('input[name="openDay"]').each(function(index) {
						$(this).prop('checked', openDayStr.charAt(index) === "1");
					});

					$('#openTime1').val(response.openTime1);
					$('#closeTime1').val(response.closeTime1);
					$('#openTime2').val(response.openTime2);
					$('#closeTime2').val(response.closeTime2);

					// 餐廳類型
					$('#restType').val(response.restType);

					// 餐廳圖片（如果有 Base64 編碼的圖片）
					if (response.image) {
						$('#imagePreview').attr('src', `data:image/jpeg;base64,${response.image}`).show();
						originalImageBase64 = response.image; // 保存原有图片数据
					} else {
						$('#imagePreview').hide(); // 沒有圖片時隱藏
					}
				} else {
					console.error('Response is null or undefined');
				}
			},
			error: function(xhr, status, error) {
				console.error('查詢餐廳信息時出錯:', error);
			}
		});
	}

	loadRestaurantInfo();

	$('#restaurantForm').on('submit', function(event) {
		event.preventDefault(); // 防止表單的默認提交行為

		const formData = new FormData(this); // 使用 FormData 來處理表單數據
		const imageInput = $('#imageInput')[0].files[0];

		// 處理營業日 checkbox 選中的狀態，生成 "0101111" 格式的字串
		let openDayStr = '';
		$('input[name="openDay"]').each(function() {
			openDayStr += $(this).is(':checked') ? '1' : '0';
		});

		// 添加 openDay 字串到 formData
		formData.append('openDay', openDayStr);

		// 將時間字段轉換為 "HH:mm:ss" 格式
		const openTime1WithSeconds = $('#openTime1').val() + ':00';
		const closeTime1WithSeconds = $('#closeTime1').val() + ':00';
		const openTime2WithSeconds = $('#openTime2').val() ? $('#openTime2').val() + ':00' : '';
		const closeTime2WithSeconds = $('#closeTime2').val() ? $('#closeTime2').val() + ':00' : '';

		// 添加時間字段到 formData
		formData.append('openTime1', openTime1WithSeconds);
		formData.append('closeTime1', closeTime1WithSeconds);
		formData.append('openTime2', openTime2WithSeconds);
		formData.append('closeTime2', closeTime2WithSeconds);

		if (imageInput) {
			const reader = new FileReader();
			reader.onload = function(e) {
				const base64String = e.target.result.split(',')[1]; // 取得 Base64 部分
				formData.append('image', base64String); // 添加 Base64 編碼的圖片到表單數據
				submitForm(formData); // 提交表單數據
			};
			reader.readAsDataURL(imageInput);
		} else {
			formData.append('image', originalImageBase64); // 如果沒有新圖片，保留原有圖片的 Base64 編碼
			submitForm(formData); // 提交表單數據
		}
	});

	function submitForm(formData) {
		const data = {};

		// 將 FormData 轉換為 JSON 對象
		formData.forEach((value, key) => {
			data[key] = value;
		});

		$.ajax({
			url: `${contextPath}/rest/rest.do?action=updateForRest`,
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data), // 將數據轉換為 JSON 字符串
			success: function(response) {
				alert('餐廳信息更新成功！');
			},
			error: function(xhr, status, error) {
				console.error('更新餐廳信息時出錯:', error);
				console.error('響應內容:', xhr.responseText); // 輸出響應內容以供調試
			}
		});
	}
});
