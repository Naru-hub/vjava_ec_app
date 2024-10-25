package com.example.vjava_ec.form.admin;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者：注文履歴編集用のFormクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderForm {
	/** ID */
	private Integer orderId;
	
	/** 注文ステータス */
	@NotNull(message = "注文ステータスを選択してください")
	private Integer orderStatus;
}
