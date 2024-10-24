package com.example.vjava_ec.service.admin.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.dto.admin.AdminOrderHistoryDTO;
import com.example.vjava_ec.repository.admin.AdminOrderMapper;
import com.example.vjava_ec.service.admin.AdminOrderService;

import lombok.RequiredArgsConstructor;

/**
 * 管理者：注文(履歴)の実装クラス
 * 
 * データベースから注文(履歴)情報を取得・作成
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

	// DI
	private final AdminOrderMapper adminOrderMapper;

	/**
	 * 全注文(履歴)一覧情報取得メソッド
	 * 
	 * @return List<AdminOrderHistoryDTO> 全注文(履歴)一覧情報
	 */
	@Override
	public List<AdminOrderHistoryDTO> findAllOrder() {
		return adminOrderMapper.selectAll();
	}

	/**
	 * 注文履歴詳細情報取得メソッド
	 * 
	 * @param id 注文のid
	 * @return 注文履歴詳細情報
	 */
	@Override
	public AdminOrderDetailDTO findByIdOrderDetail(Integer id) {
		return adminOrderMapper.selectById(id);
	}
}
