/**
 * 会員：パスワード再設定メール送信画面 JavaScriptファイル
 */

 function disableButton(form) {
    const button = form.querySelector("#submitButton");
    
    // ボタンを無効化
    button.disabled = true;
    
    // ボタンのテキストをスピナーに変更
    button.innerHTML = '<span class="spinner"></span>';
    
    // スピナーのスタイル（CSSで設定）
    button.querySelector(".spinner").style = `
        display: inline-block;
        width: 1em;
        height: 1em;
        border: 2px solid rgba(0, 0, 0, 0.3);
        border-radius: 50%;
        border-top-color: #000;
        animation: spin 1s ease infinite;
    `;

    return true;  // フォームを送信
}

// スピナーアニメーションのCSS
const style = document.createElement("style");
style.innerHTML = `
@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
`;
document.head.appendChild(style);