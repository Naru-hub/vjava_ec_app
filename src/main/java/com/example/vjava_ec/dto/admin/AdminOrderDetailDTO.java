package com.example.vjava_ec.dto.admin;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文詳細：DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderDetailDTO {
	/** 注文ID */
	private Integer orderId;
	/** 注文ステータス */
	private Integer orderStatus;
	/** 支払い方法 */
	private Integer payment;
	/** 送料 */
	private Integer postage;
	/** 合計(商品代金(税込み）+ 送料) */
	private Integer totalPrice;
	/** 注文商品 */
	private List<AdminOrderItemDTO> orderItemList;
	/** 購入日(作成日時) */
	private LocalDateTime createdAt;
	/** 更新日 */
	private LocalDateTime updatedAt;
	/** 注文者の名前 */
	private String userName;
	/** 注文者の郵便番号 */
	private String userPostcode;
	/** 注文者の住所 */
	private String userAddress;
	/** 注文者の電話番号 */
	private String userTel;
	/** 配送先の宛名 */
	private String deliveryName;
	/** 配送先の郵便番号 */
	private String deliveryPostcode;
	/** 配送先の住所 */
	private String deliveryAddress;
	/** 配送先の電話番号  */
	private String deliveryTel;

}
