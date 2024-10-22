package com.example.vjava_ec.service.user;

import java.util.List;

import com.example.vjava_ec.entity.Order;

/**
 * OrderServiceのInterfaceクラス
 */
public interface OrderService {
	/**
	 * 現在ログインしている会員の注文履歴Listを取得
	 * @return List<Order> OrderのList
	 */
	List<Order> selectOrderList();
	
	/**
	 * 注文IDからOrderエンティティを取得
	 * @return Order Orderエンティティ
	 */
	Order selectOrderById(int id);
}
