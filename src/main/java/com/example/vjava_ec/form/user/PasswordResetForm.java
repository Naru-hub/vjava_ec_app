package com.example.vjava_ec.form.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 再設定パスワード入力フォーム
 */
@Data
public class PasswordResetForm {

	/** 新しいパスワード */
	@Size(min = 8, message = "8桁以上でパスワードを入力してください")
	private String password;
	
	/** 確認用パスワード */
    @NotBlank(message = "確認用パスワードを入力してください")
    private String passwordConfirm;
}
