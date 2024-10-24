/**
 * 会員：商品詳細画面 JavaScriptファイル
 */

function decreaseQuantity() {
    var quantityInput = document.getElementById("quantity-input");
    var quantityDisplay = document.querySelector(".quantity-display")
    var currentValue = parseInt(quantityInput.value);
    if (currentValue > 1) {
        quantityInput.value = currentValue - 1;
        quantityDisplay.textContent = currentValue - 1;
    }
}

function increaseQuantity() {
    var quantityInput = document.getElementById("quantity-input");
    var quantityDisplay = document.querySelector(".quantity-display")
    var currentValue = parseInt(quantityInput.value);
    if (currentValue < 99) {  // 最大数量99に制限
        quantityInput.value = currentValue + 1;
        quantityDisplay.textContent = currentValue + 1;
    }
}

function submitForm() {
    document.getElementById("cartForm").submit();
  }