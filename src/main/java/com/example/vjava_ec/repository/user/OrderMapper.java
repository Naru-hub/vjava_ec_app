package com.example.vjava_ec.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.Order;

/**
 * 注文：マッパーインターフェイス
 * 注文履歴情報を取得
 */
@Mapper
public interface OrderMapper {
	
	/**
	 * Order情報を保存
	 */
	void insertOrder(Order order);
	
	/**
	 * 会員Idから注文履歴Listを取得
	 */
	List<Order> selectOrderListByUserId(int userId);

	/**
	 * オーダーIdから注文履歴を取得
	 */
	Order selectOrderByOrderId(int orderId);

}
