package com.example.vjava_ec.dto.admin;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品：DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminItemDTO {
	/** id */
	private Integer itemId;
	/** キャラクタid */
	private Integer characterId;
	/** 商品名 */
	private String name;
	/** 商品詳細 */
	private String detail;
	/** 商品価格（税抜き） */
	private Integer price;
	/** 商品在庫数 */
	private Integer stock;
	/** 販売ステータス */
	private Integer saleStatus;
	/** 期間限定フラグ */
	private boolean isLimited;
	/** 削除フラグ */
	private boolean isDeleted;
	/** 画像パス */
	private String imagePath;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
	/** キャラクタ名 */
	private String characterName;
	/** 税込み価格 */
	private Integer priceWithTax;
}
