package com.example.vjava_ec.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * キャラクタ：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
	/** id */
	private Integer id;
	/** キャラクタ名 */
	private String name;
	/** 削除フラグ */
	private boolean isDeleted;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
