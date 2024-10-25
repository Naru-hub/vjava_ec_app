package com.example.vjava_ec.helper.admin;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.form.admin.AdminOrderForm;

/**
 * 管理者：注文のhelperクラス
 */
public class AdminOrderHelper {
	/**
	 * orderDetailへの変換
	 * @param form
	 * @return orderDetail 注文履歴 オブジェクト
	 */
	public static AdminOrderDetailDTO convertOrderDetail(AdminOrderForm form) {
		AdminOrderDetailDTO orderDetail = new AdminOrderDetailDTO();
		
		orderDetail.setOrderId(form.getOrderId());
		orderDetail.setOrderStatus(form.getOrderStatus());
		
		return orderDetail;
	}
	
	/**
	 * orderFormへの変換
	 * @param orderDetail 注文履歴 オブジェクト
	 * @return form (orderDetailの値をセット)
	 */
	public static AdminOrderForm convertOrderForm(AdminOrderDetailDTO orderDetail) {
		AdminOrderForm form = new AdminOrderForm();
		
		form.setOrderId(orderDetail.getOrderId());
		form.setOrderStatus(orderDetail.getOrderStatus());
		
		return form;
	}
}
