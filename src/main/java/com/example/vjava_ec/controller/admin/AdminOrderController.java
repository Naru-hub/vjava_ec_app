package com.example.vjava_ec.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.dto.admin.AdminOrderDetailDTO;
import com.example.vjava_ec.dto.admin.AdminOrderHistoryDTO;
import com.example.vjava_ec.form.admin.AdminOrderForm;
import com.example.vjava_ec.helper.admin.AdminOrderHelper;
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
			// 小計額を計算して注文履歴詳細に格納
			AdminOrderDetailDTO orderDetail = adminOrderService.calcSubtotalOrderItem(orderDetailInfo);

			// モデルに格納
			model.addAttribute("orderDetail", orderDetail);

			// 注文履歴詳細画面へ遷移
			return "admin/order/detail";
		} else {
			// 対象データがない場合
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 注文履歴一覧画面へリダイレクト
			return "redirect:/admin/order/list";
		}
	}

	/**
	 * 注文履歴編集画面を表示
	 * @param id
	 * @param model
	 * @param attributes
	 * @return String 注文履歴編集画面のビュー名（"admin/order/edit"）
	 */
	@GetMapping("/edit/{id}")
	public String showEditOrderDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// IDに対応する注文詳細情報を取得
		AdminOrderDetailDTO target = adminOrderService.findByIdOrderDetail(id);

		// 編集対象の注文詳細情報が取得できた場合
		if (target != null) {
			// 小計額を計算して注文履歴詳細に格納(表示させるデータ用)
			AdminOrderDetailDTO orderDetail = adminOrderService.calcSubtotalOrderItem(target);
			// Formに変換、編集画面に遷移
			AdminOrderForm form = AdminOrderHelper.convertOrderForm(target);
			// モデルに格納
			model.addAttribute("adminOrderForm", form);
			model.addAttribute("orderDetail", orderDetail);

			// 注文履歴編集画面へ遷移
			return "admin/order/edit";
		} else {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 注文履歴詳細画面へリダイレクト
			return "redirect:/admin/order/" + id;
		}
	}

	/**
	 * 注文履歴情報を編集する
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return String 注文履歴詳細画面のビュー名（"admin/order/{id}"）
	 */
	@PostMapping("/update")
	public String updateOrder(@Validated AdminOrderForm form,
			BindingResult bindingResult,
			RedirectAttributes attributes, Model model) {
		// === バリデーションチェック ===
		// バリデーションエラーがある場合
		if (bindingResult.hasErrors()) {
			// 注文履歴編集画面へ遷移
			return "admin/order/edit";
		}

		// 元の注文履歴詳細情報の取得
		AdminOrderDetailDTO existingOrderDetail = adminOrderService.findByIdOrderDetail(form.getOrderId());

		// 注文履歴詳細情報が見つからない場合
		if (existingOrderDetail == null) {
			// 対象データがない場合はフラッシュメッセージを表示
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 注文履歴詳細画面へリダイレクト
			return "redirect:/admin/order/" + form.getOrderId();
		}

		// エンティティへの変換
		AdminOrderDetailDTO orderDetail = AdminOrderHelper.convertOrderDetail(form);
		// 更新処理
		adminOrderService.updateOrderDetail(orderDetail);

		// フラッシュメッセージ
		attributes.addFlashAttribute("message", "注文履歴詳細情報が更新されました");
		// 注文履歴詳細画面へリダイレクト
		return "redirect:/admin/order/" + existingOrderDetail.getOrderId();
	}

	/**
	 * 注文一覧検索ツール
	 * @param search 注文IDまたは注文者名
	 * @param model
	 * @return admin/order/list 注文検索結果一覧画面
	 */
	@GetMapping("/search")
	public String searchOrder(@RequestParam(required = false) String search, Model model) {
		List<AdminOrderHistoryDTO> searchResults;
		// 検索ワードが指定されていない場合、全注文一覧を表示
		if (search == null || search.isEmpty()) {
			searchResults = adminOrderService.findAllOrder();
		} else {
			// 検索ワードがある場合は検索を実行
			Integer orderId = null;
			String userName = null;
			
			// 入力された検索ワードが数字の場合は注文IDとして扱う
			try {
				orderId = Integer.valueOf(search);
				// 注文IDで検索
				searchResults = adminOrderService.findSearchOrderId(orderId);
			} catch (NumberFormatException e) {
				// 数字でない場合は注文者名として扱う
				userName = search;
				// 注文者名で検索
				searchResults = adminOrderService.findSearchUserName(userName);
			}
			// 検索結果が空の場合、メッセージを設定
			if (searchResults.isEmpty()) {
				model.addAttribute("message", "みつかりませんでした");
			}
		}
		model.addAttribute("orderList", searchResults);
		return "admin/order/list";
	}
}
