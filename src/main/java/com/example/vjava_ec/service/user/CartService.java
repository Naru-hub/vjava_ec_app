package com.example.vjava_ec.service.user;

import com.example.vjava_ec.entity.Cart;
import com.example.vjava_ec.entity.CartItem;

/**
 * CartServiceのInterfaceクラス
 */
public interface CartService {
	
	/**
	 * カートに商品を追加
	 */
	Cart addCartItem(Cart cart, CartItem addItem);
	
	/**
	 * カートの合計金額を取得
	 */
	int getTotalPrice(Cart cart);
	
	/**
	 * カートアイテムの数量を変更
	 */
	Cart editCartItemAmount(Cart cart,int itemId,int newAmount);
	
	/**
	 * 特定のItemIdのカートアイテムを削除
	 */
	Cart deleteCartItemByItemId(Cart cart,int itemId);
	
	/**
	 * カートを空にする
	 */
	Cart deleteAllCartItem(Cart cart);
	
	/**
	 * 表示用の合計金額を取得する
	 */
	String getDisplayTotalPrice(Cart cart);
}
