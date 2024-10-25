package com.example.vjava_ec.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vjava_ec.entity.Item;

/**
 * 商品マッパーインターフェース
 * 商品一覧情報を取得
 */
@Mapper
public interface ItemMapper {
	/**
	 * 削除フラグがfalseの商品のみ取得
	 */
	List<Item> selectAll();
	
	/**
	 * 削除フラグがfalseの商品からkeywordに部分一致検索した商品を取得
	 */
	List<Item> findItemSearching(@Param("keyword") String keyword);

}
