package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.Character;
import com.example.vjava_ec.repository.admin.AdminCharacterMapper;
import com.example.vjava_ec.service.admin.AdminCharacterService;

import lombok.RequiredArgsConstructor;

/**
 * 管理者：キャラクタの実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminCharacterServiceImpl implements AdminCharacterService {

	// DI
	private final AdminCharacterMapper adminCharacterMapper;

	/**
	 * キャラクタ一覧情報を検索
	 * @return List<Character> キャラクタ一覧情報のリスト
	 */
	@Override
	public List<Character> findAll() {
		return adminCharacterMapper.selectAll();
	}

	/**
	 * キャラクタの詳細情報を検索
	 * @param id
	 * @return Character キャラクタの詳細情報
	 */
	@Override
	public Character findByIdCharacter(Integer id) {
		return adminCharacterMapper.selectById(id);
	}
}