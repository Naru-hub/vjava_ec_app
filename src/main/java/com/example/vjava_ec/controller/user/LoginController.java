package com.example.vjava_ec.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vjava_ec.form.user.PasswordResetForm;
import com.example.vjava_ec.service.user.EmailService;
import com.example.vjava_ec.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * ログインコントローラ
 */
@Controller
@RequestMapping("/user/login")
@RequiredArgsConstructor
public class LoginController {
	
	// DI
	private final EmailService emailService;
	private final UserService userService;
	
	 /**
     * 会員ログイン画面を表示するためのメソッド
     * @return ログイン画面のビュー名（"user/login"）
     */
	@GetMapping
	public String showLogin() {
		return "user/login/login";
	}
	
	/**
	 * パスワード再設定メール送信画面表示
	 * @return user/reset パスワード再設定メール送信画面
	 */
	@GetMapping("/reset/mail")
	public String showSendResetPasswordEmail() {
		return "user/login/reset";
	}

	/**
	 * パスワード再設定メール送信
	 * @param email
	 * @param session
	 * @param model
	 * @return user/reset パスワード再設定メール送信画面
	 */
	@PostMapping("/reset/mail")
	public String sendResetPasswordEmail(@RequestParam("email") String email,HttpSession session,Model model) {
		// 入力されたメールアドレスの登録確認
		if(userService.selectUserByEmail(email) == null) {
			model.addAttribute("error", "そのメールアドレスは登録されていません");
			return "user/login/reset";
		}
		String token = emailService.sendResetPasswordEmail(email);
		session.setAttribute("resetToken", token);
		session.setAttribute("email", email);
		// 五分後に期限を設定
		session.setAttribute("resetLimit", System.currentTimeMillis() + (5 * 60 * 1000));
		model.addAttribute("success", "パスワード再設定のメールを送信しました。");
		return "user/login/reset";
	}
	
	/**
	 * パスワード再設定画面表示
	 * @param token
	 * @param form
	 * @param session
	 * @return user/resetpassword パスワード再設定画面
	 */
	@GetMapping("/reset/password")
	public String showResetPassword(@RequestParam("token") String token,@ModelAttribute("form") PasswordResetForm form, HttpSession session,Model model) {
		// セッションに保存しているトークンとトークン期限を取得
		String resetToken = (String)session.getAttribute("resetToken");
		Long resetLimit = (Long)session.getAttribute("resetLimit");
		if(resetToken == null || !token.equals(resetToken) || resetLimit < System.currentTimeMillis()) {
			model.addAttribute("tokenError", "もう一度メールを送信してください");
			return "user/login/reset";
		}
		return "user/login/resetpassword";
	}
	
	/**
	 * パスワード再設定
	 * @param form
	 * @param result
	 * @param session
	 * @param model
	 * @return user/passwordreseted パスワード再設定完了画面
	 */
	@PostMapping("/reset/password")
	public String resetPassword(@Validated @ModelAttribute("form") PasswordResetForm form, 
								BindingResult result, 
								HttpSession session,
								RedirectAttributes attributes,
								Model model) {
		String email = (String)session.getAttribute("email");
		if(email == null) {
			return "redirect:/user/login/reset";
		}
		if(result.hasErrors()) {
			model.addAttribute("form", form);
			return "user/login/resetpassword";
		}
		if(!form.getPassword().equals(form.getPasswordConfirm())) {
			model.addAttribute("confirmError", "パスワードが一致していません");
			model.addAttribute("form", form);
			return "user/login/resetpassword";
		}
		// パスワードの再設定
		userService.updatePassword(email,form.getPassword());
		attributes.addFlashAttribute("resetMessage", "パスワードを再設定しました");
		return "redirect:/user/login";
	}
}
