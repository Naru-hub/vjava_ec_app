package com.example.vjava_ec.service.user.impl;

import java.text.NumberFormat;
import java.util.Locale;

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
	 * カートアイテムの数量を変更、及び小計の更新
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
				// 数量変更に伴う表示小計の変更
				cartItem.setDisplayTotalPrice(NumberFormat.getNumberInstance(Locale.JAPAN).format(cartItem.getItem().getPrice() * cartItem.getAmount()));
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

	/**
	 * 表示用の合計金額を取得
	 * @param cart
	 * @return String 合計金額
	 */
	@Override
	public String getDisplayTotalPrice(Cart cart) {
		int totalPrice = getTotalPrice(cart);
		String displayTotalPrice = NumberFormat.getNumberInstance(Locale.JAPAN).format(totalPrice);
		return displayTotalPrice;
	}
}
