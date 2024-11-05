package com.example.vjava_ec.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.entity.User;
import com.example.vjava_ec.service.admin.AdminUserService;

import lombok.RequiredArgsConstructor;

/**
 * 会員コントローラ
 */
@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
	// DI
	private final AdminUserService adminUserService;

	/**
	 * 会員(すべて)の情報を取得し、会員一覧画面を表示
	 * 
	 * @param model モデルオブジェクト、会員情報一覧をビューに渡す
	 * @return String 会員一覧画面のビュー名（"admin/user/list"）
	 */
	@GetMapping("/list")
	public String showUserList(Model model) {
		// すべての会員一覧情報を取得
		List<User> userList = adminUserService.findAllUser();
		// モデルに会員一覧情報をセット
		model.addAttribute("userList", userList);
		// 会員一覧画面へ遷移
		return "admin/user/list";
	}

	/**
	 * 会員の詳細情報を取得し、会員詳細画面を表示
	 * @param id
	 * @param model モデルオブジェクト、会員の詳細情報をビューに渡す
	 * @param attributes
	 * @return String 会員詳細画面のビュー名（"admin/user/detail"）
	 */
	@GetMapping("/{id}")
	public String showUserDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 会員IDに対応する会員詳細情報を取得
		User user = adminUserService.findByIdUser(id);
		// 会員情報が存在するか
		if (user != null) {
			// モデルに格納
			model.addAttribute("user", user);
			// 会員詳細画面へ遷移
			return "admin/user/detail";
		} else {
			// フラッシュメッセージの設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 会員詳細画面へリダイレクト
			return "redirect:/admin/user/" + id;
		}
	}

	/**
	 * 会員情報編集画面を表示
	 * @param id
	 * @param model モデルオブジェクト、会員の詳細情報をビューに渡す
	 * @param attributes
	 * @return String 会員ステータス変更確認画面のビュー名（"admin/user/edit"）
	 */
	@GetMapping("/edit/{id}")
	public String showEditUserDetail(@PathVariable Integer id, Model model, RedirectAttributes attributes) {
		// 会員IDに対応する会員情報編集画面を取得
		User editUser = adminUserService.findByIdUser(id);
		// 会員情報が存在するか
		if (editUser != null) {
			// モデルに格納
			model.addAttribute("user", editUser);
			// 会員ステータス変更確認画面へ遷移
			return "admin/user/edit";
		} else {
			// フラッシュメッセージの設定
			attributes.addFlashAttribute("errorMessage", "対象データがありません");
			// 会員詳細画面へリダイレクト
			return "redirect:/admin/user/" + id;
		}
	}

	/**
	 * 会員ステータスを編集する
	 * @param id
	 * @param attributes
	 * @return String 会員詳細画面のビュー名（"admin/user/{id}"）
	 */
	@PostMapping("/update/{id}")
	public String updateUserDetail(@PathVariable Integer id, RedirectAttributes attributes) {
		// 会員IDに対応する会員詳細情報を取得
		User user = adminUserService.findByIdUser(id);
		// 会員情報が存在するか
		if (user != null) {
			// 会員情報を削除
			adminUserService.changeUserStatus(user);
			// フラッシュメッセージの設定
			attributes.addFlashAttribute("message", "会員が削除されました");
		} else {
			// 会員が見つからなかった場合の処理
			attributes.addFlashAttribute("errorMessage", "会員が見つかりませんでした");
		}
		// 一覧画面へリダイレクト
		return "redirect:/admin/user/list";
	}

}
