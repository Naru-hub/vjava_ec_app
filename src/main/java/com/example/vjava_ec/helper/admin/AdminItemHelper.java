package com.example.vjava_ec.helper.admin;

import com.example.vjava_ec.dto.admin.adminItemDTO;
import com.example.vjava_ec.entity.Item;
import com.example.vjava_ec.form.admin.AdminItemForm;

/**
 * 管理者：商品のhelperクラス
 */
public class AdminItemHelper {
	/**
	 * Itemへの変換
	 * @param form
	 * @return item 商品 オブジェクト
	 */
	public static Item convertItem(AdminItemForm form) {
		Item item = new Item();
		item.setId(form.getId());
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
	
	/**
	 * ItemFormへの変換
	 * @param item
	 * @return form (itemの値をセット)
	 */
	public static AdminItemForm convertItemForm(adminItemDTO item) {
		AdminItemForm form = new AdminItemForm();
		form.setId(item.getItemId());
		form.setCharacterId(item.getCharacterId());
		form.setName(item.getName());
		form.setDetail(item.getDetail());
		form.setPrice(item.getPrice());
		form.setStock(item.getStock());
		form.setSaleStatus(item.getSaleStatus());
		form.setLimited(item.isLimited());
		/** 画像のパスをセット */
		form.setImagePath(item.getImagePath());
		return form;
	}
}
