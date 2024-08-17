// main.js 文件
function showError() {
	console.log("showError 函数被调用");
    alert("邀請碼無效，請檢查後重新輸入。");
}

function submitForm(action) {
	    if (action === 'addOrder') {
	        document.querySelector('form[action*="/myorder/membermyorder.do"]').submit();
	    } else if (action === 'noshow') {
	        document.querySelector('form[action*="/event/event.do"]').submit();
	    }
	}

