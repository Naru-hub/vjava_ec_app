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
}
