/**
 * 会員：購入情報入力画面　JavaScriptファイル
 */

/** カートに戻るボタン */
document.getElementById("backButton").onclick = function(){
	window.location.href = '/user/cart/show';
};

/** 確認画面に進むボタン */
document.getElementById('nextButton').addEventListener('click', function() {
	document.getElementById('form').submit();
});