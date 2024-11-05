package com.example.vjava_ec.service.admin;

import java.util.List;

import com.example.vjava_ec.dto.admin.AdminItemDTO;
import com.example.vjava_ec.entity.Item;

/**
 * 管理者:商品のサービスクラスのインターフェース定義
 */
public interface AdminItemService {
	/**
	 * 商品一覧情報を検索
	 */
	List<AdminItemDTO> findAllItem();
	
	/**
	 * 商品詳細情報を検索
	 */
	AdminItemDTO findByIdItem(Integer id);
	
	/**
	 * 商品を新規作成
	 */
	void insertItem(Item item);
	
	/**
	 * 商品情報を編集
	 */
	void updateItem(Item item);
	
	/**
	 * 商品情報を削除(論理削除)
	 */
	void deleteItem(AdminItemDTO item);
}
