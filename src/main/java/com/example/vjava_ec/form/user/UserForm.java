
package com.example.vjava_ec.form.user;

import jakarta.validation.constraints.NotBlank;
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
	
	@NotBlank(message = "名前は必須です")
	private String name;
	
	@NotBlank(message = "メールアドレスは必須です")
	private String email;

	@NotBlank(message = "郵便番号は必須です")
	private String postcode;
	
	@NotBlank(message = "住所は必須です")
	private String address;
	
	@NotBlank(message = "電話番号は必須です")
	private String tel;

}
