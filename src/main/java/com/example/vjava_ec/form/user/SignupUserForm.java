package com.example.vjava_ec.form.user;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * 新規登録情報入力フォーム
 */
@Data
public class SignupUserForm {

    /** ユーザーの名前 */
    @NotBlank(message = "名前を入力してください")
    private String name;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "正しいメールアドレス形式で入力してください")
    private String email;

    /** パスワード */
    @Size(min = 8, message = "8桁以上でパスワードを入力してください")
    private String password;

    /** 確認用パスワード */
    @NotBlank(message = "確認用パスワードを入力してください")
    private String passwordConfirm;

    /** 郵便番号 (7桁に制限) */
    @Pattern(regexp = "\\d{7}", message = "郵便番号は7桁の数字で入力してください")
    private String postcode;

    /** 住所 */
    @NotBlank(message = "住所を入力してください")
    private String address;

    /** 電話番号 (11桁に制限) */
    @Pattern(regexp = "\\d{11}", message = "電話番号は11桁の数字で入力してください")
    private String tel;
    
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
