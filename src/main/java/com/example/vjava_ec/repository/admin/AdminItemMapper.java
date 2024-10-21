package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.dto.admin.adminItemDTO;
import com.example.vjava_ec.entity.Item;

/*
 * 管理者：マッパーインターフェイス
 * 商品関連の機能に使用
 */
@Mapper
public interface AdminItemMapper {

	/**
	 * 全ての商品情報を取得
	 * @return 商品のリスト
	 */
	List<adminItemDTO> selectAll();

	/**
   	 * 商品詳細情報を取得
   	 * @return 商品の詳細情報
   	 */
   	adminItemDTO selectById(@Param("id") Integer id);
   	
   	/**
   	 * 商品を登録
   	 */
   	void insert(Item item);
   	
   	/**
	 * 商品を編集
	 */
	void update(Item item);
}
