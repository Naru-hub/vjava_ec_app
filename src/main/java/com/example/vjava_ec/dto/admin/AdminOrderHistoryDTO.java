package com.example.vjava_ec.dto.admin;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注文履歴：DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderHistoryDTO {
	/** 注文id */
	private Integer orderId;
	/** 注文者 */
	private String userName;
	/** 購入日(作成日時) */
	private LocalDateTime createdAt;
	/** 合計金額(税込) */
	private Integer totalPrice;
	/** 注文ステータス */
	private Integer orderStatus;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
