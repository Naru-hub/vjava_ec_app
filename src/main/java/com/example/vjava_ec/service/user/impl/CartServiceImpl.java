package com.example.vjava_ec.service.user.impl;

import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.Cart;
import com.example.vjava_ec.entity.CartItem;
import com.example.vjava_ec.service.user.CartService;

/**
 * CartService実装クラス
 */
@Service
public class CartServiceImpl implements CartService{

	/**
	 * カートに商品を追加
	 * @param cart
	 * @param addItem 追加するCartItemエンティティ
	 * @return Cart Cartエンティティ
	 */
	@Override
	public Cart addCartItem(Cart cart, CartItem addCartItem) {
		// 既にカートに同じ商品がないか確認。ある場合数量を増やす
		for(CartItem cartItem : cart.getCartItems()) {
			// 商品IDを比べる
			if(cartItem.getItem().getId() == addCartItem.getItem().getId()) {
				cartItem.setAmount(cartItem.getAmount() + addCartItem.getAmount());
				return cart;
			}
		}
		// 重複が無い場合追加
		cart.getCartItems().add(addCartItem);
		return cart;
	}
	
	/**
	 * カートの合計金額を取得
	 * @param cart
	 * @return int 合計金額
	 */
	@Override
	public int getTotalPrice(Cart cart) {
		int totalPrice = 0;
		for (CartItem cartItem : cart.getCartItems()) {
			totalPrice += cartItem.getAmount() * cartItem.getItem().getPrice();
		}
		return totalPrice;
	}

	/**
	 * カートアイテムの数量を変更
	 * @param cart
	 * @param itemId
	 * @param newAmount
	 * @return Cart Cartエンティティ
	 */
	@Override
	public Cart editCartItemAmount(Cart cart, int itemId, int newAmount) {
		for (CartItem cartItem : cart.getCartItems()) {
			if(cartItem.getItem().getId() == itemId) {
				// 新しい数量を設定する
				cartItem.setAmount(newAmount);
			}
		}
		return cart;
	}
	
	/**
	 * 特定のItemIdのカートアイテムを削除
	 * @param cart
	 * @param itemId
	 * @return Cart Cartエンティティ
	 */
	@Override
	public Cart deleteCartItemByItemId(Cart cart, int itemId) {
		cart.getCartItems().removeIf(cartItem -> cartItem.getItem().getId().equals(itemId));
		return cart;
	}

	/**
	 * カートの内容を全削除
	 * @param cart
	 * @return Cart Cartエンティティ
	 */
	@Override
	public Cart deleteAllCartItem(Cart cart) {
		cart.getCartItems().clear();
		return cart;
	}
}
