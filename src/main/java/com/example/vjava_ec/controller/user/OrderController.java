package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vjava_ec.service.user.OrderService;

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
