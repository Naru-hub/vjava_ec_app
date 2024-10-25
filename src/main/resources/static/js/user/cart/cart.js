/**
 * 会員：カート画面　JavaScriptファイル
 */

/** 買い物を続けるボタン */
 document.getElementById("continueShopping").onclick = function(){
	window.location.href = '/user/item/list';
 };
 
 /** 詳細画面に遷移 */
 document.addEventListener('DOMContentLoaded', function() {
    // すべての画像に対してクリックイベントを設定
    document.querySelectorAll('.image').forEach(function(image) {
        image.addEventListener('click', function() {
            // data-id属性から商品IDを取得
            const itemId = this.getAttribute('data-id');
            // 商品詳細ページに遷移
            window.location.href = `/user/item/${itemId}`;
        });
    });
});