package com.example.vjava_ec.controller.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.form.user.UserForm;
import com.example.vjava_ec.helper.user.UserHelper;
import com.example.vjava_ec.service.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 会員コントローラ
 */
@Controller("user")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	// DI
	private final UserService userService;
	
	/**
	 * 会員情報詳細画面表示
	 * @param model
	 * @return user/user/profile
	 */
	@GetMapping("/detail")
	public String showUserDetail(Model model) {
		model.addAttribute("user", userService.selectLoginUser());
		return "user/user/profile";
	}
	
	/**
	 * 会員情報編集画面表示
	 * @param model
	 * @return user/user/edit 会員情報編集画面
	 */
	@GetMapping("/edit")
	public String showEditUserDetail(Model model) {
		model.addAttribute("form", UserHelper.convertUserForm(userService.selectLoginUser()));
		return "user/user/edit";
	}
	
	/**
	 * 会員情報更新処理
	 * @param form UserFormフォームクラス
	 * @param bindingResult
	 * @param attributes
	 * @return redirect:/user/detail 
	 */
	@PostMapping("/update")
	public String updateUserDetail(@Validated @ModelAttribute("form") UserForm form,BindingResult bindingResult,RedirectAttributes attributes) {
		// バリデーションチェック
		if(bindingResult.hasErrors()) {
			// 元の画面に戻る
			return "user/user/edit";
		}
		// バリデーション通過、更新処理
		userService.updateUser(UserHelper.convertUser(form));
		return "redirect:/user/detail";
	}
	
	/**
	 * 退会確認画面表示
	 * @return user/user/deleteConfirm 退会確認画面
	 */
	@GetMapping("/delete/confirm")
	public String showConfirmUserDelete(Model model) {
		return "user/user/deleteConfirm";
	}
	
	/**
	 * 退会とログアウト処理
	 * @param request
	 * @param response
	 * @return redirect:/user ホーム画面に戻す
	 */
	@PostMapping("/delete")
	public String updateUserDeleteStatus(HttpServletRequest request, HttpServletResponse response) {
		// 論理削除処理
		userService.deleteUser(userService.selectLoginUser());
		 // 現在の認証情報を取得
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    // 認証情報がある場合（ユーザーがログインしている場合）
	    if (authentication != null) {
	        // SecurityContextLogoutHandlerのインスタンスを作成
	        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
	        // ログアウト処理を実行
	        // セッションの無効化やCookieの削除などを行い、セキュリティコンテキストをクリアします
	        logoutHandler.logout(request, response, authentication);
	    }
		return "redirect:/user";
	}
}