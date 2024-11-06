package com.example.vjava_ec.form.user;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 購入情報入力Formクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForm {
	
	/** 会員id */
	private int userId;
	
	/** 宛名 */
	@NotBlank(message = "宛名を入力してください")
	private String name;
	
	/**	配達先郵便番号 */
	@Pattern(regexp = "\\d{7}", message = "郵便番号は7桁の数字で入力してください")
	private String postcode;
	
	/** 配達先住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	
	/** 配達先電話番号 */
	@Pattern(regexp = "\\d{11}", message = "電話番号は11桁の数字で入力してください")
	private String tel;
	
	/** 支払い方法 */
	@Range(min = 1, max = 3, message = "支払い方法を選択してください")
	private int payment;
}
