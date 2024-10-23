package com.example.vjava_ec.form.admin;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者：商品新規登録・編集用のFormクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminItemForm {

	/** ID */
	private Integer id;

	/** キャラクタid */
	@NotNull(message = "キャラクターを選択してください")
	private Integer characterId;

	/** 商品名 */
	@NotBlank(message = "商品名を入力してください")
	private String name;

	/** 商品詳細 */
	@NotBlank(message = "商品詳細を入力してください")
	private String detail;

	/** 価格(税抜) */
	@NotNull(message = "価格を入力してください")
	@Min(value = 0, message = "価格は0以上で入力してください")
	private Integer price;

	/** 在庫数 */
	@NotNull(message = "在庫数を入力してください")
	@Min(value = 0, message = "在庫数は0以上で入力してください")
	private Integer stock;

	/** 販売ステータス */
	@NotNull(message = "販売ステータスを選択してください")
	private Integer saleStatus;

	/** 期間限定ステータス */
	private boolean limited;

	/** 画像ファイル */
	private MultipartFile file;

	/** 画像のパス */
	private String imagePath;

}
