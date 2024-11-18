package com.example.vjava_ec.service.user;

import java.util.List;

import com.example.vjava_ec.entity.Item;

/**
 * ItemServiceのinterfaceクラス
 */
public interface ItemService {

    /**
     * すべての商品を取得
     */
	List<Item> getAllItems();
	
	/**
	 * IDから特定のItemエンティティを取得
	 */
	Item selectItemById(int id);
	
	/**
	 * 特定のIDの商品の販売ステータスを期間限定ステータスを考慮して変更する
	 */
	void updateSaleStatusById(int id);
	
    /**
     * 商品名で部分一致検索
     */
    List<Item> searchItems(String keyword);
    
    /**
     * 新着商品の取得
     */
    List<Item> selectNewItems();
}
