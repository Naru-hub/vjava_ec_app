package com.example.vjava_ec.exception.user;

/**
 * 在庫不足用のカスタムエラークラス
 */
public class InsufficientStockException extends IllegalStateException {
	/** アイテムId */
	private final int itemId;
	/** 在庫数 */
	private final int stock;
	
	/**
	 * コンストラクタ
	 * @param itemId
	 * @param stock
	 */
	public InsufficientStockException(int itemId, int stock) {
		super("在庫不足: 商品ID " + itemId + ", 在庫数 " + stock);
		this.itemId = itemId;
		this.stock = stock;
	}
	
	/**
	 * 商品IDのゲッター
	 * @return
	 */
	public int getItemId() {
		return itemId;
	}
	
	/**
	 * 在庫数のゲッター
	 * @return
	 */
	public int getStock() {
		return stock;
	}
}
