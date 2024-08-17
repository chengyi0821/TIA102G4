document.addEventListener('DOMContentLoaded', function() {
    const itemList = document.querySelector('#main-content2 ul.item_list');
    const mainContent3 = document.getElementById('main-content3');
    const newDiv = document.createElement('div');
    newDiv.id = 'selected-restaurants';
    mainContent3.appendChild(newDiv);

    const selectedItems = new Set();

    itemList.addEventListener('click', function(event) {
        const li = event.target.closest('li');
        if (li) {
            const restaurantId = li.getAttribute('data-restaurant-id');
            const itemName = li.querySelector('span.item_name').textContent;
            const itemDescription = li.querySelector('span.item_description').textContent;

            if (selectedItems.has(restaurantId)) {
                selectedItems.delete(restaurantId);
                li.style.backgroundColor = '';
            } else if (selectedItems.size < 3) {
                selectedItems.add(restaurantId);
                li.style.backgroundColor = 'lightblue';
            } else {
                alert('已經選擇三家餐廳，如要增加請刪除不需要的選項');
            }
            updateSelectedRestaurants();
        }
    });

    function updateSelectedRestaurants() {
        newDiv.innerHTML = '<h3>已選擇的餐廳：</h3>';
        const selectedRestaurants = [];

        selectedItems.forEach(restaurantId => {
            const li = document.querySelector(`li[data-restaurant-id="${restaurantId}"]`);
            const itemName = li.querySelector('span.item_name').textContent;
            const itemDescription = li.querySelector('span.item_description').textContent;
            newDiv.innerHTML += `${itemName}<br>`;

            selectedRestaurants.push({
                'restaurantId': restaurantId,
                'itemName': itemName,
                'itemDescription': itemDescription
            });
        });

        if (selectedItems.size > 0) {
            let formHtml = '<form action="' + contextPath + '/frontstage/memberFrontend/vote/option.do" method="post">';
            formHtml += `<input type="hidden" name="restList" value='${JSON.stringify(selectedRestaurants)}'>`;
            formHtml += '<input type="hidden" name="action" value="option">';
            formHtml += '<input type="submit" value="提交選擇">';
            formHtml += '</form>';
            newDiv.innerHTML += formHtml;
        }
    }
});