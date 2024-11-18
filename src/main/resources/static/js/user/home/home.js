/**
 * 会員：ホーム画面 JavaScriptファイル
 */

document.addEventListener("DOMContentLoaded", () => {
	// すべての .item-card 要素を取得
	const items = document.querySelectorAll(".item-card");

	// Intersection Observer を作成
	const observer = new IntersectionObserver((entries, observer) => {
		entries.forEach((entry, index) => {
			if (entry.isIntersecting) {
				// 遅延を追加してクラスを順番に追加
				setTimeout(() => {
					entry.target.classList.add("active");
				}, index * 100); // 各アイテムに100msの遅延を追加

				observer.unobserve(entry.target); // 一度表示したら監視を解除
			}
		});
	}, {
		threshold: 0.1 // 要素が10%表示されたらトリガー
	});

	// 監視対象を設定
	items.forEach(item => observer.observe(item));
});