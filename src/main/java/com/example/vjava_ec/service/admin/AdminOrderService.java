package com.example.vjava_ec.service.admin;

import java.util.List;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.dto.admin.AdminOrderHistoryDTO;

/**
 * 管理者:注文(履歴)のサービスクラスのインターフェース定義
 */
public interface AdminOrderService {
	/**
	 * 注文(履歴)一覧情報を検索
	 */
	List<AdminOrderHistoryDTO> findAllOrder();

	/**
	 * 注文履歴詳細情報を検索
	 */
	AdminOrderDetailDTO findByIdOrderDetail(Integer id);

	/**
	 * 注文履歴の注文商品の小計額を計算して格納する
	 */
	AdminOrderDetailDTO calcSubtotalOrderItem(AdminOrderDetailDTO orderDetail);

	/**
	 * 注文履歴情報を編集
	 */
	void updateOrderDetail(AdminOrderDetailDTO orderDetail);

}
