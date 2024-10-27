package com.example.vjava_ec.form.admin;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理者：キャラクタ新規登録・編集用のFormクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminCharacterForm {

	/** ID */
	private Integer id;

	/** キャラクタ名 */
	@NotBlank(message = "キャラクタ名を入力してください")
	private String name;
	
	/** 紹介文 */
	@NotBlank(message = "紹介文を入力してください")
	private String description;

	/** 身長 */
	@NotNull(message = "身長を入力してください")
	@Positive(message = "身長は1cm以上で入力してください") 
	private Integer height;

	/** デビュー日 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "デビュー日を入力してください")
	@Past(message = "デビュー日は過去の日付で入力してください") 
	private LocalDate debutDate;

	/** 画像ファイル */
	private MultipartFile file;

	/** 画像のパス */
	private String imagePath;
}
