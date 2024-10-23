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
	 * 会員Idから注文履歴Listを取得
	 * @param userId　会員Id
	 * @return List<Order> 注文履歴一覧
	 */
	List<Order> selectOrderListByUserId(int userId);
	
	/**
	 * オーダーIdから注文履歴を取得
	 * @param 取得したい注文のorderId
	 * @return Order Orderエンティティ
	 */
	Order selectOrderByOrderId(int orderId);
	

}
