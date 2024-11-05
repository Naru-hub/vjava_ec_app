package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.entity.Cart;
import com.example.vjava_ec.entity.Order;
import com.example.vjava_ec.exception.user.InsufficientStockException;
import com.example.vjava_ec.form.user.OrderForm;
import com.example.vjava_ec.helper.user.OrderHelper;
import com.example.vjava_ec.service.user.CartService;
import com.example.vjava_ec.service.user.EmailService;
import com.example.vjava_ec.service.user.OrderService;
import com.example.vjava_ec.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 注文コントローラ
 */
@Controller("userOrder")
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class OrderController {
	
	// DI
	private final OrderService orderService;
	private final UserService userService;
	private final CartService cartService;
	private final EmailService emailService;
	
	/**
	 * 購入情報入力画面表示
	 * @param form
	 * @param model
	 * @return "user/order/form" 購入情報入力画面
	 */
	@GetMapping("/form")
	public String showOrderForm(@ModelAttribute OrderForm form, Model model) {
		model.addAttribute("form", form);
		return "user/order/form";
	}
	
	/**
	 * 入力情報確認エンドポイント
	 * @param form
	 * @param bindingResult
	 * @param attributes
	 * @return redirect:/user/order/confirm 購入情報確認画面へリダイレクト
	 */
	@PostMapping("/form")
	public String validatedOrderForm(@Validated @ModelAttribute("form") OrderForm form, BindingResult bindingResult, RedirectAttributes attributes, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("errors", OrderHelper.getErrorMessages(bindingResult));
			model.addAttribute("form", form);
			return "user/order/form";
		}
		// formにuserIdを設定
		form.setUserId(userService.selectLoginUser().getId());
		attributes.addFlashAttribute("form", form);
		return "redirect:/user/order/confirm";
	}
	
	/**
	 * 購入情報確認画面表示
	 * @param form
	 * @param session
	 * @param model
	 * @return user/order/confirm 購入情報確認画面
	 */
	@GetMapping("/confirm")
	public String showOrderConfirm(@ModelAttribute("form") OrderForm form, HttpSession session, Model model) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			return "redirect:/user/cart/show";
		}
		// formとcartから
		Order order = OrderHelper.convertOrder(form, cart, cartService.getTotalPrice(cart));
		// 注文情報をsessionに保存
		session.setAttribute("order", order);
		// Modelに情報を渡す
		model.addAttribute("order", order);
		model.addAttribute("from", form);
		return "user/order/confirm";
	}
	
	/**
	 * 購入実行エンドポイント
	 * @param session
	 * @param attributes
	 * @return redirect:/user/order/completed 購入完了画面へリダイレクト
	 */
	@PostMapping("/confirmed")
	public String orderConfirmed(HttpSession session, RedirectAttributes attributes) {
		// sessionから注文情報を取得
		Order order = (Order)session.getAttribute("order");
		if (order == null) {
			return "redirect:/user/item/list";
		}
		// 注文情報の保存
		try {
			orderService.insertOrder(order);
		} catch(InsufficientStockException e) {
			// 在庫数が足りなかった時
			attributes.addFlashAttribute("insufficientStock", e.getErrorMessage());
			return "redirect:/user/cart/show";
		}
		// Thanksメールの送信
		emailService.sendThanksEmail(order, userService.selectLoginUser());
		// sessionから注文情報の削除
		session.removeAttribute("order");
		// カートの内容全削除
		Cart cart = (Cart) session.getAttribute("cart");
		cartService.deleteAllCartItem(cart);
		return "redirect:/user/order/completed";
	}
	
	/**
	 * 購入完了画面表示
	 * @return "user/order/completed"
	 */
	@GetMapping("/completed")
	public String orderCompleted() {
		return "user/order/completed";
	}
	
	/**
	 * 注文履歴一覧画面表示
	 * @param model
	 * @return "user/order/list" 注文履歴一覧画面
	 */
	@GetMapping("/list")
	public String showOrderList(Model model) {
		model.addAttribute("orderList", orderService.selectOrderList());
		return "user/order/list";
	}
	
	/**
	 * 注文履歴詳細画面表示
	 * @param id 注文テーブルの主キー
	 * @param model
	 * @return "user/order/detail" 注文詳細画面
	 */
	@GetMapping("/{id}")
	public String showOrderDetail(@PathVariable Integer id,Model model) {
		model.addAttribute("order", orderService.selectOrderById(id));
		return "user/order/detail";
	}
	

}
