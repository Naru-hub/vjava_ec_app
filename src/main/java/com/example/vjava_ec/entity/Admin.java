package com.example.vjava_ec.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	/** id */
	private Integer id;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 権限 */
	private Role role;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
