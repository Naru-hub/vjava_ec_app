package com.example.vjava_ec.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 会員：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	/** id */
	private Integer id;
	/** ユーザーの名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 郵便番号 */
	private String postcode;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String tel;
	/** 退会フラグ */
	private boolean isDeleted;
	/** 権限 */
	private Role role;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
