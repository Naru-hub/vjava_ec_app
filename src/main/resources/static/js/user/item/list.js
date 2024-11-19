/**
 * 会員：商品一覧画面 JavaScriptファイル
 */

document.addEventListener("DOMContentLoaded", () => {
	// すべての .item-card 要素を取得
	const items = document.querySelectorAll(".item-card");

	// Intersection Observer を作成
	const observer = new IntersectionObserver((entries, observer) => {
		entries.forEach((entry) => {
			if (entry.isIntersecting) {
				// 全てのアイテムにクラスを追加
				items.forEach((item, index) => {
					setTimeout(() => {
						item.classList.add("active");
					}, index * 100); // 各アイテムに100msの遅延を追加
				});

				// 監視を解除（全てが一度で表示されるので監視不要）
				observer.disconnect();
			}
		});
	}, {
		threshold: 0.1 // 要素が10%表示されたらトリガー
	});

	// 監視対象を設定
	items.forEach(item => observer.observe(item));
});