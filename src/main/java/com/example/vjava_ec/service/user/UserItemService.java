package com.example.vjava_ec.service.user;

import java.util.List;

import com.example.vjava_ec.entity.Item;

public interface UserItemService {

    /**
     * すべての商品を取得
     * @return Itemリスト
     */
	List<Item> getAllItems();
	
}
