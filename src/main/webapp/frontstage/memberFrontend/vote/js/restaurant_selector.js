document.addEventListener('DOMContentLoaded', function() {
    const table = document.querySelector('table');
    const hoverColor = "rgb(233, 255, 151)";
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    const maxAllowed = 3;
    
    table.addEventListener('click', function(e) {
        const tr = e.target.closest('tr');
        if (!tr) return;
        
        const checkbox = tr.querySelector('input[type="checkbox"]');
        if (!checkbox) return;
        
        if (e.target !== checkbox) {
            if (!checkbox.checked && getCheckedCount() >= maxAllowed) {
                alert('最多選擇三間餐廳，如要更換選項請取消勾選');
                return;
            }
            checkbox.checked = !checkbox.checked;
        }
        
        updateRowColor(tr, checkbox.checked);
        updateCheckboxState();
    });

    function updateRowColor(tr, isChecked) {
        if (isChecked) {
            tr.style.backgroundColor = hoverColor;
        } else {
            tr.style.backgroundColor = '';
        }
    }

    function getCheckedCount() {
        return document.querySelectorAll('input[type="checkbox"]:checked').length;
    }

    function updateCheckboxState() {
        const checkedCount = getCheckedCount();
        checkboxes.forEach(cb => {
            if (!cb.checked) {
                cb.disabled = checkedCount >= maxAllowed;
            }
        });
    }

    // 初始化已勾選的行的顏色和複選框狀態
    checkboxes.forEach(checkbox => {
        updateRowColor(checkbox.closest('tr'), checkbox.checked);
    });
    updateCheckboxState();
});