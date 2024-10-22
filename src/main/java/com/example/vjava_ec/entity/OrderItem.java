package com.example.vjava_ec.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文商品：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	/** id */
	private Integer id;
	/** 商品エンティティ */
	private Item item;
	/** 注文id */
	private Integer orderId;
	/** 数量 */
	private Integer amount;
	/** 購入金額（税込み） */
	private Integer purchasePrice;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
