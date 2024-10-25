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
     * 商品名で部分一致検索
     */
    List<Item> searchItems(String keyword);
	
}
