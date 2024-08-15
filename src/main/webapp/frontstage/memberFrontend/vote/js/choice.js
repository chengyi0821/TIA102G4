document.addEventListener('DOMContentLoaded', function() {
    const itemList = document.querySelector('.choice_list');
    const mainContent3 = document.getElementById('main-content3');
    const newDiv = document.createElement('div');
    newDiv.id = 'selected-restaurants';
    mainContent3.appendChild(newDiv);

    const selectedItems = new Set();
    const selectedRestaurants = [];

    itemList.addEventListener('click', function(event) {
        const li = event.target.closest('li');
        if (li) {
            const restId = li.getAttribute('data-restaurant-id');
            const restName = li.querySelector('.restName').textContent;
            const restInfo = `${restId}:${restName}`;

            if (selectedItems.has(restInfo)) {
                selectedItems.delete(restInfo);
                selectedRestaurants.splice(selectedRestaurants.findIndex(r => r.id === restId), 1);
                li.style.backgroundColor = '';
            } else if (selectedItems.size < 3) {
                selectedItems.add(restInfo);
                selectedRestaurants.push({id: restId, name: restName});
                li.style.backgroundColor = 'lightblue';
            } else {
                alert('已經選擇三家餐廳，如要增加請刪除不需要的選項');
            }
            updateSelectedRestaurants();
        }
    });

    function updateSelectedRestaurants() {
        newDiv.innerHTML = '<h3>已選擇的餐廳：</h3>';
        selectedRestaurants.forEach(restaurant => {
            newDiv.innerHTML += `${restaurant.name} (ID: ${restaurant.id})<br>`;
        });

        if (selectedRestaurants.length > 0) {
            let formHtml = '<form action="' + contextPath + '/frontstage/memberFrontend/vote/option.do" method="post">';
            formHtml += `<input type="hidden" name="restList" value='${JSON.stringify(selectedRestaurants)}'>`;
            formHtml += '<input type="hidden" name="action" value="option">';
            formHtml += '<input type="submit" value="提交選擇">';
            formHtml += '</form>';
            newDiv.innerHTML += formHtml;
        }
    }
});