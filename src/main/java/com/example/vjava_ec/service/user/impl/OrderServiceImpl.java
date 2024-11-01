package com.example.vjava_ec.service.user.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.Order;
import com.example.vjava_ec.entity.OrderItem;
import com.example.vjava_ec.exception.user.InsufficientStockException;
import com.example.vjava_ec.repository.user.ItemMapper;
import com.example.vjava_ec.repository.user.OrderItemMapper;
import com.example.vjava_ec.repository.user.OrderMapper;
import com.example.vjava_ec.repository.user.UserMapper;
import com.example.vjava_ec.service.user.ItemService;
import com.example.vjava_ec.service.user.OrderService;

import lombok.RequiredArgsConstructor;

/**
 * OrderServiceインターフェースの実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	// DI
	private final OrderMapper orderMapper;
	private final UserMapper userMapper;
	private final OrderItemMapper orderItemMapper;
	private final ItemMapper itemMapper;
	private final ItemService itemService;
	
	/**
	 * 注文情報の保存
	 * @param order
	 */
	@Override
	public void insertOrder(Order order) {
		// 注文商品をひとつづつ処理
		for (OrderItem orderItem : order.getOrderItems()) {
			int itemId = orderItem.getItem().getId();
			int stock = itemMapper.selectItemStockById(itemId);
			int amount = orderItem.getAmount();
			// 在庫数が足りなかった時
			if (stock < amount) {
				// カスタムエラーをスロー
				throw new InsufficientStockException(itemMapper.selectItemById(itemId),stock);
			}
			// 在庫数の更新
			itemMapper.updateItemStockById(itemId, stock - amount);
			if(itemMapper.selectItemStockById(itemId) == 0) {
				// 在庫が無くなった時商品のステータスを変更
				itemService.updateSaleStatusById(itemId);
			}
		}
		orderMapper.insertOrder(order);
		// orderIdの取得
		int orderId = order.getId();
		List<OrderItem> orderItems = order.getOrderItems();
		// orderIdのセット
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
		}
		orderItemMapper.insertItem(orderItems);
	}
	
	/**
	 * ログインしている会員の注文履歴Listを取得
	 * @return List<Order> 注文履歴List
	 */
	@Override
	public List<Order> selectOrderList() {
		// ログインしているユーザのId特定
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		int userId = userMapper.selectUserByEmail(email).getId();
		// 注文履歴の取得
		List<Order> list = orderMapper.selectOrderListByUserId(userId);
		return list;
	}
	 /**
	  * IDから特定の注文履歴を取得
	  * @param id OrderId
	  * @return Order Orderエンティティ
	  */
	@Override
	public Order selectOrderById(int id) {
		Order order = orderMapper.selectOrderByOrderId(id);
		return order;
	}
}
