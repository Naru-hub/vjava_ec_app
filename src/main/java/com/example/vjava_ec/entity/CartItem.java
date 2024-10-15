package com.example.vjava_ec.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * カートアイテム：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	/** id */
	private Integer id;
	/** 会員id */
	private Integer userId;
	/** 商品id */
	private Integer itemId;
	/** 商品の数量 */
	private Integer amount;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
