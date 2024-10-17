package com.example.vjava_ec.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.dto.admin.adminItemDTO;
import com.example.vjava_ec.service.admin.AdminItemService;

import lombok.RequiredArgsConstructor;

/**
 * 商品コントローラ
 */
@Controller
@RequestMapping("/admin/item")
@RequiredArgsConstructor
public class AdminItemController {

	// 消費税率(定数)
	@Value("${TAX_RATE}")
	private double TAX_RATE;

	// DI
	private final AdminItemService adminItemService;

	/**
	 * 全商品一覧を表示するメソッド
	 * 
	 * @param model モデルオブジェクト、商品リストをビューに渡す
	 * @return String 商品リスト画面のビュー名（"admin/item/list"）
	 */
	@GetMapping("/list")
	public String showItemList(Model model) {
		// すべての商品一覧情報を取得
		List<adminItemDTO> items = adminItemService.findAllItem();

		// 各商品の税込み価格を計算し、元の商品のpriceに設定
		for (adminItemDTO item : items) {
			// 税込み価格計算してint型にキャスト
			int priceWithTax = (int) Math.round(item.getPrice() * (1 + TAX_RATE));
			// 計算した税込み価格を設定
			item.setPrice(priceWithTax);
		}

		// モデルに商品一覧情報をセット
		model.addAttribute("itemList", items);

		return "admin/item/list";
	}

	/**
	 * 商品の詳細情報を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String 商品詳細画面のビュー名（"admin/item/{id}"）
	 */
	@GetMapping("/{id}")
	public String showItemDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 商品IDに対応する商品詳細情報を取得
		adminItemDTO item = adminItemService.findByIdItem(id);

		// 対象データがあるか
		if (item != null) {
			// データがある場合
			// 税込み価格を計算してint型にキャスト
			int priceWithTax = (int) Math.round(item.getPrice() * (1 + TAX_RATE));
			// 計算した税込み価格を設定
			item.setPriceWithTax(priceWithTax);

			// モデルに格納
			model.addAttribute("item", item);

			return "admin/item/detail";
		} else {
			// 対象データがない場合
			attributes.addFlashAttribute("errorMessage", "対象データがありません");

			return "redirect:/list";
		}
	}

	/**
	 * 商品の新規登録画面を表示
	 * @param form
	 * @return String 商品新規登録画面のビュー名（"admin/item/new"）
	 */
	@GetMapping("/form")
	public String showItemRegister() {

		return "admin/item/new";
	}

	/**
	 * 商品情報編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String 商品編集画面のビュー名（"admin/item/edit"）
	 */
	@GetMapping("/edit/{id}")
	public String showEditItemDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		
		return "admin/item/edit";
	}

}
