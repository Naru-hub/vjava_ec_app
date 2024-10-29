package com.example.vjava_ec.service.admin;

import java.util.List;

import com.example.vjava_ec.entity.Character;

/**
 * 管理者:キャラクタのサービスクラスのインターフェース定義
 */
public interface AdminCharacterService {
	/**
	 * キャラクタ一覧情報を検索
	 */
	List<Character> findAll();

	/**
	 * キャラクタの詳細情報を検索
	 */
	Character findByIdCharacter(Integer id);

	/**
	 * 新規キャラクタを登録
	 */
	void createCharacter(Character character);

	/**
	 * キャラクタ情報を更新
	 */
	void updateCharacter(Character character);

	/**
	 * キャラクタ情報を削除(論理削除)
	 */
	void deleteCharacter(Character character);
}
