package com.example.vjava_ec.helper.admin;

import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.form.admin.AdminItemForm;

/**
 * 管理者：商品のhelperクラス
 */
public class AdminItemHelper {
	/**
	 * Postへの変換
	 * @param form
	 * @return item 商品 オブジェクト
	 */
	public static Item convertPost(AdminItemForm form) {
		Item item = new Item();
		item.setCharacterId(form.getCharacterId());
		item.setName(form.getName());
		item.setDetail(form.getDetail());
		item.setPrice(form.getPrice());
		item.setStock(form.getStock());
		item.setSaleStatus(form.getSaleStatus());
		item.setLimited(form.isLimited());
		/** 画像のパスをセット */
		item.setImagePath(form.getImagePath());
		return item;
	}
}
