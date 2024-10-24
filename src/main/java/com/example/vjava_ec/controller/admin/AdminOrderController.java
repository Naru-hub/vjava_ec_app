package com.example.vjava_ec.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.dto.admin.AdminOrderHistoryDTO;
import com.example.vjava_ec.dto.admin.AdminOrderItemDTO;
import com.example.vjava_ec.service.admin.AdminOrderService;

import lombok.RequiredArgsConstructor;

/**
 * 注文コントローラ
 */
@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {

	// DI
	private final AdminOrderService adminOrderService;

	/**
	 * 注文履歴(すべて)の情報を取得し、注文履歴一覧画面を表示
	 * 
	 * @param model モデルオブジェクト、注文履歴一覧をビューに渡す
	 * @return String 注文履歴一覧画面のビュー名（"admin/order/list"）
	 */
	@GetMapping("/list")
	public String showOrderList(Model model) {
		// すべての注文履歴一覧情報を取得
		List<AdminOrderHistoryDTO> orderHistories = adminOrderService.findAllOrder();

		// モデルに商品一覧情報をセット
		model.addAttribute("orderList", orderHistories);
		// 注文履歴一覧画面へ遷移
		return "admin/order/list";
	}

	/**
	 * 注文履歴の詳細情報を取得し、注文履歴詳細画面を表示
	 * @param id 注文ID
	 * @param model モデルオブジェクト、注文履歴の詳細情報をビューに渡す
	 * @param attributes
	 * @return String 注文履歴詳細画面のビュー名（"admin/order/{id}"）
	 */
	@GetMapping("/{id}")
	public String showItemDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 注文IDに対応する注文履歴詳細情報を取得
		AdminOrderDetailDTO orderDetailInfo = adminOrderService.findByIdOrderDetail(id);

		// 対象データがあるか
		if (orderDetailInfo != null) {
			// データがある場合
			// 注文の注文商品のリストを取得
			List<AdminOrderItemDTO> orderItemList = orderDetailInfo.getOrderItemList();
			// 各注文商品の小計を計算
			for (AdminOrderItemDTO orderItem : orderItemList) {
				// 商品の購入価格 * 商品の個数 
				int subTotal = orderItem.getPurchasePrice() * orderItem.getAmount();
				// 小計価格を設定
				orderItem.setSubtotalPrice(subTotal);
			}

			// モデルに格納
			model.addAttribute("orderDetail", orderDetailInfo);
			System.out.println(orderDetailInfo);

			// 注文履歴詳細画面へ遷移
			return "admin/order/detail";
		} else {
			// 対象データがない場合
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 注文履歴一覧画面へリダイレクト
			return "redirect:/admin/order/list";
		}
	}
}
