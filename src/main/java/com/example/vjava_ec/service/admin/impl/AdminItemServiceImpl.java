package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.dto.admin.adminItemDTO;
import com.example.vjava_ec.repository.admin.AdminItemMapper;
import com.example.vjava_ec.service.admin.AdminItemService;

import lombok.RequiredArgsConstructor;

/**
 * 管理者：商品の実装クラス
 * 
 * データベースから商品情報を取得・作成
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminItemServiceImpl implements AdminItemService {
	// DI
	private final AdminItemMapper adminItemMapper;

	/**
	* 全商品一覧情報取得メソッド
	* 
	* @return 全商品一覧情報
	*/
	@Override
	public List<adminItemDTO> findAllItem() {
		return adminItemMapper.selectAll();
	}
	
	/**
	 * 商品詳細情報取得メソッド
	 * 
	 * @param id 商品のid
	 * @return 商品詳細情報
	 */
	@Override
	public adminItemDTO findByIdItem(Integer id) {
		return adminItemMapper.selectById(id);
	}

	
}
