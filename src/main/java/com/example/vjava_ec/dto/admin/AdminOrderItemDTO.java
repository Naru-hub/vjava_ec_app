package com.example.vjava_ec.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文商品：DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderItemDTO {
	/** 商品名 */
	private String itemName;
	/** 商品画像 */
	private String imagePath;
	/** 価格(税込) */
	private Integer purchasePrice;
	/** 個数 */
	private Integer amount;
	/** 小計 */
	private Integer subtotalPrice;
}
