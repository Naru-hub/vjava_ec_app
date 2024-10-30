package com.example.vjava_ec.entity;

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
	/** 商品エンティティ */
	private Item item;
	/** 商品の数量 */
	private Integer amount;
}
