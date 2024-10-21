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
	 * @return キャラクタ情報のリスト
	 */
	List<Character> selectAll();
}
