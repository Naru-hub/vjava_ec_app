package com.example.vjava_ec.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

/**
 * 商品コントローラ
 */
@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class AdminItem {
	
	/**
     * 商品リストを表示するためのメソッド。
     * 
     * <p>GETリクエスト "/admin/item/list" を受け取り、"admin/items" テンプレートを返します。
     * このテンプレートは、管理者用の商品リストページを表示します。</p>
     * 
     * @return 商品リスト画面のビュー名（"admin/items"）
     */
	@GetMapping("/list")
	public String showItemList() {
		return "admin/items";
	}

}
