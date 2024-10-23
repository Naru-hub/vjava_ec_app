package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.Character;
import com.example.vjava_ec.repository.admin.AdminCharacterMapper;
import com.example.vjava_ec.service.admin.AdminCharacterService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminCharacterServiceImpl implements AdminCharacterService {

	// DI
	private final AdminCharacterMapper adminCharacterMapper;

	/**
	 * キャラクタ一覧情報を検索
	 */
	@Override
	public List<Character> findAll() {
		return adminCharacterMapper.selectAll();
	}
}
