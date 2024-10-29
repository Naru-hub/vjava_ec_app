package com.example.vjava_ec.entity;

import java.time.LocalDate;
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
	/** 紹介文 */
	private String description;
	/** 身長 */
	private Integer height;
	/** デビュー日 */
	private LocalDate debutDate;
	/** 削除フラグ */
	private boolean isDeleted;
	/** 画像パス */
	private String imagePath;
	/** 作成日時 */
	private LocalDateTime createdAt;
	/** 更新日時 */
	private LocalDateTime updatedAt;
}
