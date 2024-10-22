
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
	
	private int userId;
	
	@NotBlank(message = "名前を入力してください")
	private String name;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しいメールアドレス形式で入力してください")
	private String email;

	@Pattern(regexp = "\\d{7}", message = "郵便番号は7桁の数字で入力してください")
	private String postcode;
	
	@NotBlank(message = "住所を入力してください")
	private String address;
	
    @Pattern(regexp = "\\d{11}", message = "電話番号は11桁の数字で入力してください")
	private String tel;

}
