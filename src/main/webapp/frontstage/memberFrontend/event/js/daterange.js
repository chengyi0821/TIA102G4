/**
 * 動態擷取日期輸入格式，只能輸入最多一個月後的日期
 */
document.addEventListener('DOMContentLoaded', function() {
    var dateRange = document.querySelector("#dateRange");
    var date_now = new Date().getTime();
    var date_end = new Date(date_now + 2592000000); // 最晚的日期可以選到30天後
    var date_now = new Date(date_now + 259200000); // 最早的日期可以選到3天後

    function to_YY_MM_DD(date) {
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        let day = date.getDate();
        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;
        return year + "-" + month + "-" + day;
    }

    var max = to_YY_MM_DD(date_end);
    var min = to_YY_MM_DD(date_now);
    dateRange.setAttribute("max", max);
    dateRange.setAttribute("min", min);
});