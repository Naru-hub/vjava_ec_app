
package com.example.vjava_ec.form.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会員情報編集用Formクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	/** 会員id */
	private int userId;
	/** 会員名 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しいメールアドレス形式で入力してください")
	private String email;
	/** 郵便番号 */
	@Pattern(regexp = "\\d{7}", message = "郵便番号は7桁の数字で入力してください")
	private String postcode;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
    @Pattern(regexp = "\\d{11}", message = "電話番号は11桁の数字で入力してください")
	private String tel;

}
