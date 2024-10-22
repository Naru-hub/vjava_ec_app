package com.example.vjava_ec.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.repository.user.ItemMapper;
import com.example.vjava_ec.service.user.TestItemService;

import lombok.RequiredArgsConstructor;

/**
 * 商品情報を操作するサービスの実装クラス
 * {@link TestItemService} 商品の取得処理を提供
 */

 @Service
 @RequiredArgsConstructor
public class TestItemServiceImpl implements TestItemService{
	
	 //DI
    private final ItemMapper itemMapper;
    
 // 消費税率(定数)
 	@Value("${TAX_RATE}")
 	private double TAX_RATE;
 	
    /**
     * 商品一覧を取得し、消費税込みの価格に変換
     * 
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
    	
        // 取得した商品情報をコンソールに表示（デバック用）
        for (Item item : items) {
            System.out.println(item);
        }
    	
        return items;
    }

}
