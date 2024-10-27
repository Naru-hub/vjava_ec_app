package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vjava_ec.entity.Character;

/*
 * 管理者：マッパーインターフェイス
 * キャラクタ関連の機能に使用
 */
@Mapper
public interface AdminCharacterMapper {
	/**
	 * 全てのキャラクタ情報を取得
	 */
	List<Character> selectAll();

	/**
	 * 指定したIDのキャラクタ情報を取得
	 */
	Character selectById(Integer id);

	/**
	 * 新規キャラクタ情報をデータベースに登録
	 */
	void insertCharacter(Character character);

	/**
	 * キャラクタ情報を更新
	 */
	void updateCharacter(Character character);
}
