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

import com.example.vjava_ec.form.user.SignupUserForm;
import com.example.vjava_ec.service.user.EmailService;
import com.example.vjava_ec.service.user.impl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 新規登録コントローラ
 */
@Controller
@RequestMapping("/user/register")
@RequiredArgsConstructor
public class RegisterController {
	
	//DI
	private final UserServiceImpl userService;
	private final EmailService emailService;
	
    /**
     * 新規登録画面を表示
     * 
     * @param model 
     * @return user/register 新規登録画面
     */
	@GetMapping
	public String showRegister(Model model) {
		model.addAttribute("signupUserForm", new SignupUserForm());
		return "user/register/register";
	}
	
    /**
     * 入力情報のバリデーションと認証メールの送信
     * 
     * @param signupUserForm 
     * @param result 
     * @param model 
     * @param redirectAttributes
     * @param session
     * @return 成功時はredirect:/user/register/confirm メールアドレス認証画面
     * 			失敗時はuser/register 新規登録画面
     */
	@PostMapping
	public String sendAuthCodeEmail(@Validated @ModelAttribute("signupUserForm") SignupUserForm signupUserForm, 
							   BindingResult result, 
							   RedirectAttributes redirectAttributes,
							   HttpSession session) {
		//失敗時は新規登録画面に戻す
		if(result.hasErrors()) {
			return "user/register/register";
		}
		// 認証メールの送信と認証コードの取得
		int code = emailService.sendConfirmEmail(signupUserForm.getEmail());
		session.setAttribute("signupUserForm", signupUserForm);
		session.setAttribute("code", code);
		// コードの有効期限を三分後に設定
		session.setAttribute("codeLimit", System.currentTimeMillis() + (3 * 60 * 1000));
		// メール認証ページへリダイレクト
		return "redirect:/user/register/confirm";
	}
	
	/**
	 * メールアドレス認証画面表示
	 * @return user/mailConfirm メールアドレス認証画面
	 */
	@GetMapping("/confirm")
	public String authCodeConfirmForm() {
		return  "user/register/mailConfirm";
	}

	/**
	 * 入力された認証コードの確認
	 * @param codeInput
	 * @param attributes
	 * @param session
	 * @param model
	 * @return redirect:/user ホーム画面
	 */
	@PostMapping("/complete")
	public String newRegister(@RequestParam("code") String codeInput,
								RedirectAttributes attributes,
								HttpSession session,
								Model model) {
		SignupUserForm signupUserForm = (SignupUserForm)session.getAttribute("signupUserForm");
		String code = Integer.toString((int)session.getAttribute("code"));
		Long codeLimit = (Long)session.getAttribute("codeLimit");
		if(signupUserForm == null) {
			// formが空
			return "redirect:/user/register";
		}
		if(!codeInput.equals(code)) {
			// コードが違う
			model.addAttribute("codeError", "認証コードが違います");
			return "user/register/mailConfirm";
		}
		if(codeLimit < System.currentTimeMillis()) {
			// コードの期限切れ
			model.addAttribute("codeError", "認証コードの期限が切れています");
			return "user/register/mailConfirm";
		}
		try {
			userService.NewRegisterUser(signupUserForm);
			// フラッシュメッセージを追加
			attributes.addFlashAttribute("successMessage", "登録が成功しました。"); 
		} catch(Exception e) {
			return "redirect:/user/register";
		}
		return "redirect:/user";
	}
	
	/**
	 * 認証メールの再送信
	 * @param session
	 * @return redirect:/user/register/confirm メールアドレス認証画面
	 */
	@PostMapping("/resend")
	public String resendAuthCodeEmail(HttpSession session) {
		SignupUserForm form = (SignupUserForm)session.getAttribute("signupUserForm");
		if(form == null) {
			return "redirect:/user/register";
		}
		// 認証メールの送信と認証コードの取得
		int code = emailService.sendConfirmEmail(form.getEmail());
		session.setAttribute("signupUserForm", form);
		session.setAttribute("code", code);
		// コードの有効期限を三分後に設定
		session.setAttribute("codeLimit", System.currentTimeMillis() + (3 * 60 * 1000));
		// メール認証ページへリダイレクト
		return "redirect:/user/register/confirm";
	}
}