


// 獲取相關元素
const addBtn = document.getElementById('add-btn');
const modal = document.getElementById('modal');
const modalClose = document.getElementsByClassName('close')[0];
const itemForm = document.getElementById('item-form');
const itemTable = document.getElementById('item-table');
const searchInput = document.getElementById('search-input');
const searchBtn = document.getElementById('search-btn');

// 綁定事件
addBtn.addEventListener('click', openModal);
modalClose.addEventListener('click', closeModal);
itemForm.addEventListener('submit', addItem);
itemTable.addEventListener('click', handleTableClick);
searchBtn.addEventListener('click', searchItems);

// 讓Modal顯示
function openModal() {
    modal.style.display = 'block';
}

// 關閉Modal
function closeModal() {
    modal.style.display = 'none';
}

// 處理表格中的點擊事件（編輯或刪除）
function handleTableClick(event) {
    const target = event.target;

    if (target.classList.contains('edit-btn')) {
        openModal();
        // 根據點擊的行數獲取要編
        const selectedRow = target.closest('tr');
        fillForm(selectedRow);
    } else if (target.classList.contains('delete-btn')) {
        const selectedRow = target.closest('tr');
        deleteItem(selectedRow);
    }
}

// 將表格行的數據填充到表單中進行編輯
function fillForm(row) {
    const cells = row.getElementsByTagName('td');
    const itemId = cells[0].innerText;
    const itemName = cells[1].innerText;
    const itemType = cells[2].innerText;
    const status = cells[3].innerText;
    const loanDays = cells[4].innerText;
    
    const purchasedate = cells[5].innerText;
    const tenure = cells[6].innerText;
    const compensationprice = cells[7].innerText;


    document.getElementById('item-id').value = itemId;
    document.getElementById('item-name').value = itemName;
    document.getElementById('item-type').value = itemType;

    document.getElementById('loan-days').value = loanDays;
    document.getElementById('status').value = status;
    document.getElementById('purchase-date').value = purchasedate;
    document.getElementById('tenure').value = purchasedate;
    document.getElementById('compensation-price').value = compensationprice;
    row.remove(); // 移除原本的那一列資料
}

// 刪除項目
function deleteItem(row) {
    row.remove();
}

// 添加新項目
function addItem(event) {
    event.preventDefault();

    const itemId = document.getElementById('item-id').value;
    const itemName = document.getElementById('item-name').value;
    const itemType = document.getElementById('item-type').value;
    const loanDays = document.getElementById('loan-days').value;
    const status = document.getElementById('status').value;
    const purchasedate = document.getElementById('purchase-date').value;
    const tenure= document.getElementById('tenure').value;
    const compensationprice = document.getElementById('compensation-price').value;

    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${itemId}</td>
        <td>${itemName}</td>
        <td>${itemType}</td>
       
        <td>${status}</td>
        <td>${loanDays}</td>
        <td>${purchasedate}</td>
        <td>${tenure}</td>
        <td>${compensationprice}</td>
        <td>
            <button class="edit-btn">編輯</button>
            <button class="delete-btn">刪除</button>
        </td>
    `;

    itemTable.appendChild(newRow);
    closeModal();
    resetForm();
}

// 搜尋項目
function searchItems() {
    const searchTerm = searchInput.value.toLowerCase();
    const rows = itemTable.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');
        let match = false;

        for (let j = 0; j < cells.length - 1; j++) {
            const cellText = cells[j].innerText.toLowerCase();

            if (cellText.includes(searchTerm)) {
                match = true;
                break;
            }
        }

        rows[i].style.display = match ? '' : 'none';
    }
}

function resetForm() {
    document.getElementById('item-id').value = "";
    document.getElementById('item-name').value = "";
    document.getElementById('item-type').value ="";

    document.getElementById('loan-days').value = "";
    document.getElementById('status').value = "";
    document.getElementById('purchase-date').value = "";
    document.getElementById('tenure').value = "";
    document.getElementById('compensation-price').value = "";
    selectedRow = null;
}