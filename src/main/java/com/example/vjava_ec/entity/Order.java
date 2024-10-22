package com.example.vjava_ec.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	/** id */
	private Integer id;
	/** 会員id */
	private Integer userId;
	/** 送料 */
	private Integer postage;
	/** 合計金額 */
	private Integer totalPrice;
	/** 支払い方法 */
	private Integer payment;
	/** 配達先の宛名 */
	private String deliveryName;
	/** 配達先の郵便番号 */
	private String deliveryPostcode;
	/** 配達先の住所 */
	private String deliveryAddress;
	/** 配達先の電話番号 */
	private String deliveryTel;
	/** 注文ステータス */
	private Integer orderStatus;
	/** 注文ステータス */
	private List<OrderItem> orderItems;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
