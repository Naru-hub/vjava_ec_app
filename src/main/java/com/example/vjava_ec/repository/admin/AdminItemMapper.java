package com.example.vjava_ec.repository.admin;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.dto.admin.adminItemDTO;

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
}
