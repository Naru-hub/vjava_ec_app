package com.example.vjava_ec.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.repository.user.ItemMapper;
import com.example.vjava_ec.service.user.ItemService;

import lombok.RequiredArgsConstructor;

/**
 * 商品情報を操作するサービスの実装クラス
 * {@link ItemService} 商品の取得処理を提供
 */
 @Service
 @Transactional
 @RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
	
	// DI
    private final ItemMapper itemMapper;
    
    // 消費税率(定数)
 	@Value("${TAX_RATE}")
 	private double TAX_RATE;
 	
    /**
     * 商品一覧を取得し、消費税込みの価格に変換 
     * @return items
     */
    @Override
    public List<Item> getAllItems() {
    	 // 商品一覧を取得
    	List<Item> items = itemMapper.selectAll();
    	// 各商品の価格を消費税込みに計算し直す
    	for(Item item : items) {
    		int price = (int)(item.getPrice() * (1 + TAX_RATE));
    		item.setPrice(price);
    	}
        return items;
    }
    
    /**
     * 商品名を部分一致検索
     * @return keywordに部分一致した商品
     */
    public List<Item> searchItems(String keyword) {
        return itemMapper.findItemSearching(keyword);
    }
}

