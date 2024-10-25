/**
 * 会員：商品詳細画面 JavaScriptファイル
 */

/** 数量-１ボタン */
function decreaseQuantity() {
    var quantityInput = document.getElementById("quantity-input");
    var quantityDisplay = document.querySelector(".quantity-display")
    var currentValue = parseInt(quantityInput.value);
    if (currentValue > 1) {
        quantityInput.value = currentValue - 1;
        quantityDisplay.textContent = currentValue - 1;
    }
}

/** 数量+１ボタン */
function increaseQuantity() {
    var quantityInput = document.getElementById("quantity-input");
    var quantityDisplay = document.querySelector(".quantity-display")
    var currentValue = parseInt(quantityInput.value);
    if (currentValue < 99) {  // 最大数量99に制限
        quantityInput.value = currentValue + 1;
        quantityDisplay.textContent = currentValue + 1;
    }
};

/** 一覧に戻るボタン */
document.getElementById("backButton").onclick = function() {
    window.location.href = '/user/item/list';
};

/** カートに商品を追加ボタン */ 
function submitForm() {
	document.getElementById("cartForm").submit();
}