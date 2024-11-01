package com.example.vjava_ec.helper.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.example.vjava_ec.entity.Cart;
import com.example.vjava_ec.entity.CartItem;
import com.example.vjava_ec.entity.Order;
import com.example.vjava_ec.entity.OrderItem;
import com.example.vjava_ec.form.user.OrderForm;

/**
 * 会員側OrderControllerのHelperクラス
 */
public class OrderHelper {
	
	/**
	 * OrderFormとCartとカートのTotalPriceからOrderエンティティに変換
	 * @param form
	 * @param cart
	 * @param totalPrice
	 * @return Order Orderエンティティ
	 */
	public static Order convertOrder(OrderForm form, Cart cart, int totalPrice) {
		Order order = new Order();
		order.setUserId(form.getUserId());
		order.setPostage(800);
		order.setTotalPrice(order.getPostage() + totalPrice);
		order.setPayment(form.getPayment());
		order.setDeliveryName(form.getName());
		order.setDeliveryPostcode(form.getPostcode());
		order.setDeliveryAddress(form.getAddress());
		order.setDeliveryTel(form.getTel());
		order.setOrderStatus(1);
		order.setOrderItems(convertOrderItem(cart));
		return order;
	}
	
	/**
	 * CartのCartItemリストからOrderItemリストに変換
	 * @param cart
	 * @return List<OrderItem> OrderItemのList
	 */
	public static List<OrderItem> convertOrderItem(Cart cart){
		List<OrderItem> orderItems = new ArrayList<>();
		List<CartItem> cartItems = cart.getCartItems();
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItem(cartItem.getItem());
			orderItem.setAmount(cartItem.getAmount());
			orderItem.setPurchasePrice(cartItem.getItem().getPrice());
			orderItems.add(orderItem);
		}
		return orderItems;
	}
	
	/**
	 * エラーメッセージの取得
	 * @param result
	 * @return List<String> エラーメッセージが入っているList
	 */
	public static List<String> getErrorMessages(BindingResult result){
		List<String> messages = new ArrayList<>();
		for (ObjectError error : result.getAllErrors()) {
			messages.add(error.getDefaultMessage());
		}
		return messages;
	}
}
