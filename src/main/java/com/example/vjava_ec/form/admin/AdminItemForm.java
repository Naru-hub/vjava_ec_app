package com.example.vjava_ec.form.admin;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品新規登録・編集用のFormクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class adminItemForm {

	/** ID */
	private Integer itemId;

	/** キャラクタ名 */
	@NotBlank(message = "キャラクタ名は必須項目です。")
	private String userName;

	/** 商品名 */
	@NotBlank(message = "商品名は必須項目です。")
	private String name;

	/** 商品詳細 */
	@NotBlank(message = "商品詳細は必須項目です。")
	private String detail;

	/** 価格(税抜) */
	private Integer price;

	/** 在庫数 */
	private Integer stock;

	/** 販売ステータス */
	private Integer saleStatus;

	/** 期間限定ステータス */
	private boolean isLimited;

	/** 画像ファイル */
	private MultipartFile file;

	/** 画像のパス */
	private String imagePath;

}
