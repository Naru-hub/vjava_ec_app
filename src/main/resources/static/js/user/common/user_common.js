document.addEventListener("DOMContentLoaded", function() {
	const menuButton = document.getElementById('hamburger-btn'); // ハンバーガーボタンのIDを指定
	const menu = document.getElementById('nav-links'); // ナビゲーションリンクのIDを指定
	console.log('テスト');


	// ハンバーガーメニューをクリックしたときにナビゲーションリンクの表示/非表示を切り替え
	menuButton.addEventListener('click', function() {
		console.log(menuButton, menu);

		console.log('テスト');
		menu.classList.toggle('show');
	});
});