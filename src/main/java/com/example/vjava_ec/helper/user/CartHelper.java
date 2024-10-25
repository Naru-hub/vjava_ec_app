package com.example.vjava_ec.helper.user;

import java.text.NumberFormat;
import java.util.Locale;

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
		String displayPrice = NumberFormat.getNumberInstance(Locale.JAPAN).format(item.getPrice());
		String displayTotalPrice = NumberFormat.getNumberInstance(Locale.JAPAN).format(item.getPrice() * amount);
		CartItem cartItem = new CartItem(item,displayPrice,displayTotalPrice,amount);
		return cartItem;
	}

}
