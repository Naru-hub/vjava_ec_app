package com.example.vjava_ec.helper.user;

import com.example.vjava_ec.entity.CartItem;
import com.example.vjava_ec.entity.Item;

/**
 * カートのヘルパークラス
 */
public class CartHelper {
	
	/**
	 * Itemエンティティと数量からCartItemエンティティを生成
	 * @param item
	 * @param amount
	 * @return CartItem CartItemエンティティ
	 */
	public static CartItem convertCart(Item item, int amount) {
		CartItem cartItem = new CartItem(item,amount);
		return cartItem;
	}

}
