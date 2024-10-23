package com.example.vjava_ec.service.user;

import java.util.List;

import com.example.vjava_ec.entity.Order;

/**
 * OrderServiceのInterfaceクラス
 */
public interface OrderService {
	/**
	 * 現在ログインしている会員の注文履歴Listを取得
	 * @return List<Order> 注文履歴のList
	 */
	List<Order> selectOrderList();
	
	/**
	 * 注文IDからOrderエンティティを取得
	 * @param id 注文テーブルの主キー
	 * @return Order Orderエンティティ
	 */
	Order selectOrderById(int id);
}
