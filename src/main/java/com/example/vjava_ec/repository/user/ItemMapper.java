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
	 * IdからItemエンティティを取得
	 */
	Item selectItemById(int id);
	
	/**
	 * 特定のIdの商品の在庫数を取得する
	 */
	int selectItemStockById(int id);
	
	/**
	 * 特定のIdの商品の在庫数を更新する
	 */
	int updateItemStockById(@Param("id") int id,@Param("amount") int amount);
	
	/**
	 * 在庫が切れた特定のIdの商品のステータスを更新する
	 */
	void updateSaleStatusById(Item item);
	
	/**
	 * 削除フラグがfalseの商品からkeywordに部分一致検索した商品を取得
	 */
	List<Item> findItemSearch(@Param("keyword") String keyword);
	
	/**
	 * 新着商品を8つ取得
	 */
	List<Item> findNewItems();
}
