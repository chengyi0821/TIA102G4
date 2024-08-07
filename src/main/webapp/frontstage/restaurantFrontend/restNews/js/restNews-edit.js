function goToRestNewsPage() {
	window.location.href = 'restNews.html';
}

$(document).ready(function() {
	const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));

	var imageNotChange;
	$('#data-table').on('click', '#update-button', function() {
		var newsId = $(this).val();
		$.ajax({
			url: `${contextPath}/restNews/restNews.do?action=compositeQuery`,
			type: 'GET',
			success: function(response) {
				if (Array.isArray(response.List)) {
					var restNews = response.List.find(a => a.newsId === Number(newsId));
					if (restNews) {
						var heading = restNews.heading;
						var type = restNews.type;
						var content = restNews.content;
						var startDate = restNews.startDate;
						var endDate = restNews.endDate;
						var imageBase64 = restNews.image;

						imageNotChange = imageBase64;
						$('#filter-form').remove();
						$('#data-table').remove();
						$('#data-list').remove();
						$('#pagination').remove();
						$('#announcement-title').remove();

						$('#createAndEdit').append(`
                        <div id="annoCreateEdit">
                            <form id="announcement-form" enctype="multipart/form-data">
                                <div id="annoCreate-title">編輯公告</div>
                                    <div id="announcement-form">
                                        <div id="form-content">
                                            <label for="start-date" class="label">起始日期:</label>
                                            <input type="date" id="start-date" name="start-date" value="${startDate}"><br>
                                            <label for="end-date" class="label">結束日期:</label>
                                            <input type="date" id="end-date" name="end-date" value="${endDate}"><br>
                                            <label for="type" class="label">類型:</label>
                                            <select id="type" name="type">
                                                <option value="ANNOUNCEMENT" ${type === 1 ? 'selected' : ''}>公告</option>
                                                <option value="ADVERTISEMENT" ${type === 2 ? 'selected' : ''}>廣告</option>
                                            </select><br>
                                            <label for="heading" class="label">公告主旨:</label>
                                            <input type="text" id="heading" name="heading" value="${heading}"><br>
                                            <label for="content" class="labelContent">公告內容:</label>
                                            <textarea id="content" name="content">${content}</textarea><br>
                                        </div>
                                            <div id="anno-image">
                                                <label for="image" class="image">圖片:</label>
                                                <div id="image-container">
                                                    <img id="current-image" alt="" style="max-width: 200px; max-height: 246px;">
                                                    <input type="file" id="image" name="image" style="display: none;">
                                                    <button type="button" id="custom-file-upload" style="position: absolute; bottom: 10px; right: 10px; z-index: 1;">編輯</button>
                                                </div>
                                            </div>
                                            <div id="buttons-Edit">
                                                <button type="button" id="submit-announcement" href="restNews.html">確定</button>
                                                <button type="button" id="cancel" class="label" onclick="goToRestNewsPage()">取消</button>
                                            </div>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    `);

						$('#current-image').attr('src', 'data:image/jpeg;base64,' + imageBase64);

						$('#custom-file-upload').off('click').on('click', function() {
							$('#image').click();
						});

						var imageFile = "";
						$('#image').off('change').on('change', function() {
							const file = this.files[0];
							const maxFileSize = 4 * 1024 * 1024 * 1024;
							const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];

							if (file) {
								if (file.size > maxFileSize) {
									alert('圖片上傳失敗! 圖片不得超過4GB');
									$(this).val('');
									$('#current-image').attr('src', '');
									imageFile = null;
									return;
								}

								if (!allowedTypes.includes(file.type)) {
									alert('Please select a valid image file (JPEG, PNG, GIF).');
									$(this).val('');
									$('#current-image').attr('src', '');
									imageFile = null;
									return;
								}

								const reader = new FileReader();
								reader.onload = function(e) {
									$('#current-image').attr('src', e.target.result);
								};
								reader.readAsDataURL(file);
								imageFile = file;
							} else {
								$('#current-image').attr('src', '');
								imageFile = null;
							}
						});

						$('#submit-announcement').off('click').on('click', function() {
							if (imageFile) {
								var reader = new FileReader();
								reader.onload = function(event) {
									var base64Image = event.target.result.split(',')[1];
									var startDateInput = $('#start-date').val();
									var endDateInput = $('#end-date').val();
									const startDateFormatted = DateToISO(startDateInput);
									const endDateFormatted = DateToISO(endDateInput);

									$.ajax({
										url: `${contextPath}/restNews/restNews.do?action=update`,
										type: 'POST',
										contentType: 'application/json',
										data: JSON.stringify({
											newsId: newsId,
											startDate: startDateFormatted,
											endDate: endDateFormatted,
											heading: $('#heading').val(),
											content: $('#content').val(),
											type: $('#type').val(),
											image: base64Image,
											deleted: false,
											restId: 1
										}),
										error: function(error) {
											console.error('Update failed:', error);
										}
									});
								};
								reader.readAsDataURL(imageFile);
							} else {
								var startDateInput = $('#start-date').val();
								var endDateInput = $('#end-date').val();
								const startDateFormatted = DateToISO(startDateInput);
								const endDateFormatted = DateToISO(endDateInput);

								$.ajax({
									url: `${contextPath}/restNews/restNews.do?action=update`,
									type: 'POST',
									contentType: 'application/json',
									data: JSON.stringify({
										newsId: newsId,
										startDate: startDateFormatted,
										endDate: endDateFormatted,
										heading: $('#heading').val(),
										content: $('#content').val(),
										type: $('#type').val(),
										image: imageNotChange,
										deleted: false,
										restId: 1
									}),
									success: function(response) {
										console.log('Update successful:', response);
									},
									error: function(error) {
										console.error('Update failed:', error);
									}
								});
							}
							alert("編輯成功!");
							goToRestNewsPage();
						});
					}
				}
			}
		});
	});
});

function DateToISO(dateString) {
	var date = new Date(dateString);
	return date.toISOString();
}
