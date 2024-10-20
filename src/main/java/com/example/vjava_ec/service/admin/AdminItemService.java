package com.example.vjava_ec.service.admin;

import java.util.List;

import com.example.vjava_ec.dto.admin.adminItemDTO;

/**
 * 管理者側の商品のサービスクラスのインターフェース定義
 */
public interface AdminItemService {
	/**
	 * 商品一覧情報を検索
	 */
	List<adminItemDTO> findAllItem();
	
	/**
	 * 商品詳細情報を検索
	 */
	adminItemDTO findByIdItem(Integer id);
}
