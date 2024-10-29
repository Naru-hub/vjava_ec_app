package com.example.vjava_ec.helper.admin;

import com.example.vjava_ec.entity.Character;
import com.example.vjava_ec.form.admin.AdminCharacterForm;

/**
 * 管理者：キャラクタのhelperクラス
 */
public class AdminCharacterHelper {
	/**
	 * Characterへの変換
	 * @param form
	 * @return character キャラクタ オブジェクト
	 */
	public static Character convertCharacter(AdminCharacterForm form) {
		Character character = new Character();
		character.setId(form.getId());
		character.setName(form.getName());
		character.setDescription(form.getDescription());
		character.setHeight(form.getHeight());
		character.setDebutDate(form.getDebutDate());
		// 画像のパスをセット
		character.setImagePath(form.getImagePath());
		return character;
	}

	/**
	 * CharacterFormへの変換
	 * @param character
	 * @return form (characterの値をセット)
	 */
	public static AdminCharacterForm convertCharacterForm(Character character) {
		AdminCharacterForm form = new AdminCharacterForm();
		form.setId(character.getId());
		form.setName(character.getName());
		form.setDescription(character.getDescription());
		form.setHeight(character.getHeight());
		if (character.getDebutDate() != null) {
			// LocalDateをそのままセット
	        form.setDebutDate(character.getDebutDate());  
	    }
		// 画像のパスをセット
		form.setImagePath(character.getImagePath());
			
		return form;
	}
}
