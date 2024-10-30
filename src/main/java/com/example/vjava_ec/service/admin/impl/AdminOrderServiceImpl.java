package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.dto.admin.AdminOrderHistoryDTO;
import com.example.vjava_ec.dto.admin.AdminOrderItemDTO;
import com.example.vjava_ec.repository.admin.AdminOrderMapper;
import com.example.vjava_ec.service.admin.AdminOrderService;

import lombok.RequiredArgsConstructor;

/**
 * 管理者：注文(履歴)の実装クラス
 * 
 * データベースから注文(履歴)情報を取得・作成
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

	// DI
	private final AdminOrderMapper adminOrderMapper;

	/**
	 * 全注文(履歴)一覧情報取得メソッド
	 * 
	 * @return List<AdminOrderHistoryDTO> 全注文(履歴)一覧情報
	 */
	@Override
	public List<AdminOrderHistoryDTO> findAllOrder() {
		return adminOrderMapper.selectAll();
	}

	/**
	 * 注文履歴詳細情報取得メソッド
	 * 
	 * @param id 注文のid
	 * @return 注文履歴詳細情報
	 */
	@Override
	public AdminOrderDetailDTO findByIdOrderDetail(Integer id) {
		return adminOrderMapper.selectById(id);
	}

	/**
	 * 注文商品の小計を求めるメソッド
	 * 
	 * @param orderDetail 注文履歴詳細のオブジェクト
	 * @return 注文履歴詳細のオブジェクト
	 */
	@Override
	public AdminOrderDetailDTO calcSubtotalOrderItem(AdminOrderDetailDTO orderDetail) {
		// 注文の注文商品のリストを取得
		List<AdminOrderItemDTO> orderItemList = orderDetail.getOrderItemList();
		// 各注文商品の小計を計算
		for (AdminOrderItemDTO orderItem : orderItemList) {
			// 商品の購入価格 * 商品の個数 
			int subTotal = orderItem.getPurchasePrice() * orderItem.getAmount();
			// 小計価格を設定
			orderItem.setSubtotalPrice(subTotal);
		}

		// 各注文商品の小計額を格納したオブジェクトを返す
		return orderDetail;
	};

	/**
	 * 注文履歴詳細情報編集メソッド
	 * 
	 * @param orderDetail 注文履歴詳細情報
	 */
	@Override
	public void updateOrderDetail(AdminOrderDetailDTO orderDetail) {
		adminOrderMapper.update(orderDetail);
	}
	
	/**
	 * 注文IDで検索するメソッド
	 * 
	 * @param orderId 注文ID
	 * @return 注文IDで検索した情報
	 */
    @Override
    public List<AdminOrderHistoryDTO> findSearchOrderId(Integer orderId) {
        return adminOrderMapper.findByOrderId(orderId);
    }
    
    /**
     * 注文者名で検索するメソッド
     * 
     * @param userName 注文者名
	 * @return 注文者名で検索した情報
     */
    @Override
    public List<AdminOrderHistoryDTO> findSearchUserName(String userName) {
        return adminOrderMapper.findByUserName(userName);
    }
}
