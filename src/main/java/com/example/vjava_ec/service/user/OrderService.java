package com.example.vjava_ec.service.user;

import java.util.List;

import com.example.vjava_ec.entity.Order;

/**
 * OrderServiceのInterfaceクラス
 */
public interface OrderService {
	/**
	 * 現在ログインしている会員の注文履歴Listを取得
	 */
	List<Order> selectOrderList();
	
	/**
	 * 注文IDからOrderエンティティを取得
	 */
	Order selectOrderById(int id);
}
