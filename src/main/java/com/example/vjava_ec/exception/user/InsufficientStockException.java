package com.example.vjava_ec.exception.user;

import java.util.ArrayList;
import java.util.List;

import com.example.vjava_ec.entity.Item;

/**
 * 在庫不足用のカスタムエラークラス
 */
public class InsufficientStockException extends IllegalStateException {
	/** アイテムId */
	private final Item item;
	/** 在庫数 */
	private final int stock;
	/** メッセージ */
	private final List<String> errorMessage;
	
	/**
	 * コンストラクタ
	 * @param itemId
	 * @param stock
	 */
	public InsufficientStockException(Item item, int stock) {
		super("以下の商品の在庫が足りなかったため購入できませんでした商品名：" + item.getName() + "在庫数：" + stock);
		this.item = item;
		this.stock = stock;
		List<String> message = new ArrayList<>();
		message.add("以下の商品の在庫が足りなかったため購入できませんでした");
		message.add("商品名：" + item.getName() + "　在庫数：" + stock);
		this.errorMessage = message;
	}
	
	/**
	 * 商品IDのゲッター
	 */
	public Item getItemId() {
		return item;
	}
	
	/**
	 * 在庫数のゲッター
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * エラーメッセージのゲッター
	 */
	public List<String> getErrorMessage(){
		return errorMessage;
	}
}
